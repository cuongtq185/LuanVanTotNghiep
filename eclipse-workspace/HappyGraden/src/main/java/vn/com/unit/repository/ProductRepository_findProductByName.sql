select *
from product
/*IF name != '' and name != null*/
where product_name like '%' + /*name*/ +'%'
/*END*/