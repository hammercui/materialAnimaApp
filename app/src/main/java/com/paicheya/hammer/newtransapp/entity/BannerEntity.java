package com.paicheya.hammer.newtransapp.entity;

/**
 * Created by cly on 17/2/1.
 */

public class BannerEntity {
    private String title;

    private String href;

    private String img;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setHref(String href){
        this.href = href;
    }
    public String getHref(){
        return this.href;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
}
