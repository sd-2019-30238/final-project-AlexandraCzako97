package com.example.finalassignment_ps.model;

import android.graphics.RectF;

public class Key {

    public int sound;
    public RectF rectangle;
    public boolean down;

    public Key(RectF rectangle, int sound) {
        this.sound = sound;
        this.rectangle = rectangle;
    }
}