package edu.ben.models;

import java.util.ArrayList;

public class Order {
	private int idOrder;
	private int idUser;
	private String shippingAddress;
	private String shippingName;
	private int shippingType;
	private boolean shipped;
	private boolean isActive;
	private String username;
	private ArrayList<Orderline> lines = new ArrayList<Orderline>();
	
	/**
	 * Constructor
	 * @param idOrder int order id
	 * @param idUser in user id
	 * @param shippingAddress string shipping address
	 * @param shippingName string shipping name
	 * @param shippingType string shipping type
	 * @param shipped boolean has shipped
	 * @param isActive boolean is active
	 */
	public Order(int idOrder, int idUser, String shippingAddress, String shippingName, int shippingType,
			boolean shipped, boolean isActive) {
		super();
		this.idOrder = idOrder;
		this.idUser = idUser;
		this.shippingAddress = shippingAddress;
		this.shippingName = shippingName;
		this.shippingType = shippingType;
		this.shipped = shipped;
		this.isActive = isActive;
	}
	
	public Order(int idOrder, String username, String shippingAddress, String shippingName, int shippingType,
			boolean shipped, boolean isActive) {
		super();
		this.idOrder = idOrder;
		this.username = username;
		this.shippingAddress = shippingAddress;
		this.shippingName = shippingName;
		this.shippingType = shippingType;
		this.shipped = shipped;
		this.isActive = isActive;
	}
	public Order() {
		
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public int getShippingType() {
		return shippingType;
	}
	public void setShippingType(int shippingType) {
		this.shippingType = shippingType;
	}
	public boolean isShipped() {
		return shipped;
	}
	public void setShipped(boolean shipped) {
		this.shipped = shipped;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public ArrayList<Orderline> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Orderline> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		String line="";
		for(Orderline l:lines) {
			line += l.toString() + " \n";
		}
		return "Order [idOrder=" + idOrder + ", idUser=" + idUser + ", shippingAddress=" + shippingAddress
				+ ", shippingName=" + shippingName + ", shippingType=" + shippingType + ", shipped=" + shipped
				+ ", isActive=" + isActive + ", username=" + username + ", lines=" + line + "]";
	}
	
	

}
