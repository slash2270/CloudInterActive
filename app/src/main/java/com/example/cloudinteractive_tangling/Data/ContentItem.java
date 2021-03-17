package com.example.cloudinteractive_tangling.Data;

public class ContentItem {

    private String strId;
    private String strTitle;
    private String strthumbnailUrl;
    private String strThumColor;

    public ContentItem(String strId, String strTitle, String strthumbnailUrl, String strThumColor) {
        this.strId = strId;
        this.strTitle = strTitle;
        this.strthumbnailUrl = strthumbnailUrl;
        this.strThumColor = strThumColor;
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

    public String getStrthumbnailUrl() {
        return strthumbnailUrl;
    }

    public void setStrthumbnailUrl(String strthumbnailUrl) {
        this.strthumbnailUrl = strthumbnailUrl;
    }

    public String getStrThumColor() {
        return strThumColor;
    }

    public void setStrThumColor(String strThumColor) {
        this.strThumColor = strThumColor;
    }

}
