package com.neuedu.his.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 患者基础父类
 * @auther 孙续洋
 * @date 7/23
 */
public class BasePatient {
    private String name;//患者姓名
    private String age;//患者年龄
    private Department department;//患者科室
    private String id;//患者编号
    private Doctor doctor;//患者挂号医生
    private String sex;//患者性别
    private String personalStatement;//患者自述/状况
    private String diagnosis;//诊断结果
    private boolean isDiagnosis;//患者是否就诊
    private int inpatientT;//患者住院时长，0：未住院，！0：已住院
    private HashMap<Medicine,Integer> medicine;
    private HashMap<Test,String> test;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonalStatement() {
        return personalStatement;
    }

    public void setPersonalStatement(String personalStatement) {
        this.personalStatement = personalStatement;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean isDiagnosis() {
        return isDiagnosis;
    }

    public void setDiagnosis(boolean diagnosis) {
        isDiagnosis = diagnosis;
    }

    public int getInpatientT() {
        return inpatientT;
    }

    public void setInpatientT(int inpatientT) {
        this.inpatientT = inpatientT;
    }

    public HashMap<Test, String> getTest() {
        return test;
    }

    public void setTest(HashMap<Test, String> test) {
        this.test = test;
    }

    public HashMap<Medicine, Integer> getMedicine() {
        return medicine;
    }

    public void setMedicine(HashMap<Medicine, Integer> medicine) {
        this.medicine = medicine;
    }
}
