package com.huikedu.services;



import com.huikedu.domain.RolePermissions;

import java.util.List;

public interface RoleService<Role> extends  IBaseService {
    /*
    显示所有角色
     */
    List<Role>  queryAllroles();

    List<Role>  selectBypage(Long startRecord , Long pageSize);

    Long selectCount();
    /**
     * 根据角色ID查询权限
     */
    List<RolePermissions> listRolePermissions(Long roleId);





}
