package com.example.learnKotlin.learn;

//Java版本的单例类
public class SingletonJava {
    private static SingletonJava instance;

    //为了禁止外部创建Singleton的实例，我们需要用private关键字将Singleton的构造函数私有化
    private SingletonJava(){}

    //给外部提供了一个getInstance()静态方法用于获取Singleton的实例
    public synchronized static SingletonJava getInstance(){
        if (instance == null){
            instance = new SingletonJava();
        }
        return instance;
    }

    public void singletonTest() {
        System.out.println("singletonTest is called.");
    }
}
