package edu.ben.models;

public class Orderline {
	private int idOrderline;
	private int idOrder;
	private int idProduct;
	private int quantity;
	private boolean active;
	private Product product;
	
	
	
	
	public Orderline(int idOrderline, int idOrder, int idProduct, int quantity, boolean active) {
		super();
		this.idOrderline = idOrderline;
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.active = active;
	}
	
	public Orderline(int idOrderline, int idOrder, int idProduct, int quantity, Product product) {
		super();
		this.idOrderline = idOrderline;
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.product = product;
	}
	
	
	
	public Orderline() {
		
	}
	
	public int getIdOrderline() {
		return idOrderline;
	}
	public void setIdOrderline(int idOrderline) {
		this.idOrderline = idOrderline;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Orderline [idOrderline=" + idOrderline + ", idOrder=" + idOrder + ", idProduct=" + idProduct
				+ ", quantity=" + quantity + ", active=" + active + ", product=" + product.toString() + "]";
	}
	
	
	
}
