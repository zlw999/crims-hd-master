package com.ireal.crims.nivm.syncagent.container;

public class DataCache {
    private static class SingletonHolder {
        public static DataCache instance = new DataCache();
    }

    private DataCache() {

    }
    public static DataCache getInstance() {
        return SingletonHolder.instance;
    }
}
