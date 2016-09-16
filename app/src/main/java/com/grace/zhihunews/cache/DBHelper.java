package com.grace.zhihunews.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.grace.zhihunews.network.entity.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "story.db";
    public static final int VERSION = 1;
    private static DBHelper dbHelper;
    private SQLiteDatabase db;

    private static final String CREATE_STORY = "create table Story (" +
            "_id integer primary key autoincrement, " +
            "id integer unique, " +
            "ga_prefix text," +
            "title text," +
            "image text," +
            "date text)";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public synchronized static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Story");
        onCreate(db);
    }

    public long saveStory(Story story) {
        long story_id = 0;
        SQLiteDatabase db = getWritableDatabase();
        if (story != null) {
            ContentValues values = new ContentValues();
            values.put("id", story.getId());
            values.put("ga_prefix", story.getGa_prefix());
            values.put("title", story.getTitle());
            values.put("image", story.getImages().get(0));
            values.put("date", story.getDate());
            story_id = db.insert("Story", null, values);
        }
        return story_id;
    }

    public List<Story> loadStoriesByDate(String date) {
        SQLiteDatabase db = getWritableDatabase();
        List<Story> stories = new ArrayList<>();
        Cursor cursor = db.query("Story", null, "date = ?", new String[] {date}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(cursor.getColumnIndex("id")));
                story.setGa_prefix(cursor.getString(cursor.getColumnIndex("ga_prefix")));
                story.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                List<String> images = new ArrayList<>();
                images.add(cursor.getString(cursor.getColumnIndex("image")));
                story.setImages(images);
                story.setDate(cursor.getColumnName(cursor.getColumnIndex("date")));
                stories.add(story);
            } while (cursor.moveToNext());
        }
        return stories;
    }

    public long deleteStoriesByDate(String date) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Story", "date = ?", new String[] {date});
    }

    public long deleteAllStory() {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Story", null, null);
    }
}
