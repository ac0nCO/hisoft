package com.neuedu.his.controller;

import com.neuedu.his.db.DataBase;
import com.neuedu.his.model.BasePatient;
import com.neuedu.his.model.Doctor;
import com.neuedu.his.model.Medicine;
import com.neuedu.his.model.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 病人业务控制类
 * @author 孙续洋
 * @date 7/24
 */
public class PatientController {

    //搜索已预约病人方法
    public ArrayList<BasePatient> findPatientApt(Doctor doctor) {
        ArrayList<BasePatient> basePatientList = new ArrayList<>();
        for (BasePatient patient : DataBase.basePatientTable) {
            if (patient.getDoctor().equals(doctor)) {
                basePatientList.add(patient);
            }
        }
        return basePatientList;
    }

    //判断病人是否诊断方法
    public String isDiagnosis(boolean isDiagnosis) {
        if (isDiagnosis) {
            return "已诊断";
        } else {
            return "未诊断";
        }
    }

    //在指定列表中查找病人方法
    public BasePatient patientChoose(String id, ArrayList<BasePatient> basePatientList) {
        BasePatient patient = new BasePatient();
        for (BasePatient basePatient : basePatientList) {
            if (basePatient.getId().equals(id)) {
                patient = basePatient;
            }
        }
        return patient;
    }

    //判断病人是否住院方法
    public String isInpatient(int inpatientT) {
        if (inpatientT == 0) {
            return "未住院";
        } else {
            return "已住院";
        }
    }

    //显示病人用药的方法
    public String medInfo(BasePatient basePatient){
        HashMap<Medicine,Integer> medList = new HashMap<>();
        int a = 0;
        for (HashMap.Entry<Medicine,Integer> medMap : basePatient.getMedicine().entrySet()){
            if (medMap.getValue()!=0){
                medList.put(medMap.getKey(),medMap.getValue());
                a++;
            }
        }
        if (a==0){
            return "";
        }
        System.out.print("\n|--用药信息--|\n");
        for (HashMap.Entry<Medicine,Integer> medMap : medList.entrySet()){
            String medName = medMap.getKey().getName();
            String count = medMap.getValue().toString();
            System.out.println("|"+medName+"|\t<"+count+">");
        }
        return "----------------";
    }


    //显示病人检查的方法
    public String testInfo(BasePatient basePatient){
        HashMap<Test,String> testList = new HashMap<>();
        int a = 0;
        for (HashMap.Entry<Test,String> testMap : basePatient.getTest().entrySet()){
            if (!("None".equals(testMap.getValue()))){
                testList.put(testMap.getKey(),testMap.getValue());
                a++;
            }
        }
        if (a==0){
            return "";
        }
        System.out.print("\n|--检查信息--|\n");
        for (HashMap.Entry<Test,String> testMap : testList.entrySet()){
            String testName = testMap.getKey().getName();
            String testState = testMap.getValue();
            String id = testMap.getKey().getId();
            System.out.println("["+id+"]" +"|"+testName+"|\t<"+testState+">");
        }
        return "----------------";
    }


    public void addPTMed(String medId,int medCount,BasePatient basePatient){
        MedicineController medicineController = new MedicineController();
        DataBaseController dataBaseController = new DataBaseController();
        Medicine medicine = medicineController.findmedById(medId);
        String a = medicine.getId();
        if ("-200".equals(a)){
            System.out.println("----【警告】输入的编号不存在！----");
            return;
        }
        basePatient.getMedicine().put(medicine,medCount);
        dataBaseController.db_Patient_fix(basePatient);
        DataBase.db_PatientUD();
    }


    public void addPTtest(String testId,BasePatient basePatient){
        TestController testController = new TestController();
        DataBaseController dataBaseController = new DataBaseController();
        Test test = testController.findTestById(testId);
        String a = test.getId();
        if ("-200".equals(a)){
            System.out.println("----【警告】输入的编号不存在！----");
            return;
        }
        basePatient.getTest().put(test,"待检查");
        dataBaseController.db_Patient_fix(basePatient);
        DataBase.db_PatientUD();
    }


    public int ptMedFee(BasePatient basePatient){
        HashMap<Medicine,Integer> medList = new HashMap<>();
        int fee = 0;
        for (HashMap.Entry<Medicine,Integer> medMap : basePatient.getMedicine().entrySet()){
            if (medMap.getValue()!=0){
                fee += medMap.getValue()*medMap.getKey().getPrice();
            }
        }
        return fee;
    }
}


