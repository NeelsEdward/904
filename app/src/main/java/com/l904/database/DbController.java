package com.l904.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Dell Laptop on 8/8/2015.
 */
public class DbController {

    private static MysqlLiteHelper myDbHelper;
    private static DbController dbController;
    private Context context;
    private SQLiteDatabase db;

    public static DbController  getInstace(){
        if(dbController == null){
            dbController = new DbController();
        }
        return  dbController;
    }

    public  void openDb(Context context){
        this.context  =context;
        if(myDbHelper == null){
            myDbHelper = new MysqlLiteHelper(context);
            myDbHelper.getWritableDatabase();
        }
    }
    public void insertTodo(String str1 , String str2){
        db = myDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MysqlLiteHelper.COLUMN_NAME_TODO_NAME,str1);
        cv.put(MysqlLiteHelper.COLUMN_NAME_TODO_COLOR,str2);
        db.insert(MysqlLiteHelper.TABLE_NAME_TODO,null,cv);
    }
    public void insertExpense(String str1 , String str2){
        db = myDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MysqlLiteHelper.COLUMN_NAME_EXPENSE_DATE,str1);
        cv.put(MysqlLiteHelper.COLUMN_NAME_EXPENSE_AMOUNT,str2);
        db.insert(MysqlLiteHelper.TABLE_NAME_EXPENSE,null,cv);
    }
    public void insertItem(String str1 , String str2,int todoId){
        db = myDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MysqlLiteHelper.COLUMN_NAME_ITEM_TODO,str1);
        cv.put(MysqlLiteHelper.COLUMN_NAME_ITEM_TARGET,str2);
        cv.put(MysqlLiteHelper.COLUMN_NAME_ITEM_TODO_ID,todoId);
        db.insert(MysqlLiteHelper.TABLE_NAME_ITEM,null,cv);
    }

    public boolean deleteTodo(int id){
        db = myDbHelper.getWritableDatabase();
        db.delete(MysqlLiteHelper.TABLE_NAME_ITEM,MysqlLiteHelper.COLUMN_NAME_ITEM_TODO_ID +" = "+id,null );
        return db.delete(MysqlLiteHelper.TABLE_NAME_TODO,MysqlLiteHelper.COLUMN_NAME_TODO_ID +" = "+id,null )>0;
    }
    public boolean deleteExpense(int id){
        db = myDbHelper.getWritableDatabase();
        return db.delete(MysqlLiteHelper.TABLE_NAME_EXPENSE,MysqlLiteHelper.COLUMN_NAME_EXPENSE_ID +" = "+id,null )>0;
    }
    public boolean deleteItem(int id){
        db = myDbHelper.getWritableDatabase();
        return db.delete(MysqlLiteHelper.TABLE_NAME_ITEM,MysqlLiteHelper.COLUMN_NAME_ITEM_ID +" = "+id,null )>0;
    }

    public void editTodo(String str1 , String str2,int id){
        db = myDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MysqlLiteHelper.COLUMN_NAME_TODO_NAME, str1);
        cv.put(MysqlLiteHelper.COLUMN_NAME_TODO_COLOR, str2);
        db.update(MysqlLiteHelper.TABLE_NAME_TODO,cv,MysqlLiteHelper.COLUMN_NAME_TODO_ID+" = "+id,null);
    }
    public void editExpense(String str1 , String str2,int id){
        db = myDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MysqlLiteHelper.COLUMN_NAME_EXPENSE_DATE,str1);
        cv.put(MysqlLiteHelper.COLUMN_NAME_EXPENSE_AMOUNT, str2);
        db.update(MysqlLiteHelper.TABLE_NAME_EXPENSE, cv, MysqlLiteHelper.COLUMN_NAME_EXPENSE_ID+ " = " + id, null);
    }
    public void editItem(String str1 , String str2,int id){
        db = myDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MysqlLiteHelper.COLUMN_NAME_ITEM_TODO,str1);
        cv.put(MysqlLiteHelper.COLUMN_NAME_ITEM_TARGET, str2);
        db.update(MysqlLiteHelper.TABLE_NAME_ITEM, cv, MysqlLiteHelper.COLUMN_NAME_ITEM_ID + " = " + id, null);
    }

    public ArrayList<ParamsUtil>  getAllTodoExpense(int id){
        String str =MysqlLiteHelper.TABLE_NAME_TODO;
        if(id == ParamsUtil.TYPE_EXPENSE){
            str =MysqlLiteHelper.TABLE_NAME_EXPENSE;
        }

        db = myDbHelper.getReadableDatabase();
        ArrayList<ParamsUtil> list  = new ArrayList<ParamsUtil>();
        Cursor cursor =null;
        try {
        cursor  = db.rawQuery("select * from "+str,null);
            if (cursor.moveToFirst()) {
                do {
                    int id1 = cursor.getInt(0);
                    String s1 = cursor.getString(1);
                    String s2 = cursor.getString(2);
                    ParamsUtil param = new ParamsUtil(id1, s1, s2);
                    list.add(param);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor !=null)
            cursor.close();
            db.close();
        }
        return list;
    }
    public List<ParamsUtil>  getAllItems(int id){
        db = myDbHelper.getReadableDatabase();
        List<ParamsUtil> list  = new ArrayList<ParamsUtil>();
            String[] columns = {MysqlLiteHelper.COLUMN_NAME_ITEM_ID,MysqlLiteHelper.COLUMN_NAME_ITEM_TODO,MysqlLiteHelper.COLUMN_NAME_ITEM_TARGET};
        Cursor cursor =null;
        try {
           cursor = db.query(MysqlLiteHelper.TABLE_NAME_ITEM, columns, MysqlLiteHelper.COLUMN_NAME_ITEM_TODO_ID+" = "+id, new String[]{MysqlLiteHelper.COLUMN_NAME_ITEM_TODO_ID}, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int id1 = cursor.getInt(0);
                    String s1 = cursor.getString(2);
                    String s2 = cursor.getString(3);
                    ParamsUtil param = new ParamsUtil(id1, s1, s2);
                    list.add(param);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor !=null)
            cursor.close();
            db.close();
        }
        return list;
    }

    private static class MysqlLiteHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "todoList.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME_ITEM = "todoItem";
        private static final String COLUMN_NAME_ITEM_ID = "_id";
        private static final String COLUMN_NAME_ITEM_TODO_ID = "todo_id";
        private static final String COLUMN_NAME_ITEM_TODO = "description";
        private static final String COLUMN_NAME_ITEM_TARGET = "target_completion";
        private static final String CREATE_TABLE_ITEM ="create table "+TABLE_NAME_ITEM +"("+COLUMN_NAME_ITEM_ID +" integer primary key autoincrement, "
                                                                                               + COLUMN_NAME_ITEM_TODO_ID+" integer, "
                                                                                               +COLUMN_NAME_ITEM_TODO+" text not null, "+COLUMN_NAME_ITEM_TARGET +" text);";

        private static final String TABLE_NAME_TODO = "todo";
        private static final String COLUMN_NAME_TODO_ID = "_id";
        private static final String COLUMN_NAME_TODO_NAME = "name";
        private static final String COLUMN_NAME_TODO_COLOR = "color";
        private static final String CREATE_TABLE_TODO ="create table "+TABLE_NAME_TODO +"("+COLUMN_NAME_TODO_ID +" integer primary key autoincrement, "
                +COLUMN_NAME_TODO_NAME+" text not null, "+COLUMN_NAME_TODO_COLOR +" text);";


        private static final String TABLE_NAME_EXPENSE = "todoExpense";
        private static final String COLUMN_NAME_EXPENSE_ID = "_id";
        private static final String COLUMN_NAME_EXPENSE_DATE = "date";
        private static final String COLUMN_NAME_EXPENSE_AMOUNT = "amount";
        private static final String CREATE_TABLE_EXPENSE ="create table "+TABLE_NAME_EXPENSE +"("+COLUMN_NAME_EXPENSE_ID +" integer primary key autoincrement, "
                +COLUMN_NAME_EXPENSE_DATE+" text not null, "+COLUMN_NAME_EXPENSE_AMOUNT +" text);";

        private MysqlLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_ITEM);
            db.execSQL(CREATE_TABLE_TODO);
            db.execSQL(CREATE_TABLE_EXPENSE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
