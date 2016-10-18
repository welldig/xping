package com.welldig.xping.file;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.welldig.xping.common.AbstractFileService;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xping.zong
 * Date: 2016/9/7
 * Time: 19:23
 * Description:
 */
public class PolicyFileService extends AbstractFileService {
    protected String transformLine(String line, int lineNo) {
        int split = 0;
        if (lineNo %2 == 0) {
            split = 3;
            return line.split("\t")[split];
        } else {
            split = 4;
            return line.split("\t")[split].split(" ")[1];
        }
    }

    public static void main(String[] args) {
        File file = new File("E:/welldig/xping/record/src/main/resources/policy.txt");
        PolicyFileService service = new PolicyFileService();
        List<String> list = service.readFromFile(file);
        Map<String, Set<String>> map= Maps.newHashMap();
        for (int i = 0; i < list.size(); i++) {
            if (i%2 == 1) {
                String ip = list.get(i);
                Set<String> datas = map.get(ip);
                if (datas == null) {
                    datas = Sets.newHashSet();
                    map.put(ip, datas);
                }
                datas.add(list.get(i-1));
            }
        }
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
