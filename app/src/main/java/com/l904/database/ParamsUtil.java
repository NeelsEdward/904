package com.l904.database;

/**
 * Created by Dell Laptop on 8/9/2015.
 */
public class ParamsUtil {
    String name_date_des,color_amount_target;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  ParamsUtil(int id,String ndd,String cam){
        this.id = id;
        this.name_date_des = ndd;
        this.color_amount_target = cam;
    }

    public String getName_date_des() {
        return name_date_des;
    }

    public void setName_date_des(String name_date_des) {
        this.name_date_des = name_date_des;
    }

    public String getColor_amount_target() {
        return color_amount_target;
    }

    public void setColor_amount_target(String color_amount_target) {
        this.color_amount_target = color_amount_target;
    }
}
