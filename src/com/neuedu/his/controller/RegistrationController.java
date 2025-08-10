package com.neuedu.his.controller;

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.*;
import com.neuedu.his.utils.ContactUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * 挂号窗口的业务控制层
 * @author 孙续洋
 * @date 7/23
 */
public class RegistrationController {

    /**
     * 挂号窗口的正在判断登陆是否成功的方法
     * -1：都是空格
     * -2：没有按规则 字母，长度不合法
     * 200：登录成功
     * 0：登录不成功 账号和密码
     */
    public int login(String account , String password){
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
        for (Register register : DataBase.registerTable){
            //对比账号和密码
            if (register.getAccount().equals(account) && register.getPassword().equals(password)){
                //成功
                return 200;
            }
        }
        //登录不成功
        return 0;
    }

    /**
     * 挂号系统录入患者信息的方法
     * @date 7/23
     * @param basePatient
     * @throws IOException
     */
    public boolean inputInfo(BasePatient basePatient, String dir,String dr_id,int dp_id) throws IOException {
       DataBaseController dataBaseController = new DataBaseController();
       DoctorController doctorController = new DoctorController();
       PatientController patientController = new PatientController();
        DepartmentController departmentController = new DepartmentController();
       boolean isExist = false;
       for (BasePatient basePatient1 : DataBase.basePatientTable){
           if (basePatient1.getName().equals(basePatient.getName()) && basePatient1.getAge().equals(basePatient.getAge()) && basePatient1.getSex().equals(basePatient.getSex())){
                isExist = true;
           }
       }
       if (!isExist){
           basePatient.setPersonalStatement("None");
           basePatient.setDiagnosis("None");
           basePatient.setDiagnosis(false);
           basePatient.setInpatientT(0);
           HashMap<Medicine,Integer> medicineList = new HashMap<>();
           for (Medicine medicine : DataBase.medicineTable){
               medicineList.put(medicine,0);
           }
           basePatient.setMedicine(medicineList);
           HashMap<Test,String> testList = new HashMap<>();
           for (Test test : DataBase.testTable){
               testList.put(test,"None");
           }
           basePatient.setTest(testList);
       }
       basePatient.setDepartment(departmentController.findDepartmentById(dp_id));
       basePatient.setDoctor(doctorController.findDoctorById(dr_id));
       basePatient.setId(String.valueOf(DataBase.basePatientTable.size()+1));
       boolean a = dataBaseController.db_Patient_wri(basePatient,dir,true);
       DataBase.db_PatientUD();
       return a;
    }
}
