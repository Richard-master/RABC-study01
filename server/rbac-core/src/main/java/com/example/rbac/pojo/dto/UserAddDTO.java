package com.example.rbac.pojo.dto;

import com.example.common.pojo.dto.AbstractDTO;
import com.example.common.validator.Const;
import com.example.rbac.constant.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.example.rbac.pojo.example.UserExample.*;

/**
 * 新增【用户】的参数
 *
 * @author Jack
 * @date 2022/05/10
 */
@ApiModel(description = "新增【用户】的参数")
public class UserAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_USERNAME, example = E_USERNAME, required = true)
    @NotNull
    @Length(max = 32)
    private String username;

    @ApiModelProperty(notes = N_NICKNAME, example = E_NICKNAME, required = true)
    @NotNull
    @Length(max = 32)
    private String nickname;

    @ApiModelProperty(notes = N_SEX, example = E_SEX, required = true, allowableValues = Sex.VALUES_STR)
    @NotNull
    @Const(constClass = Sex.class)
    private Integer sex;

    @ApiModelProperty(notes = N_DEPT_ID, example = E_DEPT_ID, required = true)
    @NotNull
    private Integer deptId;

    private List<Long> roleList;


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public List<Long> getRoleList() {
        return this.roleList;
    }

    public void setRoleList(List<Long> roleList) {
        this.roleList = roleList;
    }

}


