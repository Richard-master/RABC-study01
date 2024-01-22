package com.example.rbac.dao;

import com.example.common.dao.DAO;
import com.example.rbac.pojo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 【用户】数据库操作
 *
 * @author Jack
 * @date 2022/05/10
 */
@Repository
@Mapper
public interface UserDAO extends DAO<UserPO> {

    int getCountByDeptId(Integer deptId);

    int getCountByRole(Long roleId);

    int addRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int removeRole(@Param("userId") Long userId, @Param("roleId") Long[] roleId);

    int removeAllRole(Long userId);


}



