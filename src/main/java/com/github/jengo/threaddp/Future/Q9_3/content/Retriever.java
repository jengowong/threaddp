package com.github.jengo.threaddp.Future.Q9_3.content;

public class Retriever {
    public static Content retrieve(String urlstr) {
        return new SyncContentImpl(urlstr);
    }
}
