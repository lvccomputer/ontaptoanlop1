package com.dev.lvc.math1.models;

public class Practice {

    private String icon;

    private int idPractice;

    private String folderImage;

    private String titlePractice;


    public Practice() {
    }

    public int getIdPractice() {
        return idPractice;
    }

    public void setIdPractice(int idPractice) {
        this.idPractice = idPractice;
    }

    public String getTitlePractice() {
        return titlePractice;
    }

    public void setTitlePractice(String titlePractice) {
        this.titlePractice = titlePractice;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFolderImage() {
        return folderImage;
    }

    public void setFolderImage(String folderImage) {
        this.folderImage = folderImage;
    }
}
