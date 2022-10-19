package productor;

public class Producer extends Thread {
	String product;
	int productionCapacity;
	Shop shop;

	public Producer(String product, int prdCapacity, Shop shop) {
		this.product = product;
		this.productionCapacity = prdCapacity;
		this.shop = shop;
	}

	public void run() {
		while (true) {
			fill();
		}
	}

	public void fill() {
		shop.addProduct(product, productionCapacity);
		System.out.println(productionCapacity + " toneladas de " + product + " enviadas");
		try {
			synchronized (shop.fill) {
				if (shop.areContainersFull()) {
					shop.fill.notify();
				}
				if (shop.getContainer(product).isFull()) {
					shop.fill.wait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
