package productor;

import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
	int noProducer;
	String product;
	int productionCapacity;
	String product2;
	int productionCapacity2;
	Shop shop;
	ReentrantLock latch;

	public Producer(int noProducer, String product, int prdCapacity, String product2, int prdCapacity2, Shop shop,
			ReentrantLock latch) {
		this.noProducer = noProducer;
		this.product = product;
		this.productionCapacity = prdCapacity;
		this.product2 = product2;
		this.productionCapacity2 = prdCapacity2;
		this.shop = shop;
		this.latch = latch;
	}

	public void run() {
		while (true) {
			while (shop.shouldFill) {
				latch.lock();
				fill();
				latch.unlock();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void fill() {
		System.out.println("Productor " + noProducer + " entró a surtir.");
		shop.addProduct(product, productionCapacity);
		shop.addProduct(product2, productionCapacity2);
		System.out.println("Productor " + noProducer + " terminó de surtir.");
		synchronized (shop.fill) {
			if (shop.areContainersFull()) {
				shop.fill.notify();
				shop.shouldFill = false;
			}
		}
	}
}
