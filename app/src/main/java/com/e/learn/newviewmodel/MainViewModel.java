package com.e.learn.newviewmodel;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private int clickCountA=0, clickCountB = 0;

    public int getInitialCountA() {

        return clickCountA;
    }

    public int getInitialCountB() {

        return clickCountB;
    }

    public int getCurrentCountA() {
        clickCountA++;
        return clickCountA;
    }

    public int getCurrentCountB() {

        clickCountB++;
        return clickCountB;
    }
}
