package com.neuedu.his.controller;

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.*;
import com.neuedu.his.utils.FileOperationUtils;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.SplittableRandom;


/**
 * 数据库操作类
 * @author 孙续洋
 */
public class DataBaseController {

    /**
     * db_doctor数据库的写入方法
     * @author 孙续洋
     * @date 7/24
     * @param doctor
     * @param dir
     * @return
     * @throws IOException
     */
    public boolean db_Doctor_wri(Doctor doctor,String dir) throws IOException {
        String name = doctor.getName();
        String sex = doctor.getSex();
        String id = doctor.getId();
        Department department = doctor.getDepartment();
        String grade = doctor.getGrade();
        String account = doctor.getAccount();
        String password = doctor.getPassword();
        HashMap<Integer,String> workplan = doctor.getWorkplan();
        String info = name + " " + sex + " " + id + " " + department.getDid() + " " + grade + " " + account + " " + password
                + " " + workplan.get(1) + " " + workplan.get(2) + " " + workplan.get(3) + " " + workplan.get(4) + " " + workplan.get(5) + " " + workplan.get(6) + " " + workplan.get(7);
        try {
            FileOperationUtils.write(dir,info,true);
        }catch (IOException e){
            return false;
        }
        return true;
    }


    /**
     * db_patient数据库的写入方法
     * @author 孙续洋
     * @date 7/24
     * @param basePatient
     * @param
     * @return
     */
    public boolean db_Patient_wri(BasePatient basePatient,String dir,boolean notCover){
        Medicine medicine = new Medicine();
        String isdia;
        String name = basePatient.getName();
        String sex = basePatient.getSex();
        String age = basePatient.getAge();
        String id = basePatient.getId();
        Department department = basePatient.getDepartment();
        Doctor doctor = basePatient.getDoctor();
        String personalStatement = basePatient.getPersonalStatement();
        String diagnosis = basePatient.getDiagnosis();
        String medicine1 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(0)));
        String medicine2 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(1)));
        String medicine3 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(2)));
        String medicine4 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(3)));
        String medicine5 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(4)));
        String medicine6 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(5)));
        String medicine7 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(6)));
        String medicine8 = String.valueOf(basePatient.getMedicine().get(DataBase.medicineTable.get(7)));
        String test1 = basePatient.getTest().get(DataBase.testTable.get(0));
        String test2 = basePatient.getTest().get(DataBase.testTable.get(1));
        String test3 = basePatient.getTest().get(DataBase.testTable.get(2));
        String test4 = basePatient.getTest().get(DataBase.testTable.get(3));
        boolean isDiagnosis = basePatient.isDiagnosis();
        int inpatientT = basePatient.getInpatientT();

        if (isDiagnosis){
            isdia = "已诊断";
        }else {
            isdia = "未诊断";
        }
        String info = name + " " + sex + " " + age + " " + id + " " + Integer.toString(department.getDid()) + " "
                + doctor.getId() + " " + personalStatement + " " + diagnosis + " " + isdia + " " + String.valueOf(inpatientT) + " "
                + " "+medicine1+" "+medicine2+" "+medicine3+" "+medicine4+" "+medicine5+" "+medicine6+" "+medicine7+" "+medicine8+" "
                +test1+" "+test2+" "+test3+" "+test4;
        try {
            FileOperationUtils.write(dir,info,notCover);
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    /**
     * db_sickbed数据库写入方法
     * @author 孙续洋
     * @date 7/26
     * @param sickBed
     * @param dir
     * @param notCover
     * @return
     */
    public boolean db_Sickbed_wri(SickBed sickBed,String dir,boolean notCover){
        String id = sickBed.getNumber();
        String state = sickBed.getState();
        BasePatient basePatient = sickBed.getBasePatient();
        String time = sickBed.getTime();
        String info = id+" "+state+" "+basePatient.getId()+" "+time;
        try {
            FileOperationUtils.write(dir,info,true);
        }catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 修改db_patient数据库数据的方法
     * @author 孙续洋
     * @date 7/26
     * @param basePatient
     * @return
     */
    public boolean db_Patient_fix(BasePatient basePatient){
        String line = basePatient.getId();
        try {
            DataBase.basePatientTable.set((Integer.parseInt(line)-1),basePatient);
            FileOperationUtils.deleteFile("C:\\Users\\sun\\Desktop\\HISDB\\db_patient.txt");
            for (BasePatient basePatient1 : DataBase.basePatientTable){
                db_Patient_wri(basePatient1,"C:\\Users\\sun\\Desktop\\HISDB\\db_patient.txt",true);
            }
            return true;
        }catch (Exception e){
            //System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }


    /**
     * 修改db_Sickbed数据库的方法
     * @author 孙续洋
     * @date 7/26
     * @param sickBed
     * @return
     */
    public boolean db_Sickbed_fix(SickBed sickBed){
        String line = sickBed.getNumber();
        try {
            DataBase.sickBedTable.set(Integer.parseInt(line),sickBed);
            FileOperationUtils.deleteFile("C:\\Users\\sun\\Desktop\\HISDB\\db_sickbed.txt");
            for (SickBed sickBed1 : DataBase.sickBedTable){
                db_Sickbed_wri(sickBed1,"C:\\Users\\sun\\Desktop\\HISDB\\db_sickbed.txt",true);
            }
            return true;
        }catch (Exception e){
            //System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

}
