package com.example.administrator.xmlpulltest2;

import java.io.Serializable;

/**
 * Created by Administrator on 16.1.16.
 */
public class River implements Serializable {
    String riverName;
    int riverLength;
    String riverAbout;

    public int getRiverLength() {
        return riverLength;
    }

    public void setRiverLength(int riverLength) {
        this.riverLength = riverLength;
    }

    public String getRiverAbout() {
        return riverAbout;
    }

    public void setRiverAbout(String riverAbout) {
        this.riverAbout = riverAbout;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }
}
