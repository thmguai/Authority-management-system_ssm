package com.it.ssm.dao;

import com.it.ssm.domain.Permission;
import com.it.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.it.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRolesByUserId(String userId);


    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(rolename,roledesc) values(#{roleName},#{roleDesc})")
    void save(Role role);


    @Select("select * from role where id=#{id}")
    Role findRoleById(String id);


    @Select("select * from Permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermission(String id);


    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.it.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String id);
}
