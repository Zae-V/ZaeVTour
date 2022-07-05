package com.example.zaevtour;

public class ListViewItem {

    private String firstImage;
    private String title;
    private String address;
    private String tel;
    private String contentId;
    private String readCount;
    double mapx;
    double mapy;

    public String getfirstImage() {
        return firstImage;
    }

    public void setfirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }

    public void setContentId(String contentId){this.contentId = contentId;}

    public String getContentId(){return contentId;}

    public void setReadCount(String readCount){this.readCount = readCount;}

    public String getReadCount(){return readCount;}


}
