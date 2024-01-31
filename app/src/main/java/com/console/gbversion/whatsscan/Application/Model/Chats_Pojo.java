package com.console.gbversion.whatsscan.Application.Model;

/* loaded from: classes2.dex */
public class Chats_Pojo {
    private String chat_name;
    private int cond;
    private String datetime;
    private String last_msg;

    public Chats_Pojo(String str, int i, String str2, String str3) {
        this.chat_name = str;
        this.cond = i;
        this.datetime = str2;
        this.last_msg = str3;
    }

    public Chats_Pojo() {
    }

    public String getLastmsg() {
        return this.last_msg;
    }

    public void setLastmsg(String str) {
        this.last_msg = str;
    }

    public String getName() {
        return this.chat_name;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setName(String str) {
        this.chat_name = str;
    }

    public void setDatetime(String str) {
        this.datetime = str;
    }

    public int getCond() {
        return this.cond;
    }

    public void setCond(int i) {
        this.cond = i;
    }
}
