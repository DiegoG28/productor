package productor;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		ReentrantLock latch = new ReentrantLock();

		int rice = 0;
		int bean = 0;
		int corn = 0;
		Container riceContainer = new Container(rice);
		Container beanContainer = new Container(bean);
		Container cornContainer = new Container(corn);

		Shop shop = new Shop(riceContainer, beanContainer, cornContainer);

		Producer riceProducer = new Producer(1, "arroz", 1, "frijol", 2, shop, latch);
		Producer beanProducer = new Producer(2, "frijol", 4, "maiz", 2, shop, latch);
		Producer cornProducer = new Producer(3, "maiz", 2, "arroz", 3, shop, latch);

		Buyer b1 = new Buyer("Diego", 4, 0, 1, true, shop);
		Buyer b2 = new Buyer("Itzel", 3, 8, 0, shop);
		Buyer b3 = new Buyer("Sofia", 10, 5, 5, shop);
		Buyer b4 = new Buyer("Adolfo", 6, 0, 0, shop);
		Buyer b5 = new Buyer("Aixa", 3, 0, 2, shop);
		Buyer b6 = new Buyer("Carolina", 8, 2, 7, shop);

		riceProducer.start();
		beanProducer.start();
		cornProducer.start();
		b1.start();
		b2.start();
		b3.start();
		b4.start();
		b5.start();
		b6.start();
	}
}
