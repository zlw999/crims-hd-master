package com.ireal.crims.alarm.container;

public class DataCache {
    private static class SingletonHolder {
        public static DataCache instance = new DataCache();
    }

    private DataCache() {
    }

    public static DataCache getInstance() {
        return SingletonHolder.instance;
    }

    private int nProtocolRequenceNo = 0;

    public int getProtocolRequenceNo()
    {
        this.nProtocolRequenceNo++;
        return this.nProtocolRequenceNo;
    }
}
