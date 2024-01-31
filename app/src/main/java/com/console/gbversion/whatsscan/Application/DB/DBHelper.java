package com.console.gbversion.whatsscan.Application.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.console.gbversion.whatsscan.Application.Model.Chats_Pojo;
import com.console.gbversion.whatsscan.Application.Model.Message_Pojo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "deletemesseges", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE chats(id INTEGER PRIMARY KEY,chatnames TEXT,ccond INTEGER,lastmsg TEXT,created_at DATETIME)");
        sQLiteDatabase.execSQL("CREATE TABLE msgs(id INTEGER PRIMARY KEY,mcond INTEGER,msgname TEXT,mgrp TEXT,mmsg TEXT,created_at DATETIME)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS msgs");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS chats");
        onCreate(sQLiteDatabase);
    }

    public void createCHAT(Chats_Pojo chats_Pojo) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (Exists(chats_Pojo.getName())) {
            contentValues.put("created_at", chats_Pojo.getDatetime());
            contentValues.put("lastmsg", chats_Pojo.getLastmsg());
            writableDatabase.update("chats", contentValues, "chatnames = ? ", new String[]{chats_Pojo.getName()});
            return;
        }
        contentValues.put("chatnames", chats_Pojo.getName());
        contentValues.put("lastmsg", chats_Pojo.getLastmsg());
        contentValues.put("ccond", Integer.valueOf(chats_Pojo.getCond()));
        contentValues.put("created_at", chats_Pojo.getDatetime());
        writableDatabase.insert("chats", null, contentValues);
    }

    public long createMSG(Message_Pojo message_Pojo) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("msgname", message_Pojo.getName());
        contentValues.put("mcond", Integer.valueOf(message_Pojo.getCond()));
        contentValues.put("mgrp", message_Pojo.getGrp());
        contentValues.put("mmsg", message_Pojo.getMsg());
        contentValues.put("created_at", message_Pojo.getDatetime());
        return writableDatabase.insert("msgs", null, contentValues);
    }

    public List<Chats_Pojo> getAllChats() {
        ArrayList arrayList = new ArrayList();
        Log.e("DatabaseHelper", "SELECT  * FROM chats ORDER BY created_at DESC ");
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM chats ORDER BY created_at DESC ", null);
        if (rawQuery.moveToFirst()) {
            do {
                Chats_Pojo chats_Pojo = new Chats_Pojo();
                chats_Pojo.setName(rawQuery.getString(rawQuery.getColumnIndex("chatnames")));
                chats_Pojo.setDatetime(rawQuery.getString(rawQuery.getColumnIndex("created_at")));
                chats_Pojo.setLastmsg(rawQuery.getString(rawQuery.getColumnIndex("lastmsg")));
                chats_Pojo.setCond(rawQuery.getInt(rawQuery.getColumnIndex("ccond")));
                arrayList.add(chats_Pojo);
            } while (rawQuery.moveToNext());
            rawQuery.close();
            return arrayList;
        }
        rawQuery.close();
        return arrayList;
    }

    public List<Message_Pojo> getAllMsgfromchat(String str) {
        ArrayList arrayList = new ArrayList();
        Log.e("DatabaseHelper", "SELECT  * FROM msgs WHERE mgrp = ? ORDER BY id DESC");
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM msgs WHERE mgrp = ? ORDER BY id DESC", new String[]{str});
        if (rawQuery.moveToFirst()) {
            do {
                Message_Pojo message_Pojo = new Message_Pojo();
                message_Pojo.setName(rawQuery.getString(rawQuery.getColumnIndex("msgname")));
                message_Pojo.setMsg(rawQuery.getString(rawQuery.getColumnIndex("mmsg")));
                message_Pojo.setGrp(rawQuery.getString(rawQuery.getColumnIndex("mgrp")));
                message_Pojo.setCond(rawQuery.getInt(rawQuery.getColumnIndex("mcond")));
                message_Pojo.setDatetime(rawQuery.getString(rawQuery.getColumnIndex("created_at")));
                arrayList.add(message_Pojo);
            } while (rawQuery.moveToNext());
            rawQuery.close();
            return arrayList;
        }
        rawQuery.close();
        return arrayList;
    }

    private boolean Exists(String str) {
        boolean z = true;
        Cursor rawQuery = getReadableDatabase().rawQuery("select 1 from chats where chatnames = ?", new String[]{str});
        if (rawQuery.getCount() <= 0) {
            z = false;
        }
        rawQuery.close();
        return z;
    }

    public void closeDB() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null && readableDatabase.isOpen()) {
            readableDatabase.close();
        }
    }

    public void deleteChat(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("chats", "chatnames=?", new String[]{str});
        writableDatabase.delete("msgs", "mgrp=?", new String[]{str});
    }

    public void deleteMsg(String str) {
        getWritableDatabase().delete("msgs", "mmsg=?", new String[]{str});
    }

    public boolean Checkmsg(String str, String str2) {
        boolean z = false;
        Cursor rawQuery = getWritableDatabase().rawQuery("select 1 from chats where chatnames = ? AND lastmsg = ?", new String[]{str, str2});
        if (rawQuery.getCount() > 0) {
            z = true;
        }
        rawQuery.close();
        return z;
    }
}
