package cn.enjoyedu.ch2.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrossBarrierCyclicBarrier {
    private static AtomicInteger count = new AtomicInteger(1);
    private static CrossBarrier2 crossBarrier;
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        if(count.incrementAndGet() < 5){
            runBarrier();
        }
    });

    static {
        crossBarrier = new CrossBarrier2(cyclicBarrier);
    }


    public static void main(String[] args) {
        runBarrier();
    }

    public static void runBarrier(){
        int num = count.get();

        new Thread(() -> {
                crossBarrier.xmCross(num);
        }).start();

        new Thread(() -> {
                crossBarrier.qdCross(num);
        }).start();

        new Thread(() -> {
                crossBarrier.xgCross(num);
        }).start();
    }
}

/**
 * Title: CrossBarrier
 * Description:
 * @author xurui
 * @date 2020年5月27日
 *
 */
class CrossBarrier2{

    public CyclicBarrier cyclicBarrier;
    // 线程安全时间格式化类
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CrossBarrier2(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    /**
     * 小明
     * @param num 当前是第几个障碍
     */
    public void xmCross(int num) {
        try {
            long startTime = System.currentTimeMillis();
            LocalDateTime localDateTime = LocalDateTime.now();
            // 睡一秒
            TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(Math.round(Math.random() * 1000) + 1000)));
            // 获取时间输出
            String nowTimeStr = localDateTime.format(dateTimeFormat);
            long endTime = System.currentTimeMillis();
            double useTime = (endTime - startTime) / 1000.0;
            System.out.format("%s 小明, 翻越了第 %d个障碍物, 使用了 %4.3f s \n", nowTimeStr, num, useTime);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 乔丹
     * @param num 当前是第几个障碍
     */
    public void qdCross(int num) {
        try {
            long startTime = System.currentTimeMillis();
            LocalDateTime localDateTime = LocalDateTime.now();
            // 睡一秒
            TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(Math.round(Math.random() * 1000) + 1000)));
            // 获取时间输出
            String nowTimeStr = localDateTime.format(dateTimeFormat);
            long endTime = System.currentTimeMillis();
            double useTime = (endTime - startTime) / 1000.0;
            System.out.format("%s 乔丹, 翻越了第 %d个障碍物, 使用了 %4.3f s \n", nowTimeStr, num, useTime);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 小乖
     * @param num 当前是第几个障碍
     */
    public void xgCross(int num) {
        try {
            long startTime = System.currentTimeMillis();
            LocalDateTime localDateTime = LocalDateTime.now();
            // 睡一秒
            TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(Math.round(Math.random() * 1000) + 1000)));
            // 获取时间输出
            String nowTimeStr = localDateTime.format(dateTimeFormat);
            long endTime = System.currentTimeMillis();
            double useTime = (endTime - startTime) / 1000.0;
            System.out.format("%s 小乖, 翻越了第 %d个障碍物, 使用了 %4.3f s \n", nowTimeStr, num, useTime);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
