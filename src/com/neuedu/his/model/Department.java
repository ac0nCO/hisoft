package com.neuedu.his.model;

/**
 * 部门信息类
 * @author 孙续洋
 * @date 7/22
 */
public class Department {
    private int did;//部门id
    private String name;//部门名称

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "did='" + did + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
