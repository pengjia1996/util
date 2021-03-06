package com.xinyu.comparator;

import java.util.Comparator;

import com.xinyu.str.ExtractContentEnum;
import com.xinyu.str.StrUtil;


public class RoomComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        // 20202-1-0<20202-1-1
        int orders1 = Integer.parseInt(StrUtil.extractContent(o1, ExtractContentEnum.NUMBER));
        int orders2 = Integer.parseInt(StrUtil.extractContent(o2, ExtractContentEnum.NUMBER));
        return orders1 - orders2;
    }

}



