package com.xufangfang.o2o.util;

/**
 *
 */
public class PageCalculator {
    /**
     * 1 10  0,10
     * 2 10  10,10个
     * 0 10  0
     * @param pageIndex
     * @param pageSize
     * @return
     */

    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
