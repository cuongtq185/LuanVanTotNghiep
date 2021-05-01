SELECT b.*, product_id AS product, pr.product_quantity, a.product_price
FROM product as b
left join warehouse pr
on b.product_id = product
left join product_price as a
on b.product_id = a.product_price_id
where b.product_disable = 0
order by b.product_id desc
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY