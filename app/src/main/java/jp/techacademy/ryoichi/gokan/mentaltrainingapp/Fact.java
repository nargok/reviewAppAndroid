package jp.techacademy.ryoichi.gokan.mentaltrainingapp;

import com.google.gson.annotations.Expose;

/**
 * Created by houxianliangyi on 2017/08/15.
 */

public class Fact {
    public Fact(){};

    public Fact(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Expose
    private int id;

    @Expose
    private String title;

    @Expose
    private String content;

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
