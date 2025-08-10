package com.neuedu.his.model;

/**
 * 化验检查信息类
 * @author 周俊泽
 * @date 7/25
 */
public class Test {
    private String name;//检查名称
    private int price;//检查价格
    private String id;//检查编号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
