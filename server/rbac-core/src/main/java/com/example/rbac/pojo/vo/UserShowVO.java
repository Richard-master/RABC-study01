package com.example.rbac.pojo.vo;

import com.example.common.pojo.vo.AbstractVO;
import com.example.rbac.constant.Sex;
import com.example.rbac.pojo.example.RoleExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import static com.example.rbac.pojo.example.UserExample.*;

/**
 * 【用户】详情展示对象
 *
 * @author Jack
 * @date 2022/05/10
 */
@ApiModel(description = "【用户】详情展示对象")
public class UserShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private Long id;

    @ApiModelProperty(notes = N_USERNAME, example = E_USERNAME)
    private String username;

    @ApiModelProperty(notes = N_NICKNAME, example = E_NICKNAME)
    private String nickname;

    @ApiModelProperty(notes = N_SEX, example = E_SEX, allowableValues = Sex.VALUES_STR)
    private Integer sex;

    @ApiModelProperty(notes = N_DEPT_ID, example = E_DEPT_ID)
    private Integer deptId;

    @ApiModelProperty(notes = "【角色】列表")
    private List<RoleVO> roleList;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<RoleVO> getRoleList() {
        return this.roleList;
    }

    public void setRoleList(List<RoleVO> roleList) {
        this.roleList = roleList;
    }

    public static class RoleVO extends AbstractVO {

        @ApiModelProperty(notes = RoleExample.N_ID, example = RoleExample.E_ID)
        private Long id;

        @ApiModelProperty(notes = RoleExample.N_CODE, example = RoleExample.E_CODE)
        private String code;

        @ApiModelProperty(notes = RoleExample.N_ROLE_NAME, example = RoleExample.E_ROLE_NAME)
        private String roleName;


        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRoleName() {
            return this.roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

    }


}

