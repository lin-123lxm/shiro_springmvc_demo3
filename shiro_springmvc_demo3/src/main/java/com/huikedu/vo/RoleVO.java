package com.huikedu.vo;

//添加角色页面的表单
public class RoleVO {

    // 名称
    private  String roleName ;
    //描述
    private  String roleDesc ;
    // 角色持有的权限ID
    private  String rolePerssionId [];

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String[] getRolePerssionId() {
        return rolePerssionId;
    }

    public void setRolePerssionId(String[] rolePerssionId) {
        this.rolePerssionId = rolePerssionId;
    }
}
