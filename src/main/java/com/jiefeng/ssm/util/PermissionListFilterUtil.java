package com.jiefeng.ssm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionListFilterUtil {


    public static List<Integer> permissionListFilter(List<Integer> permissionList){

        List<Integer> resultList = new ArrayList<>();

        for (Integer item : permissionList) {
            if(item < 1000){
                resultList.add(item);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1010, 1, 2, 3, 4, 1012, 5, 6, 7, 8, 1013, 9, 10, 11, 12, 1014, 13, 14, 1015, 15, 16);
        List<Integer> integers1 = PermissionListFilterUtil.permissionListFilter(integers);
        System.out.println(integers1);
    }
}
