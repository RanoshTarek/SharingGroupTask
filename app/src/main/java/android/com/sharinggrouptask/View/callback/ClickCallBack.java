package android.com.sharinggrouptask.View.callback;

import android.com.sharinggrouptask.db.entity.User;

/******************************************************************************
 * Module: ClickCallBack
 *
 * File Name: ClickCallBack.java
 *
 * Description: Source file for Click Call Back
 *
 * Author: Rana Tarek
 ******************************************************************************/
public interface ClickCallBack {
    /*interface fun to handle row click in adapter*/
    void onClick(User user);
}
