package com.welldig.xping.record;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;

/**
 * Created by xping.zong on 2016/6/16.
 */
public class RecordBean {
    private String date;
    private String ampm;
    private float count;
    private float sum;
    private int duration;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override public String toString() {
        return toString2(date, ampm, String.format("%.1f", count), String.format("%.1f", sum), String.format("%d",
                duration), new DateTime().toString());
    }

    private String toString2(String... a) {
        StringBuilder sb = new StringBuilder();
        for (String s : a) {
            sb.append(s);
            sb.append(",");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return "";
    }

    public RecordBean(String date, String ampm, float count) {
        this.date = date;
        this.ampm = ampm;
        this.count = count;
    }

    public RecordBean(String content) {
        String[] splits = StringUtils.split(content, ",");
        if (splits == null || splits.length < 5) {
            return;
        }
        date = splits[0];
        ampm = splits[1];
        count = NumberUtils.toFloat(splits[2], 0);
        sum = NumberUtils.toFloat(splits[3], 0);
        duration = NumberUtils.toInt(splits[4], 0);
    }
}
