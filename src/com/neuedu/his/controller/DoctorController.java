package com.neuedu.his.controller;

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.BaseUser;
import com.neuedu.his.model.Doctor;
import com.neuedu.his.model.Register;
import com.neuedu.his.utils.ContactUtils;

import java.util.ArrayList;

/**
 * 医生视图的控制层
 * @author 孙续洋
 * @date 7/24
 */
public class DoctorController {
    /**
     * 通过科室ID查找医生列表方法
     * @param departmentId
     * @return
     */
    public ArrayList<Doctor> findDoctorsByDid(int departmentId){
        ArrayList<Doctor> list = new ArrayList<>();
        for (Doctor doctor : DataBase.doctorTable){
            ContactUtils.getDay();
            String workplan = doctor.getWorkplan().get(ContactUtils.getDay());
            ContactUtils.getDay();
            if (doctor.getDepartment().getDid()==departmentId && "出诊".equals(workplan)){
                //符合要求
                list.add(doctor);
            }
        }
        return list;
    }

    /**
     * 根据医生工号，遍历查找医生方法
     * @param dr_id
     * @return
     */
    public Doctor findDoctorById(String dr_id){
        Doctor doctor1 = new Doctor();
        //在医生数据库里遍历
        for (Doctor doctor : DataBase.doctorTable){
            if (doctor.getId().equals(dr_id)){
                doctor1 = doctor;
            }
        }
        //返回医生对象
        return doctor1;
    }

    /**
     * 医生登录方法，匹配账号密码方法
     * @param account
     * @param password
     * @return
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
        for (Doctor doctor : DataBase.doctorTable){
            //对比账号和密码
            if (doctor.getAccount().equals(account) && doctor.getPassword().equals(password)){
                //成功
                return 200;
            }
        }
        //登录不成功
        return 0;
    }

    /**
     * 通过医生账号查找医生方法
     * @param account
     * @return
     */
    public Doctor findDoctorByAc(String account){
        Doctor doctor1 = new Doctor();
        for (Doctor doctor : DataBase.doctorTable){
            if (doctor.getAccount().equals(account)){
                doctor1 = doctor;
            }
        }
        return doctor1;
    }


    /**
     * 通过医生工号，科室编号选择医生方法
     * @param dr_id
     * @param dp_id
     * @return
     */
    public int doctorChoose(String dr_id,int dp_id){
        DoctorController doctorController = new DoctorController();
        ArrayList<Doctor> doctorList = doctorController.findDoctorsByDid(dp_id);
        for (Doctor doctor : doctorList){
            if (doctor.getId().equals(dr_id)){
                //成功
                return 200;
            }
        }
        //选择失败
        return 0;
    }

    /**
     * 计算医生当天预约看诊人数方法
     * @param basePatientList
     * @return
     */
    public int diagnosisCount(ArrayList<BasePatient> basePatientList){
        int i=0;
        for (BasePatient basePatient : basePatientList){
            if (basePatient.isDiagnosis()){
                i++;
            }
        }
        return i;
    }
}
