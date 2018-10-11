package com.importio.util;

import com.importio.view.ArticleView;

import java.util.TimerTask;

public class TimerRead extends TimerTask {
    private static TimerRead INSTANCE = null;

    public static TimerRead getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TimerRead();
        }
        return INSTANCE;
    }

    public TimerRead() {

    }

    @Override
    public boolean cancel() {
        return super.cancel();
    }

    @Override
    public void run() {
        ArticleView articleView = new ArticleView();
        String path = "src\\main\\resources\\xml\\";
        articleView.readfromXML(path + "fileInput.xml");
        System.out.println("TimerRead.run ");
    }
}
