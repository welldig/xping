package com.welldig.xping.common;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by xping.zong on 2016/6/15.
 */
public abstract class AbstractFileService {

    protected List<String> readFromFile(File file) {
        try {
            return Files.readLines(file, Charsets.UTF_8, new LineProcessor<List<String>>() {
                List<String> result = Lists.newArrayList();
                int lineNo = 0;

                public boolean processLine(String line) throws IOException {
                    lineNo++;
                    boolean isProcess = isProcess(line, lineNo);
                    if (isProcess) {
                        result.add(transformLine(line, lineNo));
                    }
                    return isProcess;
                }

                @Override public List<String> getResult() {
                    return result;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String transformLine(String line, int lineNo) {
        return line.trim();
    }

    protected boolean isProcess(String line, int lineNo) {
        return StringUtils.isNotBlank(line);
    }

    protected void write(File file, List<Object> data) {
        save(file, data, "\n", false);
    }

    protected void append(File file, List<Object> data) {
        save(file, data, "\n", true);
    }

    protected void save(File file, List<Object> data, String joinChar, boolean isAppend) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }
        try {
            if (isAppend) {
                Files.append(data.get(0).toString(), file, Charsets.UTF_8);
            } else {
                Files.write(data.get(0).toString(), file, Charsets.UTF_8);
            }
            for (int i = 1; i < data.size(); i++) {
                Files.append(joinChar, file, Charsets.UTF_8);
                Files.append(data.get(i).toString(), file, Charsets.UTF_8);
            }
            Files.append(joinChar, file, Charsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
