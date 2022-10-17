package com.example.thibanglai.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.thibanglai.model.BienBao;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String query){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    public Cursor getData(String query){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE bienBao(loaiBienBao INT,maBienBao VARCHAR(50),tenBienBao VARCHAR(50),noiDung VARCHAR(255),thumb VARCHAR(256),hashtag VARCHAR(256))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void FirstRun(){
        SQLiteDatabase database = getWritableDatabase();
        String sql="INSERT INTO bienBao VALUES(1,'P123a','Cấm rẽ trái','Để báo cấm rẽ trái ở những vị trí đường giao nhau Để báo cấm rẽ trái. ở những vị trí đường giao nhau Để báo cấm rẽ trái ở những vị trí đường giao nhau. Để báo cấm rẽ trái ở những vị trí đường giao nhau','left_banned','#Rẽ trái')";
        String sql2="INSERT INTO bienBao VALUES(2,'P123b','Biển hiệu lệnh','Để báo cấm rẽ trái ở những vị trí đường giao nhau Để báo cấm rẽ trái. ở những vị trí đường giao nhau Để báo cấm rẽ trái ở những vị trí đường giao nhau. Để báo cấm rẽ trái ở những vị trí đường giao nhau','left_banned','#Rẽ trái')";
        database.execSQL(sql);
        database.execSQL(sql2);

    }
    public ArrayList<BienBao> ReadBienBao(){
        ArrayList<BienBao> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM bienBao";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                data.add(new BienBao(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return data;
    }
}
