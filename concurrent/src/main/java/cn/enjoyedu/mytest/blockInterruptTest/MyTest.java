package cn.enjoyedu.mytest.blockInterruptTest;

class MyThread extends Thread{
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

public class MyTest{
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new MyThread();
        thread1.start();

        Thread thread2 = new MyThread();
        thread2.start();
        synchronized (thread2){
            thread2.wait(1000);
            thread2.notify();
        }


        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        thread2.interrupt();
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.getState());
    }

}
