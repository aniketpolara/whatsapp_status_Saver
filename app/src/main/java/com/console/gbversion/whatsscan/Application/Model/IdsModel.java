package com.console.gbversion.whatsscan.Application.Model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes2.dex */
public class IdsModel {
    @SerializedName("data")
    private Data data;
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    private String msg;
    @SerializedName("StatusCode")
    private String statusCode;

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getMsg() {
        return this.msg;
    }

    /* loaded from: classes2.dex */
    public class Data {
        @SerializedName("Admob_Banner")
        private String admobBanner;
        @SerializedName("Admob_Interstitial")
        private String admobInterstitial;
        @SerializedName("Admob_native")
        private String admobNative;
        @SerializedName("Admob_OpenAd")
        private String admobOpenAd;
        @SerializedName("ins_Counter")
        private String insCounter;
        @SerializedName("isActive")
        private boolean isActive;
        @SerializedName("isBackIns")
        private boolean isBackIns;
        @SerializedName("is_exit_screen_ad_show")
        private boolean isExitScreenAdShow;
        @SerializedName("is_first_screen_show")
        private boolean isFirstScreenShow;
        @SerializedName("isReviewDailog")
        private boolean isReviewDailog;
        @SerializedName("is_screen_show")
        private boolean isScreenShow;
        @SerializedName("is_second_screen_show")
        private boolean isSecondScreenShow;
        @SerializedName("new_app_url")
        private String newAppUrl;
        @SerializedName("Privacy")
        private String privacy;
        @SerializedName("show_app_new_dialog")
        private boolean showAppNewDialog;

        public Data() {
        }

        public void setAdmobBanner(String str) {
            this.admobBanner = str;
        }

        public String getAdmobBanner() {
            return this.admobBanner;
        }

        public void setAdmobInterstitial(String str) {
            this.admobInterstitial = str;
        }

        public String getAdmobInterstitial() {
            return this.admobInterstitial;
        }

        public void setAdmobOpenAd(String str) {
            this.admobOpenAd = str;
        }

        public String getAdmobOpenAd() {
            return this.admobOpenAd;
        }

        public void setAdmobNative(String str) {
            this.admobNative = str;
        }

        public String getAdmobNative() {
            return this.admobNative;
        }

        public void setPrivacy(String str) {
            this.privacy = str;
        }

        public String getPrivacy() {
            return this.privacy;
        }

        public void setInsCounter(String str) {
            this.insCounter = str;
        }

        public String getInsCounter() {
            return this.insCounter;
        }

        public void setIsActive(boolean z) {
            this.isActive = z;
        }

        public boolean isIsActive() {
            return this.isActive;
        }

        public void setIsBackIns(boolean z) {
            this.isBackIns = z;
        }

        public boolean isIsBackIns() {
            return this.isBackIns;
        }

        public void setIsReviewDailog(boolean z) {
            this.isReviewDailog = z;
        }

        public boolean isIsReviewDailog() {
            return this.isReviewDailog;
        }

        public void setIsExitScreenAdShow(boolean z) {
            this.isExitScreenAdShow = z;
        }

        public boolean isIsExitScreenAdShow() {
            return this.isExitScreenAdShow;
        }

        public void setIsFirstScreenShow(boolean z) {
            this.isFirstScreenShow = z;
        }

        public boolean isIsFirstScreenShow() {
            return this.isFirstScreenShow;
        }

        public void setIsScreenShow(boolean z) {
            this.isScreenShow = z;
        }

        public boolean isIsScreenShow() {
            return this.isScreenShow;
        }

        public void setIsSecondScreenShow(boolean z) {
            this.isSecondScreenShow = z;
        }

        public boolean isIsSecondScreenShow() {
            return this.isSecondScreenShow;
        }

        public void setNewAppUrl(String str) {
            this.newAppUrl = str;
        }

        public String getNewAppUrl() {
            return this.newAppUrl;
        }

        public void setShowAppNewDialog(boolean z) {
            this.showAppNewDialog = z;
        }

        public boolean isShowAppNewDialog() {
            return this.showAppNewDialog;
        }
    }
}
