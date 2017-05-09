package com.example.trang.note.manager;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trang.note.App;
import com.example.trang.note.note.Note;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Trang on 5/4/2017.
 */

public class NoteManager {
    public static final String DATABASE_PATH = App.getContext().getApplicationInfo().dataDir + "/databases/";
    public static final String DATABASE_NAME = "listno.db";
    public static final String NAME_TABLE = "listnote";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_DATE = "date";
    public SQLiteDatabase sqLiteDatabase;


    public NoteManager() {

    }

    public void copyDatabase(Context context) {
        AssetManager assetManager = context.getAssets();

        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(DATABASE_NAME);
            String path = DATABASE_PATH + DATABASE_NAME;
            File file = new File(path);
            if (file.exists()) {
                return;
            }
            File filepath = new File(DATABASE_PATH);
            if (!filepath.exists()) {
                filepath.mkdir();
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int lenght;
            while ((lenght = inputStream.read(bytes)) > 0) {
                fos.write(bytes, 0, lenght);
            }
            inputStream.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            //App.getContext().getDatabasePath(DATABASE_NAME).getAbsolutePath() : duong dan 2
            sqLiteDatabase = SQLiteDatabase.openDatabase(App.getContext().getDatabasePath(DATABASE_NAME).getAbsolutePath(),
                    null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDatabase() {
        if (sqLiteDatabase != null || sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public boolean insertDatabase(String content, String color, String date) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        if (!content.isEmpty() && !color.isEmpty() && !date.isEmpty()) {

            contentValues.put(COLUMN_CONTENT, content);
            contentValues.put(COLUMN_COLOR, color);
            contentValues.put(COLUMN_DATE, date);
            sqLiteDatabase.insert(NAME_TABLE, null, contentValues);
            return true;
        }
        return false;


    }

    public void updateDatabase(Note note) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CONTENT, note.getContent());
        contentValues.put(COLUMN_COLOR, note.getColor());
        contentValues.put(COLUMN_DATE, note.getDate());
        sqLiteDatabase.update(NAME_TABLE, contentValues, "id=?", new String[]{String.valueOf(note.getId())});
        closeDatabase();
    }

    public boolean deleteId(int id) {
        openDatabase();
        sqLiteDatabase.delete(NAME_TABLE, "id=?", new String[]{String.valueOf(id)});
        closeDatabase();
        return true;
    }

    public boolean deleteDate(String date) {
        openDatabase();
        sqLiteDatabase.delete(NAME_TABLE, "date=?", new String[]{date});
        closeDatabase();
        return true;
    }


    public ArrayList<Note> getAllNote(Context mContext) {
        openDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + NAME_TABLE + " ORDER BY " + COLUMN_ID + " ASC", null);
        if (cursor == null) {
            return new ArrayList<>();
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            return new ArrayList<>();
        }
        ArrayList<Note> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        int id = cursor.getColumnIndex(COLUMN_ID);
        int content = cursor.getColumnIndex(COLUMN_CONTENT);
        int color = cursor.getColumnIndex(COLUMN_COLOR);
        int date = cursor.getColumnIndex(COLUMN_DATE);
        while (!cursor.isAfterLast()) {
            arrayList.add(new Note(cursor.getInt(id), cursor.getString(content), cursor.getString(color), cursor.getString(date)));
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return arrayList;
    }
}
