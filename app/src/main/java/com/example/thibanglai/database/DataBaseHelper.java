package com.example.thibanglai.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import com.example.thibanglai.model.BienBao;
import com.example.thibanglai.model.DA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "DB_LAW";
    public final static String DATABASE_PATH = "/data/data/com.example.thibanglai/databases/";
    public static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }


    //Create a empty database on the system
    public void createDatabase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            Log.d("DBExists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            // onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist1 = checkDataBase();
        if (!dbExist1) {
            this.getReadableDatabase();
            try {
                this.close();
                copyDataBase();
                Log.d("DBExists", "COPY DONE");
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }

    //Check database already exist or not
    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        } catch (SQLiteException e) {
        }
        return checkDB;
    }

    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException {

        InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public void db_delete()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }
    //Open database
    public void openDatabase() throws SQLException
    {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }

    }
    public ArrayList<BienBao> ReadBienBao(){
        ArrayList<BienBao> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM Signs";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                //BienBao(int loaiBienBao, String maBienBao, String tenBienBao, String noiDung,String thumb,String hashtag) {
                data.add(new BienBao(cursor.getInt(5),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(6)));
            }while (cursor.moveToNext());
        }
        return data;
    }
    public Cursor getData(String query){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query,null);
    }
    public void setMarked(int question_id,boolean marked_stt){
        int stt = marked_stt ? 1 : 0;
        SQLiteDatabase database = getWritableDatabase();
        String sql="UPDATE Question SET marked = "+stt+" WHERE id= "+question_id;
        database.execSQL(sql);
        database.close();
    }
    public int getMarked(int question_id){
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT marked FROM Question WHERE id= '%"+question_id+"%'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 0;
    }
    public void setChoose(int question_id,int choose,int maDe){
        SQLiteDatabase database = getWritableDatabase();
        String sql="UPDATE links SET choose = "+choose+" WHERE maCH= "+question_id+" AND maDe= "+maDe;
        database.execSQL(sql);
    }
    public int getChoose(int question_id,int maDe){
        SQLiteDatabase database = getReadableDatabase();
        String sql="SELECT choose FROM links WHERE maCH= "+question_id+" AND maDe= "+maDe;
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 0;
    }
}

