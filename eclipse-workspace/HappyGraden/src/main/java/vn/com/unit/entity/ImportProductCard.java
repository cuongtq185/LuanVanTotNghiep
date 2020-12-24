package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "import_product_card")
public class ImportProductCard {
	
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "product")
	private Long product;
	
	@Column(name = "imp_detail")
	private Long impDetail;
	
	@Column(name = "imp_createAt")
	private Long impCreateAt; 	
	
	@Column(name = "imp_quantity")
	private Long impQuantity;
	
	@Column(name = "imp_product_price")
	private Long impProductPrice;

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Long getImpDetail() {
		return impDetail;
	}

	public void setImpDetail(Long impDetail) {
		this.impDetail = impDetail;
	}

	public Long getImpCreateAt() {
		return impCreateAt;
	}

	public void setImpCreateAt(Long impCreateAt) {
		this.impCreateAt = impCreateAt;
	}

	public Long getImpQuantity() {
		return impQuantity;
	}

	public void setImpQuantity(Long impQuantity) {
		this.impQuantity = impQuantity;
	}

	public Long getImpProductPrice() {
		return impProductPrice;
	}

	public void setImpProductPrice(Long impProductPrice) {
		this.impProductPrice = impProductPrice;
	}
	
	

}
