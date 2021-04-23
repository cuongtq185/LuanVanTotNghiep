package vn.com.unit.dto;

import vn.com.unit.entity.BillItem;

public class BillItemDto extends BillItem {

	private String productName;
	 
	private String categoryName;
	
	private String originName;

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	private String img;

	private float productPrice;

	public BillItemDto() {
	}

	public BillItemDto(BillItem billItem) {
		super(billItem);
	}

	public String getProductName() {
		return productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public float getTotal() {
		return this.getProductPrice() * this.getQuantity();
	}
	
	public BillItem extractBillItem( ) {
		return (BillItem) this;
	}
	
}
