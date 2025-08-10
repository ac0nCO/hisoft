package com.neuedu.his.utils;

import com.neuedu.his.controller.DataBaseController;
import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 文本工具类
 * @author 孙续洋
 * @date:7/23
 */
public class ContactUtils {


    /**
    作用：判断是否为空
     */
    public static boolean isNull(String msg){
        if (msg == null){
            return false;
        }
        if ("".equals(msg.trim())){
            return false;
        }
        return true;
    }

    /**
     * 判断文本是否符合规则
     * 包含字母大写或小写或数字，且长度在4-20范围内
     * @param msg
     * @return
     */
    public static boolean validate(String msg){
        //正则表达式
        String regex = "[a-zA-Z0-9]{4,20}";
        return msg.matches(regex);
    }
    public static int getDay(){
        //日历类
        Calendar calendar = Calendar.getInstance();
        //获取今天是星期几
        //周日1
        //周一2 1
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek -=1;
        if (dayOfWeek==0){
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(date);
        return dateString;
    }

}
