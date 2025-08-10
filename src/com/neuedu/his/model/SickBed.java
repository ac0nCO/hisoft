package com.neuedu.his.model;

/**
 * 病床类
 * 作者：王思汉
 * date：7/25
 */
public class SickBed {
    //病床编号
    private String number;
    //病床状态
    private String state;
    //病床病人信息
    private BasePatient basePatient;
    //病床占用时长
    private String time;

    public String getNumber() {
        return number;
    }
//得到病床编号
    public void setNumber(String number) {
        this.number = number;
    }
//设置病床编号
    public String getState() {
        return state;
    }
//得到病床状态
    public void setState(String state) {
        this.state = state;
    }
//设置病床状态
    public BasePatient getBasePatient() {
        return basePatient;
    }
//得到病床病人信息
    public void setBasePatient(BasePatient basePatient) {
        this.basePatient = basePatient;
    }
//设置病床病人信息
    public String getTime() {
        return time;
    }
//得到病床占用时长
    public void setTime(String time) {
        this.time = time;
    }
//设置病床占用时长
    @Override
    public String toString() {
        return "SickBed{" +
                "number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", basePatient=" + basePatient +
                ", time='" + time + '\'' +
                '}';
    }
}
