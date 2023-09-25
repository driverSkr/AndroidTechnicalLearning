package com.example.contentProvider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private static final int table1Dir = 0;//表示访问table1 表中的所有数据
    private static final int table1Item = 1;//表示访问table1 表中的单条数据
    private static final int table2Dir = 2;//表示访问table2 表中的所有数据
    private static final int table2Item = 3;//表示访问table2 表中的单条数据

    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public MyContentProvider() {
    }

    //初始化ContentProvider 的时候调用。
    // 通常会在这里完成对数据库的创建和升级等操作
    //返回true表示ContentProvider 初始化成功，返回false则表示失败
    @Override
    public boolean onCreate() {
        /*这个方法接收3个参数，可以分别把authority、path和一个自定义代码传进去。
        这样，当调用UriMatcher的match()方法时，就可以将一个Uri对象传入，返回值是某个能够匹配这个Uri对象所对应的自定义代码*/
        uriMatcher.addURI("com.example.app.provider", "table1", table1Dir);
        uriMatcher.addURI("com.example.app.provider", "table1/#", table1Item);
        uriMatcher.addURI("com.example.app.provider", "table2", table2Dir);
        uriMatcher.addURI("com.example.app.provider", "table2/#", table2Item);
        return true;
    }

    //uri参数用于确定查询哪张表
    // projection参数用于确定查询哪些列
    // selection和selectionArgs参数用于约束查询哪些行
    //sortOrder参数用于对结果进行排序
    // 查询的结果存放在Cursor对象中返回
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case table1Dir:
                // 查询table1表中的所有数据
                break;
            case table1Item:
                // 查询table1表中的单条数据
                break;
            case table2Dir:
                // 查询table2表中的所有数据
                break;
            case table2Item:
                // 查询table2表中的单条数据
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return null;
    }

    //uri参数用于确定要添加到的表
    // 待添加的数据保存在values参数中。
    // 添加完成后，返回一个用于表示这条新记录的URI
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    //uri参数用于确定更新哪一张表中的数据，
    // 新数据保存在values参数中，
    // selection和selectionArgs参数用于约束更新哪些行，
    //受影响的行数将作为返回值返回
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    //uri参数用于确定删除哪一张表中的数据，
    //selection和selectionArgs参数用于约束删除哪些行，
    // 被删除的行数将作为返回值返回
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    //根据传入的内容URI返回相应的MIME类型
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case table1Dir:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case table1Item:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case table2Dir:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case table2Item:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
            default:
                return null;
        }
    }
}