package productor;

public class Container extends Shop {
	int products = 0;

	public Container(Container ctn1, Container ctn2, Container ctn3) {
		super(ctn1, ctn2, ctn3);
		// TODO Auto-generated constructor stub
	}

	public Container(int products) {
		this.products = products;
	}

	public Boolean isFull() {
		if (products == 10) {
			return true;
		}
		return false;
	}

	public int getQuantity() {
		return products;
	}
}
