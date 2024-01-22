package com.example.rbac.web.api;

import com.example.common.pojo.qo.OptionQO;
import com.example.common.pojo.vo.OptionVO;
import com.example.common.pojo.vo.PageVO;
import com.example.rbac.pojo.dto.DepartmentAddDTO;
import com.example.rbac.pojo.dto.DepartmentUpdateDTO;
import com.example.rbac.pojo.qo.DepartmentQO;
import com.example.rbac.pojo.vo.DepartmentListVO;
import com.example.rbac.pojo.vo.DepartmentShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 【部门】API
 * <p>swagger接口文档
 *
 * @author Jack
 * @date 2022/05/10
 */
@Api(tags = "【部门】API")
public interface DepartmentAPI {

    /**
     * 新增【部门】
     */
    @ApiOperation(value = "新增【部门】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "departmentAddDTO", dataTypeClass = DepartmentAddDTO.class, value = "新增【部门】参数", paramType = "body"),
    })
    ResponseEntity<DepartmentShowVO> save(DepartmentAddDTO departmentAddDTO) throws Exception;

    /**
     * 修改【部门】
     */
    @ApiOperation(value = "修改【部门】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "departmentUpdateDTO", dataTypeClass = DepartmentUpdateDTO.class, value = "修改【部门】参数", paramType = "body"),
    })
    ResponseEntity<DepartmentShowVO> update(DepartmentUpdateDTO departmentUpdateDTO);

    /**
     * 分页查询【部门】
     */
    @ApiOperation(value = "分页查询【部门】")
    ResponseEntity<PageVO<DepartmentListVO>> list(DepartmentQO departmentQO);

    /**
     * 查询【部门】选项列表
     */
    @ApiOperation(value = "查询【部门】选项列表")
    ResponseEntity<List<OptionVO<Integer, String>>> findOptions(OptionQO<Integer, String> qo);

    /**
     * 查看【部门】详情
     */
    @ApiOperation(value = "查看【部门】详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = Integer.class, value = "【部门】id", paramType = "path"),
    })
    ResponseEntity<DepartmentShowVO> show(Integer id);

    /**
     * 删除单个【部门】
     */
    @ApiOperation(value = "删除单个【部门】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = Integer.class, value = "【部门】id", paramType = "path"),
    })
    ResponseEntity<Integer> delete(Integer id);

    /**
     * 批量删除【部门】
     */
    @ApiOperation(value = "批量删除【部门】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataTypeClass = Integer.class, allowMultiple = true, value = "id数组", paramType = "body"),
    })
    ResponseEntity<Integer> deleteBatch(Integer[] id);

}

