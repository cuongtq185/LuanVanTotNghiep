package vn.com.unit.dto;

import vn.com.unit.entity.CartItem;

public class CartDto extends CartItem {

	private String productImg;

	private String productName;

	private float productPrice;
	
	private int quantity;
	
	private Long product;

	
	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return this.getProductPrice() * this.getQuantity();
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}
	
//	public CartItem extractCart() {
//		return (CartItem) this;
//	}
	
}
