select a.*, b.product_name
from product_price as a
left join product as b 
on b.product_id = a.product_price_id
where b.product_disable = 0
ORDER BY b.product_id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY