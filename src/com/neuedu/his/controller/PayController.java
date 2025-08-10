package com.neuedu.his.controller;



import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.Charger;
import com.neuedu.his.model.Test;
import com.neuedu.his.utils.ContactUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayController {
    public static int login(String account, String password){
        //判断账号密码是否正确匹配
        //附加功能：
        //判断输入的字符串是否为空，获取是否都是空格
        if (!ContactUtils.isNull(account)||!ContactUtils.isNull(password)){
            //不合格，结束该方法
            return -1;
        }
        //判断是否是字母以及数字组成，并且长度4-20
        if (!ContactUtils.validate(account)||!ContactUtils.validate(password)){
            return -2;
        }
        //与数据库对比账号密码是否正确
        for (Charger charger: DataBase.chargerTable){
            //对比账号和密码
            if (charger.getAccount().equals(account) && charger.getPassword().equals(password)){
                //成功
                return 200;
            }
        }
        //登录不成功
        return 0;
    }
    public int Pay(String id){
        PatientController patientController=new PatientController();
        BasePatient basePatient1=patientController.patientChoose(id,DataBase.basePatientTable);
        int TotalPay=0;
        HashMap<Test,String>testList = basePatient1.getTest();
            for (Map.Entry<Test,String> entry: testList.entrySet()) {
                String testState= entry.getValue();
                if (!(testState.equals("待检查")) && !(testState.equals("None"))) {
                    TotalPay += entry.getKey().getPrice();
                } else {
                    TotalPay += 0;
                }
            }
        TotalPay+=((basePatient1.getInpatientT())*200);

        return TotalPay;
    }
}
