package com.web.bean;

/**
 * Created by skandula on 3/21/16.
 */
public class PrototypeBean {
    public PrototypeBean(){
        System.out.println("PrototypeBean bean is instantiated");
    }
    public int getCounter() {
        return counter++;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    private int counter;

}
