package com.huikedu.services;

import com.huikedu.domain.Permission;
import com.huikedu.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl extends  IBaseSeviceImpl implements  PermissionService {

    @Autowired
    PermissionMapper permissionMapper ;

    @Override
    public List<Permission> listAllPermissions(Permission permission) {
        return permissionMapper.select(permission);
    }
}
