package com.welldig.xping.file;

import com.welldig.xping.common.AbstractFileService;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by xping.zong on 2016/7/28.
 */
public class FileFilterService extends AbstractFileService {
    public static void main(String[] args) {
        File file = new File("E:/welldig/xping/record/src/main/resources/xx.txt");
        FileFilterService service = new FileFilterService();
        List<String> list = service.readFromFile(file);
        for (String s : list) {
            System.out.println(s);
        }
    }

    protected String transformLine(String line, int lineNo) {
        String[] split = StringUtils.split(line, "\"");
        if (split != null && split.length > 2) {
            return split[0] + "XConstant." + toCamelUpper(split[1]) + line.substring(split[0].length() + split[1]
                    .length() + 2);
        }
        return line;
    }

    private String toCamelUpper(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        char[] result = new char[s.length() * 2];
        int i = 0;
        for (char c:s.toCharArray()){
            if (c >= 'a' && c <= 'z') {
                result[i] = (char) (c - 'a' + 'A');
            } else if (c >= 'A' && c <= 'Z') {
                result[i] = '_';
                i ++ ;
                result[i] = c;
            } else {
                result[i] = c;
            }
            i ++ ;
        }
        char[] xx = new char[i];
        for (int j = 0; j < i; j++) {
            xx[j] = result[j];
        }
        return String.valueOf(xx);
    }
}
