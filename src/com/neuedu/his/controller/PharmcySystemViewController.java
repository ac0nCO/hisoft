package com.neuedu.his.controller;
/**
 * @date 07-26
 * author：钱程
 * 药房登录的判断
 */

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.PharmacyDr;
import com.neuedu.his.utils.ContactUtils;
import com.neuedu.his.view.PharmcySystemView;

 public class PharmcySystemViewController {
    /**
     * 药房窗口的判断是否成功的方法
     *-1:都是空格或者null
     * -2:没有按规则字母，长度违规
     * 200:登录成功
     * 0:登录不成功
     * @return
     */
    public int login(String account, String password){
        //附加功能
        //判断输入字符串是否空，是否都是空格
        if(!ContactUtils.isNull(account)||!ContactUtils.isNull(password)){
            //不合法，结束
            return -1;
        }
        //判断是否由字母以及数字组成，并且长度2-30
        if (!ContactUtils.validate(account)||!ContactUtils.validate(password)){
            //
            return -2;
        }
        for(PharmacyDr pharmacyDr: DataBase.pharmacyDrTable){
            //对比账号和密码
            if(pharmacyDr.getAccount().equals(account)&&pharmacyDr.getPassword().equals(password)){
                return 200;
            }
        }

        //与数据库对比账号和密码是否正确
        return 0;
    }
}