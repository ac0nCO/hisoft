package com.neuedu.his.controller;



import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.SickBed;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 病床操作类
 * author：王思汉
 * date：7/26
 */
public class SickBedController {
    DataBaseController dataBaseController=new DataBaseController();
    //定义查找病床操作层
    public String findTheSickbed(String number) throws IOException {
        SickBedController sickBedController = new SickBedController();
        for (SickBed sickBed : DataBase.sickBedTable) {
            if (sickBed.getNumber().equals(number)) {
                //找到对应床位
                if ("0".equals(sickBed.getState())) {
                    return "***【该床位为空，请重新输入】***";
                    //判断
                } else {
                    DataBase.db_SickbedUD();
                    //实时更新数据
                    String info="患者姓名："+sickBed.getBasePatient().getName()+"  "+
                            "患者id："+sickBed.getBasePatient().getId()+"  "+
                            "患者年龄："+sickBed.getBasePatient().getAge()+"  "+
                            "患者科室："+sickBed.getBasePatient().getDepartment()+"  "+
                            "患者性别："+sickBed.getBasePatient().getSex()+"  "+
                            "剩余时间："+sickBed.getTime()+"days";
                    return info;
                    //返回该病床对应的患者信息
                }
            }
        }
        return "2";
    }
//定义判断床位是否为空操作层
    public boolean isNull(String choose) {
        SickBedController sickBedController = new SickBedController();
        for (SickBed sickBed : DataBase.sickBedTable) {
            if (sickBed.getNumber().equals(choose)) {
                if ("0".equals(sickBed.getState())) { return true; }
                else { return false; }
            }
        }
        return false;
    }
    //定义取消病床操作层
    public String deleteSickbed(String choose,String id) throws IOException {
        //ArrayList<SickBed> sickBedList=new ArrayList<>();
        PatientController basePatientController=new PatientController();
        BasePatient basePatient=basePatientController.patientChoose(id,DataBase.basePatientTable);
        if(!isNull(choose)){
            DataBase.sickBedTable.get(Integer.parseInt(choose)).setState("0");
            //将病床状态改为空
            DataBase.sickBedTable.get(Integer.parseInt(choose)).setTime("0");
            //将病床占用时间改为0
            dataBaseController.db_Sickbed_fix(DataBase.sickBedTable.get(Integer.parseInt(choose)));
            //将内存上的操作确实改到本地内存库上
            dataBaseController.db_Patient_fix(basePatient);
            //更新患者数据库
            DataBase.db_SickbedUD();
            //实时更新数据
             return "***【取消成功】***";
        }
        else {return"***【该床位为空,请重新输入】***";}
    }
    public BasePatient FindPatientInfoByID(String choose){
        ArrayList<SickBed> sickBedList=new ArrayList<>();
        return sickBedList.get(Integer.parseInt(choose)).getBasePatient();
    }
    //定义修改病床操作层
    public String changeSickbed(String choose,String id,String after) throws IOException {
        PatientController basePatientController=new PatientController();
        BasePatient basePatient=basePatientController.patientChoose(id,DataBase.basePatientTable);
        //BasePatient aftersickbed= FindPatientInfoByID(choose);
        if(!isNull(choose)&&isNull(after)){//通过find方法先找到choose所对应的床位再.basepatient.getid
            DataBase.sickBedTable.get(Integer.parseInt(choose)).setState("0");
            //将病床状态改为空
            DataBase.sickBedTable.get(Integer.parseInt(after)).setState("1");
            //将之后病床状态改为占用
            DataBase.sickBedTable.get(Integer.parseInt(after)).setTime(DataBase.sickBedTable.get(Integer.parseInt(choose)).getTime());
            //将上一个病床的时间继承到新的病床上
            DataBase.sickBedTable.get(Integer.parseInt(choose)).setTime("0");
            //将之前的病床时间改为零
            DataBase.sickBedTable.get(Integer.parseInt(after)).setBasePatient(basePatient);
            //将之前的病人信息继承到新床上
            dataBaseController.db_Sickbed_fix(DataBase.sickBedTable.get(Integer.parseInt(choose)));
            //将内存上的操作确实改到本地内存库上
            dataBaseController.db_Sickbed_fix(DataBase.sickBedTable.get(Integer.parseInt(after)));
            //将内存上的操作确实改到本地内存库上
            basePatient.setInpatientT(Integer.parseInt(DataBase.sickBedTable.get(Integer.parseInt(choose)).getTime()));
            //更新时间
            dataBaseController.db_Patient_fix(basePatient);
            //更新患者数据库
            DataBase.db_SickbedUD();
            //实时更新数据
            return "***【换床成功】***";
        }
        else { return "***【无效操作】***";}
    }
    //定义增添病床操作层
    public String additionSickbed(String choose,String id,String time) throws IOException {
        PatientController basePatientController=new PatientController();
        BasePatient basePatient=basePatientController.patientChoose(id,DataBase.basePatientTable);
        if (isNull(choose)){
            DataBase.sickBedTable.get(Integer.parseInt(choose)).setState("1");
            //将该床位状态改为占用
            DataBase.sickBedTable.get(Integer.parseInt(choose)).setBasePatient(basePatient);
            //将病人信息放到常床位中
            DataBase.sickBedTable.get(Integer.parseInt(choose)).setTime(time);
            //将住院时长放到床位上
            dataBaseController.db_Sickbed_fix(DataBase.sickBedTable.get(Integer.parseInt(choose)));
            //将内存上的操作确实改到本地内存库上
            DataBase.db_SickbedUD();
            //实时更新数据
            basePatient.setInpatientT(Integer.parseInt(time));
            //设置时间
            dataBaseController.db_Patient_fix(basePatient);
            //更新患者数据库
            DataBase.db_PatientUD();
            //实时更新数据
            return "***【已添加成功】***";
        }
        else {return "***【该床位已被占用，请重新输入】***";}
    }
}