package com.example.cloudinteractive_tangling.Data;

public class ContentItem {

    private String tvId,tvTitle,tvthumbnailUrl;

    public ContentItem(String tvId, String tvTitle, String tvthumbnailUrl) {
        this.tvId = tvId;
        this.tvTitle = tvTitle;
        this.tvthumbnailUrl = tvthumbnailUrl;
    }

    public String getTvId() {
        return tvId;
    }

    public void setTvId(String tvId) {
        this.tvId = tvId;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvthumbnailUrl() {
        return tvthumbnailUrl;
    }

    public void setTvthumbnailUrl(String tvthumbnailUrl) {
        this.tvthumbnailUrl = tvthumbnailUrl;
    }

}
