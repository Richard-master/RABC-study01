package com.example.rbac.pojo.mapper;

import com.example.rbac.pojo.dto.UserAddDTO;
import com.example.rbac.pojo.dto.UserUpdateDTO;
import com.example.rbac.pojo.po.UserPO;
import com.example.rbac.pojo.vo.UserShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【用户】映射
 *
 * @author Jack
 * @date 2022/05/10
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * addDTO映射po
     *
     * @param userAddDTO
     * @return
     */
    UserPO fromAddDTO(UserAddDTO userAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param userPO
     * @param userUpdateDTO
     */
    void setUpdateDTO(@MappingTarget UserPO userPO, UserUpdateDTO userUpdateDTO);

    /**
     * po映射showVO
     *
     * @param userPO
     * @return
     */
    UserShowVO toShowVO(UserPO userPO);


}

