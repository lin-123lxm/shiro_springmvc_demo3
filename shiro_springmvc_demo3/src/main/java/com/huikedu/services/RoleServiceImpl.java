package com.huikedu.services;


import com.huikedu.domain.RolePermissions;
import com.huikedu.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl extends IBaseSeviceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List queryAllroles() {
        return roleMapper.select(null);
    }

    @Override
    public List selectBypage(Long startRecord, Long pageSize) {
        Map<String, Object> paramMaps = new HashMap<>();
        paramMaps.put("startRecord", startRecord);
        paramMaps.put("pageSize", pageSize);
        return roleMapper.selectByPage(paramMaps);
    }
    @Override
    public Long selectCount() {
        return roleMapper.selectCount();
    }

    @Override
    public List<RolePermissions> listRolePermissions(Long roleId) {
        RolePermissions rolePermissions = new RolePermissions();
        rolePermissions.setRoleId(roleId);
        return roleMapper.listallRolePermission(rolePermissions);
    }
}
