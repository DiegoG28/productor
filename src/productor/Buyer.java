package productor;

public class Buyer extends Thread {
	String name;
	int riceQuantity = 0;
	int beanQuantity = 0;
	int cornQuantity = 0;
	Boolean isFirstTurn = false;
	Shop shop;

	public Buyer(String name, int riceQuantity, int beanQuantity, int cornQuantity, Shop shop) {
		this.name = name;
		this.riceQuantity = riceQuantity;
		this.beanQuantity = beanQuantity;
		this.cornQuantity = cornQuantity;
		this.shop = shop;
	}

	public Buyer(String name, int riceQuantity, int beanQuantity, int cornQuantity, Boolean isFirstTurn, Shop shop) {
		this.name = name;
		this.riceQuantity = riceQuantity;
		this.beanQuantity = beanQuantity;
		this.cornQuantity = cornQuantity;
		this.isFirstTurn = isFirstTurn;
		this.shop = shop;
	}

	public void run() {
		try {
			if (isFirstTurn) {
				synchronized (shop.fill) {
					shop.fill.wait();
				}
			} else {
				synchronized (shop.turn) {
					shop.turn.wait();
				}
			}
			System.out.println("");
			showList();
			buy();
			showContainers();
			System.out.println("");
			synchronized (shop.turn) {
				shop.turn.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buy() {
		if (!shop.hasAvailability("arroz", riceQuantity)
				|| !shop.hasAvailability("frijol", beanQuantity)
				|| !shop.hasAvailability("maiz", cornQuantity)) {
			System.out.println(name + " no pudo comprar porque no hay suficiente producto");
			try {
				synchronized (shop.fill) {
					shop.fill.notifyAll();
					shop.fill.wait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		shop.sellProduct("arroz", riceQuantity, name);
		shop.sellProduct("frijol", beanQuantity, name);
		shop.sellProduct("maiz", cornQuantity, name);
	}

	public void showContainers() {
		System.out.println("--------------------");
		System.out.println("Stock de la tienda");
		System.out.println("Arroz: " + shop.getContainer("arroz").getQuantity());
		System.out.println("Frijol: " + shop.getContainer("frijol").getQuantity());
		System.out.println("Maíz: " + shop.getContainer("maiz").getQuantity());
		System.out.println("--------------------");
	}

	public void showList() {
		System.out.println("--------------------");
		System.out.println("Lista de " + name);
		System.out.println("Arroz - " + riceQuantity);
		System.out.println("Frijol - " + beanQuantity);
		System.out.println("Maíz - " + cornQuantity);
		System.out.println("--------------------");
	}
}
