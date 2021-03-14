SELECT *
FROM origin
where origin_disable = 0
order by origin_id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY