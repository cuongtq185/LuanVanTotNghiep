package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "warehouse")
public class WareHouse {


	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "product")
	private Long product;

	@Column(name = "product_quantity")
	private String productQuantity;
	
	private String productName;
	
	private float productPrice;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
	
}
