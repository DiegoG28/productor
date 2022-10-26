package productor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
	int noProducer;
	String product;
	double baseProduction;
	double productionModified;
	String product2;
	double baseProduction2;
	double productionModified2;
	Shop shop;
	Semaphore s;
	Season season;

	public Producer(int noProducer, String product, double baseProduction, String product2, double baseProduction2,
			Shop shop,
			Semaphore s, Season season) {
		this.noProducer = noProducer;
		this.product = product;
		this.baseProduction = baseProduction;
		this.product2 = product2;
		this.baseProduction2 = baseProduction2;
		this.shop = shop;
		this.s = s;
		this.season = season;
	}

	public void run() {
		while (true) {
			try {
				s.acquire();
				setProduction();
				fill();
				s.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				sleep(1000);
			} catch (Exception e) {

			}
		}
	}

	public void fill() throws InterruptedException {
		System.out.println("Productor " + noProducer + " entró a surtir.");
		shop.addProduct(product, productionModified);
		shop.addProduct(product2, productionModified2);
		System.out.println("Productor " + noProducer + " terminó de surtir.");
		if (shop.areContainersFull()) {
			synchronized (shop.open) {
				shop.open.notify();
			}
			synchronized (shop.fill) {
				shop.fill.wait();
			}
		}
	}

	public void setProduction() {
		String seasonName = season.getSeason();
		if (seasonName.equals("primavera")) {
			productionModified = baseProduction;
			productionModified2 = baseProduction2;
		} else if (seasonName.equals("verano")) {
			productionModified = baseProduction * 1.5;
			productionModified2 = baseProduction2 * 1.5;
		} else if (seasonName.equals("otoño")) {
			productionModified = baseProduction * 2;
			productionModified2 = baseProduction2 * 2;
		} else if (seasonName.equals("invierno")) {
			productionModified = baseProduction * 0.5;
			productionModified2 = baseProduction2 * 0.5;
		}
	}
}
