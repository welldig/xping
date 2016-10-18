package com.welldig.xping.time;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import qunar.concurrent.ManagedExecutors;
import qunar.concurrent.ManagedThreadPool;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by xping.zong on 2016/7/22.
 */
public class TimeMain {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat date_Format = new SimpleDateFormat("yyyy-MM-dd");




    public static void main(String[] args) throws InterruptedException, ExecutionException {
        class TimeParse implements Callable<List<String>>  {

            @Override public List<String> call() throws Exception {
                List<String> result = Lists.newArrayList();
                result.add(date_Format.format(dateFormat.parse("20160601")));
//                result.add(date_Format.format(dateFormat.parse("20171123")));
                return result;
            }
        }
        ManagedThreadPool executor = ManagedExecutors.getExecutor();
        List<TimeParse> timeParses = Lists.newArrayList();
        int PROCESS_NUM = 2;
        for (int i = 0; i < PROCESS_NUM; i++) {
            timeParses.add(new TimeParse());
        }
        final CountDownLatch countDownLatch = new CountDownLatch(PROCESS_NUM);

        List<Future<List<String>>> futures = executor.invokeAll(timeParses);
        for (Future<List<String>> future : futures) {
            System.out.println(future.get());
            countDownLatch.countDown();
        }
        countDownLatch.await();
    }
}
