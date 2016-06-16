package com.welldig.xping.record;

import com.google.common.collect.Lists;
import com.welldig.xping.common.AbstractFileService;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xping.zong on 2016/6/15.
 */
public class RecordService extends AbstractFileService {

    public void record(String ampm, float count) {

        String path="E:/welldig/xping/record/src/main/resources/welldig.txt";
        File file = new File(path);
        List<String> datas = readFromFile(file);
        if (datas == null || datas.size() < 2) {
            return;
        }
        RecordBean firstBean = new RecordBean(datas.get(1));
        RecordBean lastBean = new RecordBean(datas.get(datas.size() - 1));

        RecordBean recordBean = new RecordBean(new DateTime().toString("yyyy-MM-dd"), ampm, count);
        recordBean.setSum(lastBean.getSum() + count);
        recordBean.setDuration(Days.daysBetween(new DateTime(firstBean.getDate()), DateTime.now()).getDays());
        ArrayList<Object> objects = Lists.newArrayList();
        objects.add(recordBean);
        append(file, objects);
    }

    public static void main(String[] args) {
//        new RecordService().record("am", 0.5f);
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }
}
