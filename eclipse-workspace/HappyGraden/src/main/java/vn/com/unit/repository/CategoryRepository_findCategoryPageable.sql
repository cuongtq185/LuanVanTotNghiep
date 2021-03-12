SELECT *
FROM category
WHERE category_disable = 0
order by category_id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY