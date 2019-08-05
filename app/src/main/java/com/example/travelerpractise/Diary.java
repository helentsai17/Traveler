package com.example.travelerpractise;

import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;

public class Diary {

    private String mDiaryText;
    private HashMap<String,String> ImageSet;

    public String getmDiaryText() {
        return mDiaryText;
    }

    public void setmDiaryText(String mDiaryText) {
        this.mDiaryText = mDiaryText;
    }

    public HashMap<String, String> getImageSet() {
        return ImageSet;
    }

    public void setImageSet(HashMap<String, String> imageSet) {
        ImageSet = imageSet;
    }

    public Diary() {
    }

    public Diary(String mDiaryText, HashMap<String, String> imageSet) {
        this.mDiaryText = mDiaryText;
        ImageSet = imageSet;
    }
}
