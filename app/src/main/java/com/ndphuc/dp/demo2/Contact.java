package com.ndphuc.dp.demo2;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class Contact {
    private String id,name,avatar,about;
    private Date birthDay;

    public Contact(String id, String name, String avatar, String about, Date birthDay) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.about = about;
        this.birthDay = birthDay;
    }

    public Contact() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
