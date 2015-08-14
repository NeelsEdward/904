package com.l904.database;


public class ParamsUtil {

    public static final  String TYPE= "type";
    public static final int TYPE_EXPENSE=1;
    public static final int TYPE_COUNTER=2;
    public static final int TYPE_TODO=3;
    public static final int TYPE_TODOITEM=4;
    public static final String[] TYPES = new String[]{"", "Expense", "Counter", "Todo", "Todo Item"};



    String name_date_des,color_amount_target;
    int id;
    private String expOrCounter;


    public ParamsUtil(int id, String ndd, String cam) {
        this.id = id;
        this.name_date_des = ndd;
        this.color_amount_target = cam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getExpOrCounter() {
        return expOrCounter;
    }

    public void setExpOrCounter(String expOrCounter) {
        this.expOrCounter = expOrCounter;
    }
}
