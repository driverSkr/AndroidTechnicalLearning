package com.example.cameraalbum;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
* 调用摄像头拍照并显示出来
* 从相册中选择图片
* */
public class TakePictureActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int takePhoto = 1;
    private static final int fromAlbum = 2;
    private Uri imageUri;
    private File outputImage;
    private ImageView imageView;
    private ActivityResultLauncher<Intent> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        imageView = findViewById(R.id.imageView);
        findViewById(R.id.takePhotoBtn).setOnClickListener(this);
        findViewById(R.id.fromAlbumBtn).setOnClickListener(this);

        //对回传消息的处理（新方法，替代了startActivityForResult）
        /*register =  registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            switch (result.getResultCode()){
                case takePhoto:
                    if (result.getResultCode() == Activity.RESULT_OK){
                        //将拍摄的照片显示出来
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                            imageView.setImageBitmap(rotateIfRequired(bitmap));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*调用摄像头拍照并显示出来*/
            case R.id.takePhotoBtn:
                //创建File对象，用于存储拍照后的图片（存放在手机SD卡的应用关联缓存目录下）
                //从Android 6.0 系统开始，读写SD卡被列为了危险权限，如果将图片存放在SD卡的任何其他目录，都要进行运行时权限处理才行，而使用应用关联目录则可以跳过这一步
                outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                if (outputImage.exists()){
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                    //设备的系统版本高于Android 7.0就调用FileProvider 的getUriForFile()方法将File对象转换成一个封装过的Uri对象
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        //getUriForFile()方法接收3个参数：第一个参数要求传入Context对象，第二个参数可以是任意唯一的字符串，第三个参数则是我们刚刚创建的File对象
                /*之所以要进行这样一层转换，是因为从Android 7.0 系统开始，直接使用本地真实路径的Uri被认为是不安全的，会抛出一个FileUriExposedException异常。
                而FileProvider则是一种特殊的ContentProvider ，它使用了和ContentProvider 类似的机制来对数据进行保护，可以选择性地将封装过的Uri共享给外部，从而提高了应用的安全性。*/
                        imageUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider",outputImage);
                    }else {
                        //设备的系统版本低于Android 7.0 ，就调用Uri的fromFile()方法将File对象转换成Uri对象
                        //这个Uri对象标识着output_image.jpg 这张图片的本地真实路径
                        imageUri = Uri.fromFile(outputImage);
                    }
                    //启动相机程序
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    //指定图片的输出地址
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    //消息传给ActResponseActivity，并请求回传数据
                    //register.launch(intent);
                    startActivityForResult(intent, takePhoto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            /*从相册中选择图片*/
            case R.id.fromAlbumBtn:
                //示打开系统的文件选择器
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //设置一些条件过滤，只允许可打开的图片文件显示出来
                intent.setType("image/*");
                startActivityForResult(intent, fromAlbum);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            /*调用摄像头拍照并显示出来*/
            case takePhoto:
                if (resultCode == Activity.RESULT_OK){
                    //将拍摄的照片显示出来
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageView.setImageBitmap(rotateIfRequired(bitmap));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /*从相册中选择图片*/
            case fromAlbum:
                if (requestCode == Activity.RESULT_OK && data != null){
                    //调用了返回Intent 的getData()方法来获取选中图片的Uri
                    Uri uri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = getBitmapFromUri(uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
                break;
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws FileNotFoundException {
        ParcelFileDescriptor r = getContentResolver().openFileDescriptor(uri, "r");
        return BitmapFactory.decodeFileDescriptor(r.getFileDescriptor());
    }

    //判断图片是否需要旋转
    private Bitmap rotateIfRequired(Bitmap bitmap) throws IOException {
        ExifInterface exif = new ExifInterface(outputImage.getPath());
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        switch (orientation){
            case ExifInterface.ORIENTATION_ROTATE_90:
               return rotateBitmap(bitmap,90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateBitmap(bitmap,180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateBitmap(bitmap,270);
            default: return bitmap;
        }
    }
    //旋转图片
    private Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) degree);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        //将不再需要的Bitmap对象回收
        bitmap.recycle();
        return rotatedBitmap;
    }
}