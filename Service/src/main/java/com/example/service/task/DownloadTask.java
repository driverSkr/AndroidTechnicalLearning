package com.example.service.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

//AsyncTask 的基本用法
/*AsyncTask 类指定3个泛型参数，这3个参数的用途如下。
Params。在执行AsyncTask 时需要传入的参数，可用于在后台任务中使用。
Progress。在后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。
Result。当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。*/
/*如果想要启动这个任务，只需编写以下代码即可：DownloadTask().execute()
* 当然，你也可以给execute()方法传入任意数量的参数，这些参数将会传递到DownloadTask的doInBackground()方法当中。*/
public class DownloadTask extends AsyncTask<Void,Integer,Boolean> {

    private Context context;
    private ProgressDialog progressDialog;

    public DownloadTask(Context context,ProgressDialog progressDialog){
        this.context = context;
        this.progressDialog = progressDialog;
    }

    //这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，比如显示一个进度条对话框等。
    @Override
    protected void onPreExecute() {
        progressDialog.show();//显示进度对话框
    }

    //这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务
    /*任务一旦完成，就可以通过return语句将任务的执行结果返回，如果AsyncTask的第三个泛型参数指定的是Void，就可以不返回任务执行结果*/
    /*注意，在这个方法中是不可以进行UI操作的，如果需要更新UI元素，比如说反馈当前任务的执行进度，可以调用publishProgress (Progress...)方法来完成*/
    @Override
    protected Boolean doInBackground(Void... params) {
        try{
            while (true){
                int downloadPercent = 99/*doDownload()*/;
                //跟新进度，使用publishProgress方法可以把数据传到主线程
                /*一旦调用该方法，onProgressUpdate()方法就会很快被调用，在这里就可以进行UI操作了*/
                publishProgress(downloadPercent);
                if (downloadPercent >= 100){
                    break;
                }
            }
            //返回一个值后，这样onPostExecute()方法就会很快被调用，这个方法也是在主线程中运行的
            /*然后，在onPostExecute()我们会根据下载的结果弹出相应的Toast 提示，从而完成整个DownloadTask任务*/
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //当在后台任务中调用了publishProgress(Progress...)方法后，onProgressUpdate (Progress...)方法就会很快被调用
    /*该方法中携带的参数就是在后台任务中传递过来的。在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新*/
    @Override
    protected void onProgressUpdate(Integer... values) {
        //在这里更新下载进度
        progressDialog.setMessage("Download" + values[0] + "%");
    }

    //当后台任务执行完毕并通过return语句进行返回时，这个方法就很快会被调用
    /*返回的数据会作为参数传递到此方法中，可以利用返回的数据进行一些UI操作，比如说提醒任务执行的结果，以及关闭进度条对话框等。*/
    @Override
    protected void onPostExecute(Boolean result) {
        progressDialog.dismiss();//关闭进度对话框
        //在这里提示下载结果
        if (result){
            Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show();
        }
    }
}
