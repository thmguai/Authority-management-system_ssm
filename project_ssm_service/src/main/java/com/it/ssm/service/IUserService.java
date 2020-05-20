package com.it.ssm.service;

import com.it.ssm.domain.Role;
import com.it.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(Integer page, Integer size);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId, String[] ids);

    void disableById(String id);

    void ableById(String id);
}
