select product.*, img.product_img, price.product_price, wh.product_quantity, img3D.product_img as product_img3d
from product product
left join product_img2D img on product.product_id = img.id_product_img2D
left join product_img3D img3D on product.product_id = img3D.id_product_img3D
left join product_price price on product.product_id = price.product_price_id
left join warehouse wh on product.product_id = wh.product
where product_disable = 0 and product_id = /*product_id*/ 