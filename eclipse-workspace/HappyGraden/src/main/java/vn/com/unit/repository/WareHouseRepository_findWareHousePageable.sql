SELECT *, product_id AS product, pr.product_quantity
FROM product
left join warehouse pr
on product_id = product
where product_disable = 0
order by product_id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY