package vn.com.unit.dto;

import vn.com.unit.entity.Product;

public class ProductDto extends Product {

	private String originName;
	
	private String categoryName;
	
	private int productPrice;
	
	private String productImg;
	
	private int originId;
	
	private int categoryId; 
	
	private String impDetail;
	
	private int impQuantity;
	
	private int impProductPrice;
	
	public int getOriginId() {
		return originId;
	}


	public void setOriginId(int originId) {
		this.originId = originId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}


	public ProductDto() {
		// TODO Auto-generated constructor stub
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}


	public String getImpDetail() {
		return impDetail;
	}


	public void setImpDetail(String impDetail) {
		this.impDetail = impDetail;
	}


	public int getImpQuantity() {
		return impQuantity;
	}


	public void setImpQuantity(int impQuantity) {
		this.impQuantity = impQuantity;
	}


	public int getImpProductPrice() {
		return impProductPrice;
	}


	public void setImpProductPrice(int impProductPrice) {
		this.impProductPrice = impProductPrice;
	}

	
//	public Product extractProduct() {
//		return (Product) this;
//	}
	
}
