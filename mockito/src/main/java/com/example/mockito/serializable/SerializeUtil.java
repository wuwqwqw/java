package com.example.mockito.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    // 保存对象，序列化
    public static void saveObject(Object object) throws Exception {
        ObjectOutputStream out = null;
        FileOutputStream fout = null;
        try {
            // 我们打开 1.txt 文件，发现里面的内容乱码，注意这不需要我们来看懂，这是二进制文件，计算机能读懂就行了
            fout = new FileOutputStream("D:/1.txt");
            out = new ObjectOutputStream(fout);
            out.writeObject(object);
        } finally {
            fout.close();
            out.close();
        }
    }

    // 读取对象，反序列化
    public static Object readObject() throws Exception {
        ObjectInputStream in = null;
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("D:/1.txt");
            in = new ObjectInputStream(fin);
            Object object = in.readObject();
            return object;
        } finally {
            fin.close();
            in.close();
        }
    }


    public static void main(String[] args) {
        User user = new User();
        user.setName("旭旭宝宝");
        user.setAge(33);

        // 保存
        try {
            SerializeUtil.saveObject(user);
        } catch (Exception e) {
            System.out.println("保存时异常：" + e.getMessage());
        }
        // 读取
        User userObject;
        try {
            userObject = (User) SerializeUtil.readObject();
            System.out.println(userObject);
        } catch (Exception e) {
            System.out.println("读取时异常：" + e.getMessage());
        }
    }

}
