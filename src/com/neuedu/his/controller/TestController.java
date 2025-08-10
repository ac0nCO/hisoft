package com.neuedu.his.controller;

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.ExamDr;
import com.neuedu.his.model.Test;
import com.neuedu.his.utils.ContactUtils;

import java.util.ArrayList;


/**
 * 化验检查控制层
 * @author 周俊泽
 */
public class TestController {
    /**
     * 通过编号读取检查方法
     * @author 孙续洋
     * @param id
     * @return
     */
    public Test findTestById(String id){
        Test test1 = new Test();
        test1.setId("-200");
        for (Test test : DataBase.testTable){
            if (test.getId().equals(id)){
                return test;
            }
        }
        return test1;
    }


    /**
     * 显示需要检查的患者列表方法
     * @author 孙续洋
     * @return
     */
    public ArrayList<BasePatient> testPTList(){
        ArrayList<BasePatient> basePatients = new ArrayList<>();
        BasePatient basePatient1 = new BasePatient();
        int b = 0;
        for (BasePatient basePatient : DataBase.basePatientTable){
            int a = 0;
            for (Test test : DataBase.testTable){
                if (!("None".equals(basePatient.getTest().get(test)))){
                    a++;
                    b++;
                }
            }
            if (a!=0){
                basePatients.add(basePatient);
            }
        }
        if (b!=0){
            return basePatients;
        }
        basePatient1.setId("-200");
        basePatients.add(basePatient1);
        return basePatients;
    }


    /**
     * 检查医生登录方法
     * @author 周俊泽
     * @param account
     * @param password
     * @return
     */
    public int login(String account,String password){
        //判断输入字符串是否空，是否都是空格
        if(!ContactUtils.isNull(account)||!ContactUtils.isNull(password)){
            //不合法，结束
            return -1;
        }
        //判断是否由字母以及数字组成，并且长度4-8
        if (!ContactUtils.validate(account)||!ContactUtils.validate(password)){
            //
            return -2;
        }

        //与数据库对比账号和密码是否正确
        for (ExamDr examDr:DataBase.examDrTable) {
            //对比账号密码
            if (examDr.getAccount().equals(account)&&examDr.getPassword().equals(password)){
                //成功
                return 200;
            }
        }
        //登录不成功
        return 0;


    }
}
