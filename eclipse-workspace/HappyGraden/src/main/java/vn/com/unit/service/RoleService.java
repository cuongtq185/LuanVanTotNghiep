package vn.com.unit.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.Role;

public interface RoleService {
	
	/* kiểm tra role khi login */
	public List<GrantedAuthority> findAuthorities(Account account);
	
	/* tìm quyền bởi người dùng */
	public List<Role> findRoleByAccount(Account account);
	
	/* tìm quyền bởi id người dùng */
	public List<Role> findRoleByAccountId(Long accountId);
	
	/* tìm id role bởi role name */
	public Long findRoleIdByName(String role_name);
}
