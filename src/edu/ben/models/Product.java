package edu.ben.models;

public class Product {
	private int idProduct;
	private String artist;
	private String title;
	private String genre;
	private float price;
	private int stock;
	private String url;;
	
	
	public Product(int idProduct, String artist, String title, String genre, float price, int stock) {
		this.idProduct = idProduct;
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.stock = stock;
		this.url = "/img/anti-Rihanna.jpg";
	}
	
	public Product() {
		this.url = "/img/anti-Rihanna.jpg";
	}
	
	
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idCd) {
		this.idProduct = idCd;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", artist=" + artist + ", title=" + title + ", genre=" + genre
				+ ", price=" + price + ", stock=" + stock + ", url=" + url + "]";
	}
	
	
}
