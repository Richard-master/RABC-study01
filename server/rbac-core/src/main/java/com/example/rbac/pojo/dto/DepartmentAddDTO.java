package com.example.rbac.pojo.dto;

import com.example.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.example.rbac.pojo.example.DepartmentExample.E_DEPT_NAME;
import static com.example.rbac.pojo.example.DepartmentExample.N_DEPT_NAME;

/**
 * 新增【部门】的参数
 *
 * @author Jack
 * @date 2022/05/10
 */
@ApiModel(description = "新增【部门】的参数")
public class DepartmentAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_DEPT_NAME, example = E_DEPT_NAME, required = true)
    @NotNull
    @Length(max = 33)
    private String deptName;


    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}


