select c.*
from import_product_card as c
ORDER BY c.imp_id desc
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY