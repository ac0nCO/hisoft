package com.neuedu.his.model;

/**
 * 药品信息类
 * @author 钱程
 * @date 07-25
 */
public class Medicine {
    private String name;//药品名称
    private int price;//药品价格
    private String id;//药品id

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
