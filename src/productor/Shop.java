package productor;

public class Shop {
	Container riceContainer;
	Container beanContainer;
	Container cornContainer;
	Object fill = new Object();
	Object open = new Object();
	Object turn = new Object();

	public Shop(Container riceCtn, Container beanCtn, Container cornCtn) {
		riceContainer = riceCtn;
		beanContainer = beanCtn;
		cornContainer = cornCtn;
	}

	public Shop() {

	}

	public void addProduct(String product, int quantity) {
		Container container = getContainer(product);
		container.products += quantity;
		if (container.products >= 10) {
			container.products = 10;
		}
	}

	public void sellProduct(String product, int quantity, String buyerName) {
		if (quantity > 0) {
			Container container = getContainer(product);
			container.products -= quantity;
			System.out.println(buyerName + " compró " + quantity + " toneladas de " + product);
		}
		return;
	}

	public Container getContainer(String product) {
		if (product == "arroz") {
			return riceContainer;
		} else if (product == "frijol") {
			return beanContainer;
		} else if (product == "maiz") {
			return cornContainer;
		}
		return null;
	}

	public Boolean areContainersFull() {
		if (riceContainer.isFull() && beanContainer.isFull() && cornContainer.isFull()) {
			return true;
		}
		return false;
	}

	public Boolean hasAvailability(String product, int quantity) {
		Container container = getContainer(product);
		if (container.getQuantity() < quantity) {
			return false;
		}
		return true;
	}
}
