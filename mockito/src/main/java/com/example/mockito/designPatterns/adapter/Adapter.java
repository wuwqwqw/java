package com.example.mockito.designPatterns.adapter;

//显卡，即我们的适配器
public class Adapter extends Target {

        // 被代理的设备
        private Adaptee apt = null;

        public Adapter(Adaptee apt) {
                this.apt = apt;
        }

        public String request() {
                return apt.getData();
        }
}