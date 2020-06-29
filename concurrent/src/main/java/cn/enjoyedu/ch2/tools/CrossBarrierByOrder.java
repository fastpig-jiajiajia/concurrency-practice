package cn.enjoyedu.ch2.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CrossBarrierByOrder {
	
	public static void main(String[] args) {
		CrossBarrier crossBarrier = new CrossBarrier();
		
		new Thread(() -> {
	        for(int i  = 0; i < 4; i++){
	        	crossBarrier.xmCross(i);
	        }
	    }).start();

	    new Thread(() -> {
	        for(int i  = 0; i < 4; i++){
	        	crossBarrier.qdCross(i);
	        }
	    }).start();
	    
	    new Thread(() -> {
	    	for(int i  = 0; i < 4; i++){
	    		crossBarrier.xgCross(i);
	    	}
	    }).start();
	    
//	    new Thread(() -> {
//	    	
//	    });
	}
}

/**
 * Title: CrossBarrier
 * Description: 
 * @author xurui
 * @date 2020年5月27日
 * 
 */
class CrossBarrier{
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	AtomicInteger count = new AtomicInteger(1);
	
	/**
	 * 小明
	 * @param num 当前是第几个障碍
	 */
	public void xmCross(int num) {
		lock.lock();
		
		
		try {
			while(count.get() % 3 != 1) {
				condition1.await();
			}
			long startTime = System.currentTimeMillis();
			LocalDateTime localDateTime = LocalDateTime.now();
			// 睡一秒
			TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(Math.round(Math.random() * 1000) + 1000)));
			// 获取时间输出
			String nowTimeStr = localDateTime.format(dateTimeFormat);
			long endTime = System.currentTimeMillis();
			double useTime = (endTime - startTime) / 1000.0;
			System.out.format("%s 小明, 翻越了第 %d个障碍物, 使用了 %4.3f s \n", nowTimeStr, num, useTime);
			count.incrementAndGet();
			
			condition2.signal();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	/**
	 * 乔丹
	 * @param num 当前是第几个障碍
	 */
	public void qdCross(int num) {
		lock.lock();
		try {
			while(count.get() % 3 != 2) {
				condition2.await();
			}
			long startTime = System.currentTimeMillis();
			LocalDateTime localDateTime = LocalDateTime.now();
			// 睡一秒
			TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(Math.round(Math.random() * 1000) + 1000)));
			// 获取时间输出
			String nowTimeStr = localDateTime.format(dateTimeFormat);
			long endTime = System.currentTimeMillis();
			double useTime = (endTime - startTime) / 1000.0;
			System.out.format("%s 乔丹, 翻越了第 %d个障碍物, 使用了 %4.3f s \n", nowTimeStr, num, useTime);
			count.incrementAndGet();
			
			condition3.signal();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	/**
	 * 小乖
	 * @param num 当前是第几个障碍
	 */
	public void xgCross(int num) {
		lock.lock();
		
		
		try {
			while(count.get() % 3 != 0) {
				condition3.await();
			}
			long startTime = System.currentTimeMillis();
			LocalDateTime localDateTime = LocalDateTime.now();
			// 睡一秒
			TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(Math.round(Math.random() * 1000) + 1000)));
			// 获取时间输出
			String nowTimeStr = localDateTime.format(dateTimeFormat);
			long endTime = System.currentTimeMillis();
			double useTime = (endTime - startTime) / 1000.0;
			System.out.format("%s 小乖, 翻越了第 %d个障碍物, 使用了 %4.3f s \n", nowTimeStr, num, useTime);
			count.incrementAndGet();
			
			condition1.signal();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
//	public void watchCount() {
//		while(true) {
//			if(count != 12) {
//				if(count % 3 == 0) {
//					
//				}
//			}
//		}
//	}
}
