/**
 * 
 */
package cn.daniel.Buffer;

import java.util.Random;

/**
 * @author Daniel Liang
 * @ClassName CircularBufferLockTest
 * @date 2018年12月31日 下午2:41:10
 *
 */
public class CircularBufferTest {
	
	private static final int nThreads = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {
		CircularBufferLock cb = new CircularBufferLock<Integer>(1024);
		Random ra = new Random();
		
		for (int i = 0; i < nThreads; i++) {
			new Thread(() -> {
				while (true) {
					try {
						cb.putElement("" + (ra.nextInt(90) + 10));
						System.out.println(cb.status(false));
						Thread.sleep(ra.nextInt(300) + 200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		for (int i = 0; i < nThreads; i++) {
			new Thread(() -> {
				while (true) {
					try {
						cb.getElement();
						System.out.println(cb.status(false));
						Thread.sleep(ra.nextInt(300) + 200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		

	}

}
