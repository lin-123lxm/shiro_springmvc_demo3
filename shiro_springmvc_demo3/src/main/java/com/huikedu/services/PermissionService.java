package com.huikedu.services;

import com.huikedu.domain.Permission;

import java.util.List;

public interface PermissionService extends  IBaseService {


    List<Permission> listAllPermissions(Permission permission);

}
