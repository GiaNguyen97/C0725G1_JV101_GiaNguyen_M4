package org.example.mail_settings.entity;

public class MailSettings {
    private String language;
    private int pageSize;
    private boolean spamFilter;
    private String signature;

    public MailSettings() {
    }

    public MailSettings(String language, int pageSize, boolean spamFilter, String signature) {
        this.language = language;
        this.pageSize = pageSize;
        this.spamFilter = spamFilter;
        this.signature = signature;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSpamFilter() {
        return spamFilter;
    }

    public void setSpamFilter(boolean spamFilter) {
        this.spamFilter = spamFilter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getLanguageCode() {
        return switch (language) {
            case "Vietnamese" -> "vi";
            case "Japanese" -> "ja";
            case "Chinese" -> "zh";
            default -> "en";
        };
    }
}
