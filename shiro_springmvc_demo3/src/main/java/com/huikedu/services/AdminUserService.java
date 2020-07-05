package com.huikedu.services;

import com.huikedu.domain.AdminUser;
import com.huikedu.domain.AdminUserRole;


import java.util.List;
import java.util.Set;

public interface AdminUserService extends  IBaseService {

    /*
    查询指定区间的记录
     */
    List<AdminUser> selectByPage(Long currPage, Long pageSize);

        /*
        查询总记录数
         */
    Long selectCount();

    /**
     * 添加用户信息,同时添加用户关联的角色
     * account
     * password
     *checkpid
     */
    Boolean addAdminUserRole(String account, String password, String checkpid);

    /**
     * 账号重名验证
     */
    Boolean checkUsername(String account);

    /**
     * 查询用户的角色
     * @param adminUserRole
     * @return
     */
    List<AdminUserRole>  selectAdminUserRole(AdminUserRole adminUserRole);

    /**
     *修改用户关联的角色
     */
    boolean updateAdminUserRolesubmit(String adminUserId, String roleId[]);

    /**
     * 启用/禁用用户
     */
    boolean updateAdminUserDisable(String adminUserId);


    /**
     *查询出当前用户对象
     */
    AdminUser QueryUserByName(String username );

    /**
     * 查询当前账号的所有角色
     */
    List<String> findRolesByUserName(String username);

    /**
     * 查询当前账号的所有权限
     */
    List<String> findPermissionsByUserName(String username);

}





