package com.neuedu.his.controller;


import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.DrInpatient;
import com.neuedu.his.utils.ContactUtils;

/**
 * 住院部医生操作类
 * 作者：王思汉
 * date：7/25
 */
public class DrInpatientController {
    public int login(String account,String password){
        //住院部护士登录系统
        if(!ContactUtils.isNull(account)||!ContactUtils.isNull(password)){
            return -1;
            //判断输入的字符串是否为空或全是空格
        }
        if(!ContactUtils.validate(account)||!ContactUtils.validate(password)){
            return -2;
            //判断是否由字母及数字组成，并且长度为4--20
        }
        for(DrInpatient drInpatient: DataBase.drInpatientTable){
            if(drInpatient.getAccount().equals(account)&&drInpatient.getPassword().equals(password)){
                return 200;
                //对比判断
            }
        }
        return 0;
        //与数据库对比是否正确
    }
}
