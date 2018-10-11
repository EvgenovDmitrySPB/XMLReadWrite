package com.importio.util;

import com.importio.view.ArticleView;

import java.util.TimerTask;

public class TimerWrite extends TimerTask {
    private static TimerWrite INSTANCE = null;

    public static TimerWrite getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TimerWrite();
        }
        return INSTANCE;
    }

    public TimerWrite() {

    }

    @Override
    public boolean cancel() {
        return super.cancel();
    }

    @Override
    public void run() {
        ArticleView articleView = new ArticleView();
        String path = "src\\main\\resources\\xml\\";
        articleView.writeToXML(path);
        System.out.println("TimerWrite.run");
    }
}
