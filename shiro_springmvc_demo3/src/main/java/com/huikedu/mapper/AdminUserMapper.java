package com.huikedu.mapper;

import com.huikedu.domain.AdminUser;
import com.huikedu.domain.AdminUserRole;

import java.util.List;
import java.util.Map;

public interface AdminUserMapper  extends  Imaper{

    /*
    实现分页查询的方法
     */
    List<AdminUser> selectByPage(Map<String, Long> maps);

    /*
    查询总记录条数
     */
    Long selectCount();

    /*
    添加用户角色信息表
     */
    boolean addAdminUserRole(AdminUserRole adminUserRole) ;

    /**
     * 删除用户角色（删除用户-角色中间表的记录），
     */
    boolean delAdminUserRole(AdminUserRole adminUserRole);

    /**
     * 根据用户信息查询当前用户的角色ID列表
     */
    List<AdminUserRole> selectAdminUserRole(AdminUserRole aur);

    /**
     * 根据ID查询AdminUser对象
     */
    AdminUser selectAdminUserById(Long adminUserId);
    /**
     * 修改用户状态：启用|禁用
     */
    boolean updateAdminUserDisable(AdminUser adminUser);

    /**
     * 查询账号
     */
    AdminUser QueryUserByName(String account);

    /*
    查询当前用户的角色
     */
    List<String> findRolesById(Long adminUserId);
    /**
     * 查询当前用户的所有权限
     */
    List<String> findPermissionsById(Long adminUserId);


}
