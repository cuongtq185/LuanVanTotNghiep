select product.*, p.product_price, img.product_img
from product
left join product_img2D as img on product.product_id = img.id_product_img2D
left join product_price as p on product.product_id = p.product_price_id
where 1=1 
	/*IF name != '' and name != null*/
	 and product_name like CONCAT('%',/*name*/'','%')
	/*END*/