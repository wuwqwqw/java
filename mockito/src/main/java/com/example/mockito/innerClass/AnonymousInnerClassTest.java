package com.example.mockito.innerClass;

import java.util.Arrays;
import java.util.Comparator;

public class AnonymousInnerClassTest {

    public int field1 = 1;
    protected int field2 = 2;
    int field3 = 3;
    private int field4 = 4;

    public AnonymousInnerClassTest() {
        System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
    }
    // 自定义接口
    interface OnClickListener {
        void onClick(Object obj);
    }

    private void anonymousClassTest() {
        // 在这个过程中会新建一个匿名内部类对象，
        // 这个匿名内部类实现了 OnClickListener 接口并重写 onClick 方法
        OnClickListener clickListener = new OnClickListener() {
            // 可以在内部类中定义属性，但是只能在当前内部类中使用，
            // 无法在外部类中使用，因为外部类无法获取当前匿名内部类的类名，
            // 也就无法创建匿名内部类的对象
            int field = 1;

            @Override
            public void onClick(Object obj) {
                System.out.println("对象 " + obj + " 被点击");
                System.out.println("其外部类的 field1 字段的值为: " + field1);
                System.out.println("其外部类的 field2 字段的值为: " + field2);
                System.out.println("其外部类的 field3 字段的值为: " + field3);
                System.out.println("其外部类的 field4 字段的值为: " + field4);
            }
        };
        // new Object() 过程会新建一个匿名内部类，继承于 Object 类，
        // 并重写了 toString() 方法
        clickListener.onClick(new Object() {
            @Override
            public String toString() {
                return "obj1";
            }
        });
    }

    public static void main(String[] args) {
        AnonymousInnerClassTest outObj = new AnonymousInnerClassTest();
        outObj.anonymousClassTest();

        Integer[] ints = {1, 2, 3};
        Arrays.sort(ints,new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
    }
}
