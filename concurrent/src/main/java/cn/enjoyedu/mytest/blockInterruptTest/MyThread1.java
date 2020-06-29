package cn.enjoyedu.mytest.blockInterruptTest;

public class MyThread1 extends Thread{
    public synchronized static void doSomething(){
        while(true){
            //do something
        }
    }
    @Override
    public void run(){
        doSomething();
    }
}
