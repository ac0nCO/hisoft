package com.neuedu.his.model;

import java.util.HashMap;

/**
 * 门诊医生信息类
 * @author 孙续洋
 * @date 7/24
 */
public class Doctor extends BaseUser{
    //id,account,password,name
    private Department department;//医生科室
    private String grade;//初级医师，主治医师，专家
    private HashMap<Integer,String> workplan;//1-7
    private String sex;//医生性别
    private int ptCount;//预约患者数量，不写入数据库

    public HashMap<Integer, String> getWorkplan() {
        return workplan;
    }

    public void setWorkplan(HashMap<Integer, String> workplan) {
        this.workplan = workplan;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "department=" + department +
                ", grade='" + grade + '\'' +
                ", workplan=" + workplan +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public int getPtCount() {
        return ptCount;
    }

    public void setPtCount(int ptCount) {
        this.ptCount = ptCount;
    }
}
