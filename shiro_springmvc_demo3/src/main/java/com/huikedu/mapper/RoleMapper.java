package com.huikedu.mapper;


import com.huikedu.domain.Role;
import com.huikedu.domain.RolePermissions;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends  Imaper<Role> {


    /*
    列表
     */
    List<Role> selectByPage(Map   maps);


    /*
    查询记录条数
     */

    Long selectCount();

    /**
     * 根据RoleID查询所有角色权限对象
     */
    List<RolePermissions> listallRolePermission(RolePermissions rolePermissions );


}
