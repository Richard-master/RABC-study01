package com.example.rbac.web.rest;

import com.example.common.util.JsonUtil;
import com.example.rbac.help.RoleHelper;
import com.example.rbac.pojo.dto.RoleAddDTO;
import com.example.rbac.pojo.dto.RoleUpdateDTO;
import com.example.rbac.pojo.po.RolePO;
import com.example.rbac.web.AbstractWebTest;
import com.example.rbac.web.constant.WebConst;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 【角色】单元测试
 *
 * @author Jack
 * @date 2022/05/10
 */
@DisplayName("【角色】Controller")
public class RoleControllerTest extends AbstractWebTest {

    @Autowired
    private RoleHelper roleHelper;


    @Test
    @DisplayName("新增【角色】")
    public void save() throws Exception {
        RoleAddDTO addDTO = roleHelper.getRoleAddDTO();
        restMockMvc.perform(post(WebConst.API_PATH + "/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(addDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("修改【角色】")
    public void update() throws Exception {
        RolePO role = roleHelper.saveRoleExample();
        RoleUpdateDTO updateDTO = roleHelper.getRoleUpdateDTO(role);
        restMockMvc.perform(put(WebConst.API_PATH + "/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(updateDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("分页查询【角色】")
    public void list() throws Exception {
        RolePO role = roleHelper.saveRoleExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/role"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.list.length()").value(is(1)));
    }

    @Test
    @DisplayName("查询【角色】选项列表")
    public void findOptions() throws Exception {
        RolePO role = roleHelper.saveRoleExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/role/options"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(is(1)));
    }

    @Test
    @DisplayName("查看【角色】详情")
    public void show() throws Exception {
        RolePO role = roleHelper.saveRoleExample();
        restMockMvc.perform(get(WebConst.API_PATH + "/role/{id}", role.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("删除单个【角色】")
    public void del() throws Exception {
        RolePO role = roleHelper.saveRoleExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/role/{id}", role.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }

    @Test
    @DisplayName("批量删除【角色】")
    public void deleteBatch() throws Exception {
        RolePO role = roleHelper.saveRoleExample();
        restMockMvc.perform(delete(WebConst.API_PATH + "/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJSONString(Lists.newArrayList(role.getId()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(is(1)));
    }


}
