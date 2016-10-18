package com.welldig.xping.file;

import com.google.common.base.Joiner;
import com.welldig.xping.common.AbstractFileService;

import java.io.File;
import java.util.List;

/**
 * Created by xping.zong on 2016/8/16.
 */
public class SqlFileService extends AbstractFileService {
    public static void main(String[] args) {
        File file = new File("E:/welldig/xping/record/src/main/resources/xx.txt");
        SqlFileService service = new SqlFileService();
        List<String> list = service.readFromFile(file);
        String join = Joiner.on("','").join(list);
        System.out.println("('" + join + "')");
    }


}
