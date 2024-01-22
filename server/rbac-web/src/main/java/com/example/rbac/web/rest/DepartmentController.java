package com.example.rbac.web.rest;

import com.example.common.constant.ErrorCode;
import com.example.common.exception.BusinessException;
import com.example.common.pojo.qo.OptionQO;
import com.example.common.pojo.vo.OptionVO;
import com.example.common.pojo.vo.PageVO;
import com.example.rbac.pojo.dto.DepartmentAddDTO;
import com.example.rbac.pojo.dto.DepartmentUpdateDTO;
import com.example.rbac.pojo.mapper.DepartmentMapper;
import com.example.rbac.pojo.po.DepartmentPO;
import com.example.rbac.pojo.qo.DepartmentQO;
import com.example.rbac.pojo.vo.DepartmentListVO;
import com.example.rbac.pojo.vo.DepartmentShowVO;
import com.example.rbac.service.DepartmentService;
import com.example.rbac.web.AbstractController;
import com.example.rbac.web.api.DepartmentAPI;
import com.example.rbac.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * 【部门】控制器
 *
 * @author Jack
 * @date 2022/05/10
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/department")
public class DepartmentController extends AbstractController implements DepartmentAPI {

    @Autowired
    private DepartmentService departmentService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DepartmentShowVO> save(@Valid @RequestBody DepartmentAddDTO departmentAddDTO) throws Exception {
        DepartmentPO department = departmentService.save(departmentAddDTO);
        return ResponseEntity.created(new URI(WebConst.API_PATH + "/department/" + department.getId()))
                .body(DepartmentMapper.INSTANCE.toShowVO(department));
    }

    @Override
    @PutMapping
    public ResponseEntity<DepartmentShowVO> update(@Valid @RequestBody DepartmentUpdateDTO departmentUpdateDTO) {
        DepartmentPO department = departmentService.update(departmentUpdateDTO);
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.toShowVO(department));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<DepartmentListVO>> list(@Valid DepartmentQO departmentQO) {
        PageVO<DepartmentListVO> page = departmentService.list(departmentQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/options")
    public ResponseEntity<List<OptionVO<Integer, String>>> findOptions(OptionQO<Integer, String> qo) {
        List<OptionVO<Integer, String>> options = departmentService.findOptions(qo);
        return ResponseEntity.ok(options);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentShowVO> show(@PathVariable Integer id) {
        DepartmentShowVO departmentShowVO = departmentService.show(id);
        return ResponseEntity.ok(departmentShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        int count = departmentService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody Integer[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = departmentService.delete(id);
        return ResponseEntity.ok(count);
    }

}


