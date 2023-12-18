package com.alipay.shop.designer.composite.test;

/**
 * @author hyy
 * @Description
 * @create 2023-12-16 22:53
 */
public class testClassCast {
    public static void main(String[] args) {
//        Person p = new Person();
//        p.name = "hujiale";
//        Man m = (Man) p;
//        System.out.println(m.toString()); -> ClassCastException

//        AbstractClass cls = new AbstractClass(); -> cannot initial abstract class
        Person p = new Man();
        p.name = "hujiale";

        Man m = (Man) p;
        System.out.println(m.like);
        System.out.println(m.name);
    }
}

abstract class AbstractClass {
    private String name;
}

class Person {
    String name;

}

class Man extends Person {
    String like;
}