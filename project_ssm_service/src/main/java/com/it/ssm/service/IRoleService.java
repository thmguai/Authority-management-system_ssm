package com.it.ssm.service;

import com.it.ssm.domain.Permission;
import com.it.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll(Integer page, Integer size);

    void save(Role role);

    Role findRoleById(String id);

    List<Permission> findOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionId);

    Role findById(String id);
}
