package com.it.ssm.service;

import com.it.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll(Integer page, Integer size);

    void save(Permission permission);

    Permission findById(String id);
}
