package com.example.rbac.help;

import com.example.rbac.pojo.dto.RoleAddDTO;
import com.example.rbac.pojo.dto.RoleUpdateDTO;
import com.example.rbac.pojo.po.RolePO;
import com.example.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.rbac.pojo.example.RoleExample.E_CODE;
import static com.example.rbac.pojo.example.RoleExample.E_ROLE_NAME;

@Component
public class RoleHelper {

    @Autowired
    private RoleService roleService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public RoleAddDTO getRoleAddDTO() {
        RoleAddDTO dto = new RoleAddDTO();
        dto.setCode(E_CODE);
        dto.setRoleName(E_ROLE_NAME);
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public RoleUpdateDTO getRoleUpdateDTO(RolePO role) {
        RoleUpdateDTO dto = new RoleUpdateDTO();
        dto.setId(role.getId());
        dto.setCode(role.getCode());
        dto.setRoleName(role.getRoleName());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public RolePO saveRoleExample() {
        RoleAddDTO addDTO = this.getRoleAddDTO();
        return roleService.save(addDTO);
    }


}

