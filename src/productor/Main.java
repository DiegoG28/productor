package productor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		ReentrantLock latch = new ReentrantLock();
		Semaphore s = new Semaphore(1);
		Season season = new Season();

		double rice = 0;
		double bean = 0;
		double corn = 0;
		Container riceContainer = new Container(rice);
		Container beanContainer = new Container(bean);
		Container cornContainer = new Container(corn);

		Shop shop = new Shop(riceContainer, beanContainer, cornContainer);

		Producer p1 = new Producer(1, "arroz", 2, "frijol", 4, shop, s, season);
		Producer p2 = new Producer(2, "frijol", 2, "maiz", 4, shop, s, season);
		Producer p3 = new Producer(3, "maiz", 2, "arroz", 3, shop, s, season);
		Producer p4 = new Producer(4, "frijol", 1, "arroz", 1, shop, s, season);

		Buyer b1 = new Buyer("Diego", 4, 0, 1, true, shop, season);
		Buyer b2 = new Buyer("Itzel", 3, 8, 0, shop, season);
		Buyer b3 = new Buyer("Sofia", 10, 5, 5, shop, season);
		Buyer b4 = new Buyer("Adolfo", 6, 0, 0, shop, season);
		Buyer b5 = new Buyer("Aixa", 3, 0, 2, shop, season);
		Buyer b6 = new Buyer("Carolina", 4, 1, 4, shop, season);
		Buyer b7 = new Buyer("Manolo", 2, 4, 0, shop, season);
		Buyer b8 = new Buyer("Luisa", 5, 7, 1, shop, season);
		Buyer b9 = new Buyer("Carlos", 1, 2, 1, shop, season);
		Buyer b10 = new Buyer("Karen", 3, 0, 0, shop, season);
		Buyer b11 = new Buyer("Alberto", 0, 2, 7, shop, season);
		Buyer b12 = new Buyer("Claudia", 6, 3, 1, shop, season);
		Buyer b13 = new Buyer("David", 5, 0, 10, shop, season);
		Buyer b14 = new Buyer("Isabel", 0, 0, 1, shop, season);
		Buyer b15 = new Buyer("Paco", 10, 10, 10, shop, season);
		Buyer b16 = new Buyer("Maria", 5, 1, 2, shop, season);
		Buyer b17 = new Buyer("Ernesto", 2, 2, 2, shop, season);
		Buyer b18 = new Buyer("Lisa", 4, 4, 4, shop, season);
		Buyer b19 = new Buyer("Isaias", 0, 1, 5, shop, season);
		Buyer b20 = new Buyer("Victoria", 1, 9, 1, shop, season);

		p1.start();
		p2.start();
		p3.start();
		p4.start();

		b1.start();
		b2.start();
		b3.start();
		b4.start();
		b5.start();
		b6.start();
		b7.start();
		b8.start();
		b9.start();
		b10.start();
		b11.start();
		b12.start();
		b13.start();
		b14.start();
		b15.start();
		b16.start();
		b17.start();
		b18.start();
		b19.start();
		b20.start();
	}
}
