package com.example.demoauthorization.request;

import java.util.List;




public class ContentAnnouncementCreateRequest {
    private Boolean isImportant;
     private String title;

//    private String url;
    private List<String> roles;
     private String content;
    private Boolean isSent;
    private Boolean isUsingExcel;
    private Long publishedDate;
    private String englishTitle;
    private String englishContent;
    private Long contentAnnouncementTypeId;
    private Long expiredAt;
    private String illustrationUrl;
    private Boolean isAllAreas;

    public ContentAnnouncementCreateRequest() {
    }

    public ContentAnnouncementCreateRequest(Boolean isImportant, String title, List<String> roles, String content, Boolean isSent, Boolean isUsingExcel, Long publishedDate, String englishTitle, String englishContent, Long contentAnnouncementTypeId, Long expiredAt, String illustrationUrl, Boolean isAllAreas) {
        this.isImportant = isImportant;
        this.title = title;
        this.roles = roles;
        this.content = content;
        this.isSent = isSent;
        this.isUsingExcel = isUsingExcel;
        this.publishedDate = publishedDate;
        this.englishTitle = englishTitle;
        this.englishContent = englishContent;
        this.contentAnnouncementTypeId = contentAnnouncementTypeId;
        this.expiredAt = expiredAt;
        this.illustrationUrl = illustrationUrl;
        this.isAllAreas = isAllAreas;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }

    public Boolean getUsingExcel() {
        return isUsingExcel;
    }

    public void setUsingExcel(Boolean usingExcel) {
        isUsingExcel = usingExcel;
    }

    public Long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getEnglishContent() {
        return englishContent;
    }

    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }

    public Long getContentAnnouncementTypeId() {
        return contentAnnouncementTypeId;
    }

    public void setContentAnnouncementTypeId(Long contentAnnouncementTypeId) {
        this.contentAnnouncementTypeId = contentAnnouncementTypeId;
    }

    public Long getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Long expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getIllustrationUrl() {
        return illustrationUrl;
    }

    public void setIllustrationUrl(String illustrationUrl) {
        this.illustrationUrl = illustrationUrl;
    }

    public Boolean getAllAreas() {
        return isAllAreas;
    }

    public void setAllAreas(Boolean allAreas) {
        isAllAreas = allAreas;
    }
}
