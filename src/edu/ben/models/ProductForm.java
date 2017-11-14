package edu.ben.models;

public class ProductForm {
	private String artist;
	private String title;
	private String genre;
	private float price;
	private int stock;
	
	public ProductForm(String artist, String title, String genre, float price, int stock) {
		super();
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.stock = stock;
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
	
	

}
