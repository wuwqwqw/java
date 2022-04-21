package com.example.mockito.designPatterns.builder;

public class UserBuilderTest {
    public static void main(String[] args) {
        User user = new User.UserBuilder("hello", "world")
                .age(10)
                .address("Beijing")
                .phone("110")
                .Build();
        System.out.println(user);
    }
}
