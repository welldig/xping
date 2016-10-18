package com.welldig.xping;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xping.zong
 * Date: 2016/9/7
 * Time: 17:39
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        Map<String, Set<String>> stringSetMap = parseMapSetConfig("23:a,b,c;222:b,c");
        System.out.println(stringSetMap);

    }

    public static Map<String, Set<String>> parseMapSetConfig(final String config) {
        Map<String, Set<String>> result = Maps.newHashMap();
        if (config == null) {
            return result;
        }
        String[] oneItems = StringUtils.split(config, ";");
        for (String oneItem : oneItems) {
            String[] twoItems = StringUtils.split(oneItem, ":");
            if (twoItems.length != 2) {
                continue;
            }
            result.put(twoItems[0], Sets.newHashSet(StringUtils.split(twoItems[1], ",")));
        }
        return result;
    }
}
