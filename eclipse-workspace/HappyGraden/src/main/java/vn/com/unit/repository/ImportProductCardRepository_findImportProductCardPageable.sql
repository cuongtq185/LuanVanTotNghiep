select c.*, p.product_name
from import_product_card as c
left join product as p on p.product_id  = c.product
where product_disable = 0
ORDER BY c.product
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY