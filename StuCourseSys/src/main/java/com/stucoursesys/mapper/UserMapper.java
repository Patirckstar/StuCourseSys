package com.stucoursesys.mapper;

import com.stucoursesys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    SysUser findByUsername(@Param("username") String username);

    SysUser findById(@Param("id") Long id);

    List<String> findRoleCodes(@Param("userId") Long userId);
}
