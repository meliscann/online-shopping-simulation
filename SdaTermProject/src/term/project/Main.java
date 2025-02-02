package term.project;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	//Singleton Design Pattern implementation:
	static class ShoppingCart {
		private static ShoppingCart instance;
		private List<Product> products;
		
		private ShoppingCart() {  
			this.products = new ArrayList<>();
		}
		
		public static ShoppingCart getInstance() {
			if (instance == null) {
				instance = new ShoppingCart();
			}
			return instance;
		}
		
		static class Product {
	        private String name;
	        private double price;

	        public Product(String name, double price) {
	            this.name = name;
	            this.price = price;
	        }

	        public String getName() {
	            return name;
	        }
	        
	        public double getPrice() {
	        	return price;
	        }
	    } 	
		
		public void addProduct(Product product) {
			products.add(product);
			System.out.println(product.getName() + " added to the shopping cart.");
		}
		
		public void removeProduct(Product product) {
			if (products.remove(product)) {
				System.out.println(product.getName() + " removed from the shopping cart.");
			}
			else {
				System.out.println(product.getName() + " not found in the shopping cart.");
			}
		}
		
		public void showProducts() {
			System.out.println("Shopping Cart Contents:");
			for (Product product : products) {
				System.out.println("-> " + product.getName() + ", Price: " + product.getPrice());
			}
		}
	}
	
	//Decorator Design Pattern implementation
	public interface Product {
		double getPrice();
		String getName();
	}
	
	static class ConcreteProduct implements Product {
		private double price;
		private String name;
		
		ConcreteProduct(double price, String name) {
			this.price = price;
			this.name = name;
		}

		@Override
		public double getPrice() {
			return price;
		}

		@Override
		public String getName() {
			return name;
		}
	}
	
	static class DiscountDecorator implements Product {
		private Product product;
		private double discount;
		
		DiscountDecorator(Product product, double discount) {
			this.product = product;
			this.discount = discount;
		}

		@Override
		public double getPrice() {
			return product.getPrice() * (1-discount);
		} 

		@Override
		public String getName() {
			return product.getName() + " - " + (discount*100) + "% off \n";
		}	
	}
	
	private static void showDiscountedProducts(Product product) {
		System.out.println("Discount applied to products:");
		System.out.print("Product Name: " + product.getName());
		System.out.println("Discounted Price: " + product.getPrice());
		}
	
	
	//Factory Design Pattern implementation:
	public interface PaymentMethod {
		boolean paymentProcess();
		void showPaymentInfo();
	}
	
	public static class CashOnDelivery implements PaymentMethod {
		private boolean paymentProcess;

		public CashOnDelivery(boolean paymentProcess) {
			this.paymentProcess = paymentProcess;
		}

		@Override
		public boolean paymentProcess() {
			return paymentProcess;
		}

		@Override
		public void showPaymentInfo() {
			System.out.println("Payment will be on delivery.");
			}
		}
	
	
	public static class CreditCard implements PaymentMethod {
		private boolean paymentProcess;
		private int installment;
		
		public CreditCard(boolean paymentProcess, int installment) {
			this.paymentProcess = paymentProcess;
			this.installment = installment;
		}

		@Override
		public boolean paymentProcess() {
			return paymentProcess;
		}

		@Override
		public void showPaymentInfo() {
			if (paymentProcess) {
				System.out.println("You paid with credit card and completed successfully!");
			}
			else {
				System.out.println("Ppayment by credit card failed");
			}
			if(installment != 1) {
				System.out.println(installment + " installments were made.");
			}
			else {
				System.out.println("Payment in a single installment.");
			}
		}
	}
	
	
	public static class DebitCard implements PaymentMethod {
		private boolean paymentProcess;

		//Constructor 
		public DebitCard(boolean paymentProcess) { 
			this.paymentProcess = paymentProcess;
		}

		@Override
		public boolean paymentProcess() {
			return paymentProcess;
		}

		@Override
		public void showPaymentInfo() {
			if (paymentProcess) {
				System.out.println("You paid with debit card and completed successfully!");
			}
			else {
				System.out.println("Payment by debit card failed.");
			}
		}
	}
	
	
	class PaymentMethodFactory {
		public static PaymentMethod createPaymentMethod(String type, boolean paymentProcess, int installment) {
			PaymentMethod paymentMethod = null;
			
			if (type.equals("Credit Card")) {
				paymentMethod = new CreditCard(paymentProcess, installment);
			}
			else if (type.equals("Debit Card")) {
				paymentMethod = new DebitCard(paymentProcess);
			}
			else if (type.equals("Cash On Delivery")){
				paymentMethod = new CashOnDelivery(paymentProcess);
			}
			else if (paymentMethod == null) {
				System.out.println("Invalid payment method.");
			}
			return paymentMethod;
		}
	}	
		
	//Observer Design Pattern implementation
	interface OrderObserver {
	    void update(OrderStatus status);
	}
	
	enum OrderStatus {
	    PLACED, DISPATCHED, DELIVERED
	}
	
	public static class Order {
	    private List<OrderObserver> observers;
	    private OrderStatus status;
	    
	    public Order() {
            this.observers = new ArrayList<>();
        }

	    public void addObserver(OrderObserver observer) {
	        observers.add(observer);
	    }

	    public void removeObserver(OrderObserver observer) {
	        observers.remove(observer);
	    }

	    public void setStatus(OrderStatus status) {
	        this.status = status;
	        notifyObservers();
	    }

	    public void setObservers(List<OrderObserver> observers) {
	        this.observers = observers;
	    }

	    private void notifyObservers() {
	        for (OrderObserver observer : observers) {
	            observer.update(status);
	        }
	    }
	}

	static class EmailNotificationObserver implements OrderObserver {
	    @Override
	    public void update(OrderStatus status) {
	        System.out.println("Email: Order status updated -> " + status);
	    }
	}

	
	static class SMSNotificationObserver implements OrderObserver {
	    @Override
	    public void update(OrderStatus status) {
	        System.out.println("SMS: Order status updated -> " + status);
	    }
	}
	
	
	public static void main(String[] args) {
	
		ShoppingCart cart = ShoppingCart.getInstance();
		ShoppingCart.Product product1 = new ShoppingCart.Product("Headphones", 2000);
		ShoppingCart.Product product2 = new ShoppingCart.Product("Sweatshirt", 500);
		Order order = new Order();
		
		//TESTING
		cart.addProduct(product1);
        cart.addProduct(product2);
		cart.showProducts();
		cart.removeProduct(product2);
		cart.showProducts();
		
		Product originalProduct = new ConcreteProduct(2000, "Headphones");
		Product discountedProduct = new DiscountDecorator(originalProduct, 0.2);
		showDiscountedProducts(discountedProduct);
		
		PaymentMethod creditCard = PaymentMethodFactory.createPaymentMethod("Credit Card", true, 3);
		PaymentMethod debitCard = PaymentMethodFactory.createPaymentMethod("Debit Card", false, 0);
		PaymentMethod cash = PaymentMethodFactory.createPaymentMethod("Cash On Delivery", false, 0);
		
		
		System.out.println("\nPayment Status:");
		creditCard.showPaymentInfo();
		//debitCard.showPaymentInfo();
		//cash.showPaymentInfo();
		System.out.println();
        
        OrderObserver emailObserver = new EmailNotificationObserver();
        OrderObserver smsObserver = new SMSNotificationObserver();

        order.addObserver(emailObserver);
        order.addObserver(smsObserver);

        //Notify observers when order status changes with SMS and email
        System.out.println("Order Status:");
        //order.setStatus(OrderStatus.PLACED);
        //order.setStatus(OrderStatus.DISPATCHED);
        order.setStatus(OrderStatus.DELIVERED);
	}
}
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	


