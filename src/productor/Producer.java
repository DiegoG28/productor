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

	public synchronized void run() {
		int vecesEjecutadas = 0;
		while (true) {
			if (vecesEjecutadas == 0) {
				latch.lock();
				fill();
				vecesEjecutadas++;
				latch.unlock();
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (Math.round(Math.random()) == 0) {
				vecesEjecutadas = 0;
			}
		}
	}

	public void fill() {
		shop.addProduct(product, productionCapacity, noProducer);
		shop.addProduct(product2, productionCapacity2, noProducer);
		synchronized (shop.fill) {
			if (shop.areContainersFull()) {
				shop.fill.notify();
			}
			if (shop.getContainer(product).isFull()) {
				try {
					shop.fill.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
