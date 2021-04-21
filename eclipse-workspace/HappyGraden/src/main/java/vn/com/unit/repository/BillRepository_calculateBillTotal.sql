select sum(bill.price)
from
	(
		select (bill_item.quantity * product_price.product_price) as price
		from
			(
				select *
				from bill_item			
				where bill = /*bill_id*/''
			) as bill_item
		left join product 
		on product.product_id = bill_item.product
		left join product_price
		on product.product_id = product_price.product_price_id
	) as bill