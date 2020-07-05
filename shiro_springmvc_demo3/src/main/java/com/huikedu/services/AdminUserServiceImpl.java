package com.huikedu.services;

/*
 用户业务组件
 */

import com.huikedu.common.PasswordHelper;
import com.huikedu.domain.AdminUser;
import com.huikedu.domain.AdminUserRole;
import com.huikedu.mapper.AdminUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("adminUserService")
public class AdminUserServiceImpl extends IBaseSeviceImpl implements AdminUserService {

    @Autowired
    AdminUserMapper adminUserMapper;
    Map<String, Long> maps = new HashMap<>();

    @Override
    public List<AdminUser> selectByPage(Long currPage, Long pageSize) {
        maps.put("currPage", currPage);
        maps.put("pageSize", pageSize);
        return adminUserMapper.selectByPage(maps);
    }

    @Override
    public Long selectCount() {
        return adminUserMapper.selectCount();
    }

    //添加用户
    @Override
    public Boolean addAdminUserRole(String account, String password, String checkpid) {
        try {
            AdminUser adminUser = new AdminUser();
            adminUser.setAccount(account);
            adminUser.setPassword(password);
            //生成密码和盐值
            PasswordHelper.encryptPassword(adminUser);
            adminUser.setIsDeleted(false);
            adminUser.setIsDisabled(false);
            // 得到刚保存的Adminuser对象的Id
            adminUserMapper.insert(adminUser);
            if (null != checkpid && !"".equals(checkpid)) {
                String roleIds[] = checkpid.split(",");
                for (String roleId : roleIds) {
                    //向用户角色的中间表，添加用户所关联的角色信息。
                    AdminUserRole adminUserRole = new AdminUserRole();
                    adminUserRole.setAdminUserId(adminUser.getId());
                    adminUserRole.setRoleId(Long.parseLong(roleId));
                    adminUserMapper.addAdminUserRole(adminUserRole);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkUsername(String account) {
        /**
         * adminusermapper接口
         */
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount(account);
        List<AdminUser> adminUserList = adminUserMapper.select(adminUser);
        // 此处由大家添加代码
        if (adminUserList == null || adminUserList.size() == 0) {
            // 不存在重复
            return true;
        }
        //存在重复
        return false;
    }


    public List<AdminUserRole> selectAdminUserRole(AdminUserRole adminUserRole) {
        return adminUserMapper.selectAdminUserRole(adminUserRole);
    }

    @Override
    public boolean updateAdminUserRolesubmit(String adminUserId, String roleId[]) {
        AdminUserRole adminUserRole = new AdminUserRole();
        adminUserRole.setAdminUserId(Long.parseLong(adminUserId));// 用户ID
        //2\删除用户原来已有角色，删除降中间表和角色的关联sys_user_roles
        adminUserMapper.delAdminUserRole(adminUserRole);
        if (roleId != null && !"".equals(roleId)) {
            //3、重新添加
            for (String roleid : roleId) {
                AdminUserRole adminUserrole = new AdminUserRole();//用户角色中间表对象
                adminUserrole.setAdminUserId(Long.parseLong(adminUserId));
                adminUserrole.setRoleId(Long.parseLong(roleid));
                adminUserMapper.addAdminUserRole(adminUserrole);
            }
        }
        return true;
    }

    @Override
    public boolean updateAdminUserDisable(String adminUserId) {
        /**
         * 1、根据ID查询出当前AdminUser对象
         * 2、根据AdminUser对象的状态来判断是启用还是禁用
         */
        AdminUser adminUser = adminUserMapper.selectAdminUserById(Long.parseLong(adminUserId));
        if (adminUser.getIsDisabled()) {
            adminUser.setIsDisabled(false);
        }else {
            adminUser.setIsDisabled(true);
        }
        return  adminUserMapper.updateAdminUserDisable(adminUser);
    }

    @Override
    public AdminUser QueryUserByName(String username) {
        return adminUserMapper.QueryUserByName(username);
    }

    @Override
    public List<String> findRolesByUserName(String username) {
        AdminUser  adminUser =  adminUserMapper.QueryUserByName(username);
        if (adminUser == null ){
            return Collections.EMPTY_LIST;
        }
        return adminUserMapper.findRolesById(adminUser.getId());
    }

    @Override
    public List<String> findPermissionsByUserName(String username) {
        AdminUser  adminUser =  adminUserMapper.QueryUserByName(username);
        if (adminUser == null ){
            return Collections.EMPTY_LIST;
        }
        return adminUserMapper.findPermissionsById(adminUser.getId());
    }


}

