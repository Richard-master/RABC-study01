package com.example.rbac.web.api;

import com.example.common.pojo.vo.PageVO;
import com.example.rbac.pojo.dto.UserAddDTO;
import com.example.rbac.pojo.dto.UserUpdateDTO;
import com.example.rbac.pojo.qo.UserQO;
import com.example.rbac.pojo.vo.UserListVO;
import com.example.rbac.pojo.vo.UserShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

/**
 * 【用户】API
 * <p>swagger接口文档
 *
 * @author Jack
 * @date 2022/05/10
 */
@Api(tags = "【用户】API")
public interface UserAPI {

    /**
     * 新增【用户】
     */
    @ApiOperation(value = "新增【用户】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAddDTO", dataTypeClass = UserAddDTO.class, value = "新增【用户】参数", paramType = "body"),
    })
    ResponseEntity<UserShowVO> save(UserAddDTO userAddDTO) throws Exception;

    /**
     * 修改【用户】
     */
    @ApiOperation(value = "修改【用户】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUpdateDTO", dataTypeClass = UserUpdateDTO.class, value = "修改【用户】参数", paramType = "body"),
    })
    ResponseEntity<UserShowVO> update(UserUpdateDTO userUpdateDTO);

    /**
     * 分页查询【用户】
     */
    @ApiOperation(value = "分页查询【用户】")
    ResponseEntity<PageVO<UserListVO>> list(UserQO userQO);

    /**
     * 查看【用户】详情
     */
    @ApiOperation(value = "查看【用户】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = Long.class, value = "【用户】id", paramType = "path"),
    })
    ResponseEntity<UserShowVO> show(Long id);

    /**
     * 删除单个【用户】
     */
    @ApiOperation(value = "删除单个【用户】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = Long.class, value = "【用户】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(Long id);

    /**
     * 批量删除【用户】
     */
    @ApiOperation(value = "批量删除【用户】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = Long.class, allowMultiple = true, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(Long[] id);

}

