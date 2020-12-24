SELECT *, pr.product_quantity
FROM product
left join warehouse pr
on product_id = product
order by product_id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY