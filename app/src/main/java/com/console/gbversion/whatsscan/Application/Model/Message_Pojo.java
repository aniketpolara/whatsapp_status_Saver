package com.console.gbversion.whatsscan.Application.Model;

/* loaded from: classes2.dex */
public class Message_Pojo {
    private int cond;
    private String datetime;
    private String grp;
    private String msg;
    private String name;

    public Message_Pojo(String str, int i, String str2, String str3, String str4) {
        this.name = str;
        this.cond = i;
        this.grp = str2;
        this.msg = str3;
        this.datetime = str4;
    }

    public Message_Pojo() {
    }

    public String getName() {
        return this.name;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String str) {
        this.datetime = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getCond() {
        return this.cond;
    }

    public void setCond(int i) {
        this.cond = i;
    }

    public String getGrp() {
        return this.grp;
    }

    public void setGrp(String str) {
        this.grp = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
