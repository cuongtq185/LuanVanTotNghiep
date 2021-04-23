
select bill_item.*, product.product_name, product_price.product_price 
from bill_item as bill_item
left join product as product
	on product.product_id = bill_item.product
left join product_price as product_price
	on product_price.product_price_id = bill_item.product
where bill_item.bill = /*bill_id*/''
