package com.example.cloudinteractive_tangling.data;

public class ContentBean {

    public String strId;
    public String strTitle;
    public String strUrl;
    public String strColor;

    public ContentBean(String strId, String strTitle, String strUrl, String strColor) {
        this.strId = strId;
        this.strTitle = strTitle;
        this.strUrl = strUrl;
        this.strColor = strColor;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrUrl() {
        return strUrl;
    }

    public void setStrUrl(String strUrl) {
        this.strUrl = strUrl;
    }

    public String getStrColor() {
        return strColor;
    }

    public void setStrColor(String strColor) {
        this.strColor = strColor;
    }

}
