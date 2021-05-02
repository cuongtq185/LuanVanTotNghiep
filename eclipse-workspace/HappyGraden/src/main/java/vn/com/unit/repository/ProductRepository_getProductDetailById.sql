select b.*, a.category_name, c.origin_name, d.product_price, e.product_quantity, img.product_img
from product as b
left join category as a on a.category_id = b.category
left join origin as c on c.origin_id = b.origin
left join product_price as d on d.product_price_id = b.product_id
left join warehouse as e on e.product = b.product_id
left join product_img2D as img on img.id_product_img2D = b.product_id
where b.product_id = /*id*/'' and b.product_disable = 0