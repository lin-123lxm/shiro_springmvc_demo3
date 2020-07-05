package com.huikedu.mapper;

import com.huikedu.domain.Permission;
import com.huikedu.domain.RolePermissions;
import java.util.List;
import java.util.Map;

public interface PermissionMapper extends Imaper<Permission> {

	/*
	删除角色和权限关联（主要是删除角色和权限的中间表）
	 */
	Boolean delRolePermission(RolePermissions rp);
	/*
	添加角色和权限的关联(添加角色和权限的中间表)
	 */
	Boolean addRolePermission(RolePermissions rp);

	/*
	分页查询
	 */
	List<Permission> selectByPage(Map<String, Long> map);
	

	Long selectCount();

	List<RolePermissions> selectRolePermission(RolePermissions rp);
	
}
