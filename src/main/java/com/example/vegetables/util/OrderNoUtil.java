package com.example.vegetables.util;

import java.util.Date;
import java.util.Random;

public class OrderNoUtil {
    /**
     * 基础编码表
     */
    public static final String BASIC_CODE = "abcdefghfkvmnrqtuwxy745368ABCDEFHJKLMNKQTUVWXY";
    public static String getCode(Date date){

        try {
            //年份的后两位
            Integer yy = DateUtil1.getYY(date);
            StringBuilder year = new StringBuilder();
            year.append(yy);
            String subYear = year.substring(2, 4);
            //两位月份
            String month = DateUtil1.getMonth(date)+"";
            if(month.length()<2){
                month = "0"+month;
            }
            //随机组合的六位
            Random random=new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <6 ; i++) {
                int num = random.nextInt(BASIC_CODE.length());
                sb.append(BASIC_CODE.charAt(num));
            }
            StringBuilder code = new StringBuilder();
            code.append(subYear).append(month).append(sb);
            return code.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
