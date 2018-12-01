package com.filc.czatprojekt;

import android.support.annotation.NonNull;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

class TimestampConverter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");


    static long getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis()).getTime();
    }

    static String convertTimeStamp(@NonNull long timestamp) {
        Date date = new Date(timestamp);
        return sdf.format(date);
    }
}

