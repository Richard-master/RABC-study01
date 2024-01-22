package com.example.rbac.pojo.mapper;

import com.example.rbac.pojo.dto.DepartmentAddDTO;
import com.example.rbac.pojo.dto.DepartmentUpdateDTO;
import com.example.rbac.pojo.po.DepartmentPO;
import com.example.rbac.pojo.vo.DepartmentShowVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 【部门】映射
 *
 * @author Jack
 * @date 2022/05/10
 */
@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    /**
     * addDTO映射po
     *
     * @param departmentAddDTO
     * @return
     */
    DepartmentPO fromAddDTO(DepartmentAddDTO departmentAddDTO);

    /**
     * 将updateDTO中的值设置到po
     *
     * @param departmentPO
     * @param departmentUpdateDTO
     */
    void setUpdateDTO(@MappingTarget DepartmentPO departmentPO, DepartmentUpdateDTO departmentUpdateDTO);

    /**
     * po映射showVO
     *
     * @param departmentPO
     * @return
     */
    DepartmentShowVO toShowVO(DepartmentPO departmentPO);


}

