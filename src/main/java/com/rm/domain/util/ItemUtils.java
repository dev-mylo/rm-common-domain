package com.rm.domain.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUtils {

    public static Map<String, Object> getItemList(Integer totalCnt, List itemList) {

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("itemList", itemList);
        resultMap.put("itemCnt", totalCnt);

        return resultMap;
    }
}
