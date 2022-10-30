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
import com.example.thibanglai.model.Exam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

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
            this.getWritableDatabase();
            try {
                this.close();
                copyDataBase();
                Log.d("DBExists", "COPY DONE");
            } catch (IOException e) {
                throw new Error("Error copying database"+e);
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

        InputStream mInput = myContext.getAssets().open("databases/"+DATABASE_NAME);
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
    public ArrayList<Exam> ReadDe(){
        ArrayList<Exam> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM exam";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                //Exam(int time,int time, int current_question, int num_correct_answer)
                data.add(new Exam(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        } else {
            //
            data.add(new Exam(0,0,0,0));
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
        database.close();
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
    public void generateDe(){
        int LastMaDe = getLastMaDe();
        SQLiteDatabase database = getWritableDatabase();
        String sql="insert into exam (time,current_question,num_correct_answer) values(?,?,?)";
        generateCauhoi(LastMaDe,database);
        String sql2="UPDATE sqlite_sequence SET seq=0 WHERE name='exam'";
        database.execSQL(sql2);
        database.execSQL(sql, new String[]{String.valueOf(1140000),"","0"});
        database.close();
    }

    private void generateCauhoi(int LastMaDe,SQLiteDatabase database) {
        int maCH[] = noiMang(getLT(database),getBB(database),getSH(database));
        Log.d("so cau hoi", String.valueOf(maCH.length));
        for(int i =0;i<25;i++){
            String sql2="insert into links (maDe,maCH,choose) values(?,?,?)";
            database.execSQL(sql2, new String[]{String.valueOf(LastMaDe+1),String.valueOf(maCH[i]),String.valueOf(0)});
        }
    }
    private int[] noiMang( int[] maCHLT,int[] maCHBB,int[] maCHSH){
        int[] result = new int[25];
        System.arraycopy(maCHLT, 0, result, 0, 15);
        System.arraycopy(maCHBB, 0, result, 15, 5);
        System.arraycopy(maCHSH, 0, result, 20, 5);
        return result;
    }
    // CẤU TRÚC ĐỀ THI: 15 LUẬT GIAO THÔNG(1,3,4), 5 SA HÌNH(7), 5 BIỂN BÁO(6)
    private int[] getLT(SQLiteDatabase database){
        return sampleRandomNumbersWithoutRepetition(15,1,database);
    }
    private int[] getBB(SQLiteDatabase database){
        return sampleRandomNumbersWithoutRepetition(5,2,database);
    }
    private int[] getSH(SQLiteDatabase database){
        return sampleRandomNumbersWithoutRepetition(5,3,database);
    }
    public static int[] sampleRandomNumbersWithoutRepetition(int count,int type,SQLiteDatabase database) {
        Random rng = new Random();

        int[] result = new int[count];
        int cur = 0,start=0,end=0;

        String sql1 = "SELECT id FROM Question WHERE question_type =1 OR question_type = 3 OR question_type = 5";
        String sql12 = "SELECT COUNT(*) FROM Question WHERE question_type =1 OR question_type = 3 OR question_type = 5";

        String sql2 = "SELECT id FROM Question WHERE question_type =6";
        String sql22 = "SELECT COUNT(*) FROM Question WHERE question_type =6";

        String sql3 = "SELECT id FROM Question WHERE question_type =7";
        String sql32 = "SELECT COUNT(*) FROM Question WHERE question_type =7";
        if(type == 1){
            Cursor cursor = database.rawQuery(sql12,null);
            if(cursor.moveToFirst()){
                do {
                    end= cursor.getInt(0);
                }while (cursor.moveToNext());
            } else end= 0;
        }
        if(type == 2){
            Cursor cursor = database.rawQuery(sql22,null);
            if(cursor.moveToFirst()){
                do {
                    end= cursor.getInt(0);
                }while (cursor.moveToNext());
            } else end= 0;
        }
        if(type == 3){
            Cursor cursor = database.rawQuery(sql32,null);
            if(cursor.moveToFirst()){
                do {
                    end= cursor.getInt(0);
                }while (cursor.moveToNext());
            } else end= 0;
        }
        
        int remaining = end - start;

        ArrayList<Integer> cauHoi = new ArrayList<Integer>();
        //
        if(type==1){
            cauHoi = addQuestionId(cauHoi,sql1,database);
        }
        if(type==2){
            cauHoi = addQuestionId(cauHoi,sql2,database);
        }
        if(type==3){
            cauHoi = addQuestionId(cauHoi,sql3,database);
        }
        //
        for (int i = start; i < end && count > 0; i++) {
            double probability = rng.nextDouble();
            if (probability < ((double) count) / (double) remaining) {
                count--;
                result[cur++] = cauHoi.get(i);
            }
            remaining--;
        }
        return result;
    }

    private static ArrayList<Integer> addQuestionId(ArrayList<Integer> cauHoi,String sql,SQLiteDatabase database) {
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                cauHoi.add(cursor.getInt(0));
            }while (cursor.moveToNext());
        }
        return cauHoi;
    }
    //

    private int getLastMaDe(){
        SQLiteDatabase database = getReadableDatabase();
        String sql ="SELECT * FROM exam ORDER BY maDe DESC LIMIT 1";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                Log.d("last ma de", String.valueOf(cursor.getInt(0)));
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 1;
    }
    public void saveStatus(long time, int curentQuestion, int maDe){
        SQLiteDatabase database = getWritableDatabase();
        String sql ="UPDATE exam SET time ="+time+", current_question ="+curentQuestion+" WHERE maDe="+maDe;
        database.execSQL(sql);
        database.close();
    }
    public void clear(int maDe){
        SQLiteDatabase database = getWritableDatabase();
        //time = 1140000
        String sql ="UPDATE links SET choose =0 WHERE maDe="+maDe;
        String sql2 ="UPDATE exam SET time =1140000,num_correct_answer=0,current_question=1 WHERE maDe="+maDe;
        database.execSQL(sql);
        database.execSQL(sql2);
        database.close();
    }

    public void setKq(int maDe){
        SQLiteDatabase database = getWritableDatabase();
        String sql ="UPDATE exam SET num_correct_answer =1 WHERE maDe="+maDe;
        database.execSQL(sql);
        database.close();
    }
    public int getIsFinish(int maDe){
        SQLiteDatabase database = getReadableDatabase();
        String sql ="Select num_correct_answer From exam WHERE maDe="+maDe;
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 0;
    }
    public int getTime(int maDe){
        SQLiteDatabase database = getReadableDatabase();
        String sql ="Select time From exam WHERE maDe="+maDe;
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 0;
    }
    public int getCurrentQuestion(int maDe){
        SQLiteDatabase database = getReadableDatabase();
        String sql ="Select current_question From exam WHERE maDe="+maDe;
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 1;
    }
    public int getNumberSave(){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM Question WHERE marked =1";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 0;
    }
    public int getNumberWrong(){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM Question WHERE wrong =1";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return 0;
    }
    public int getWrongChoose(int id){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT wrong_choose FROM Question WHERE id ="+id;
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return -1;
    }
    public void setWrongChoose(int id,int wrong_choose){
        SQLiteDatabase database = getWritableDatabase();
        //UPDATE exam SET num_correct_answer =1
        String sql = "UPDATE Question SET wrong_choose ="+wrong_choose+" WHERE id ="+id;
        database.execSQL(sql);
        database.close();
    }
    public void setWrongQuestion(int id,boolean stt){
        SQLiteDatabase database = getWritableDatabase();
        //UPDATE exam SET num_correct_answer =1
        String sql = "UPDATE Question SET wrong ="+(stt ? 1: 0)+" WHERE id ="+id;
        database.execSQL(sql);
        database.close();
    }
    public int getQuestionID(int maDe){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT maCH FROM links WHERE maDe ="+maDe;
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                return cursor.getInt(0);
            }while (cursor.moveToNext());
        } else return -1;
    }
}

