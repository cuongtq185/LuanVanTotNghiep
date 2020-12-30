--RoleRepository_findRoleByAccountId => tìm quyền bởi id người dùng
SELECT *
FROM ROLE
WHERE ROLE_ID IN (SELECT ROLE
				  FROM ACCOUNT
				  WHERE ACCOUNT_ID = /*account_id*/)