package vn.com.unit.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "import_product_card")
public class ImportProductCard {

	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "imp_id")
	private Long impId;

	@Column(name = "product")
	private Long product;

	@Column(name = "imp_detail")
	private String impDetail;

	@Column(name = "imp_createAt")
	private Date impCreateAt;

	@Column(name = "imp_quantity")
	private int impQuantity;

	@Column(name = "imp_product_price")
	private float impProductPrice;
	
	private String productName;

	public Long getImpId() {
		return impId;
	}

	public void setImpId(Long impId) {
		this.impId = impId;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public String getImpDetail() {
		return impDetail;
	}

	public void setImpDetail(String impDetail) {
		this.impDetail = impDetail;
	}

	public Date getImpCreateAt() {
		return impCreateAt;
	}

	public void setImpCreateAt(Date impCreateAt) {
		this.impCreateAt = impCreateAt;
	}

	public int getImpQuantity() {
		return impQuantity;
	}

	public void setImpQuantity(int impQuantity) {
		this.impQuantity = impQuantity;
	}

	public float getImpProductPrice() {
		return impProductPrice;
	}

	public void setImpProductPrice(float impProductPrice) {
		this.impProductPrice = impProductPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

}
