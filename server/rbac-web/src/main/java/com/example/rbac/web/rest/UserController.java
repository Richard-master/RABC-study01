package com.example.rbac.web.rest;

import com.example.common.constant.ErrorCode;
import com.example.common.exception.BusinessException;
import com.example.common.pojo.vo.PageVO;
import com.example.rbac.pojo.dto.UserAddDTO;
import com.example.rbac.pojo.dto.UserUpdateDTO;
import com.example.rbac.pojo.mapper.UserMapper;
import com.example.rbac.pojo.po.UserPO;
import com.example.rbac.pojo.qo.UserQO;
import com.example.rbac.pojo.vo.UserListVO;
import com.example.rbac.pojo.vo.UserShowVO;
import com.example.rbac.service.UserService;
import com.example.rbac.web.AbstractController;
import com.example.rbac.web.api.UserAPI;
import com.example.rbac.web.constant.WebConst;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * 【用户】控制器
 *
 * @author Jack
 * @date 2022/05/10
 */
@RestController
@RequestMapping(WebConst.API_PATH + "/user")
public class UserController extends AbstractController implements UserAPI {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserShowVO> save(@Valid @RequestBody UserAddDTO userAddDTO) throws Exception {
        UserPO user = userService.save(userAddDTO);
        return ResponseEntity.created(new URI(WebConst.API_PATH + "/user/" + user.getId()))
                .body(UserMapper.INSTANCE.toShowVO(user));
    }

    @Override
    @PutMapping
    public ResponseEntity<UserShowVO> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserPO user = userService.update(userUpdateDTO);
        return ResponseEntity.ok(UserMapper.INSTANCE.toShowVO(user));
    }

    @Override
    @GetMapping
    public ResponseEntity<PageVO<UserListVO>> list(@Valid UserQO userQO) {
        PageVO<UserListVO> page = userService.list(userQO);
        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserShowVO> show(@PathVariable Long id) {
        UserShowVO userShowVO = userService.show(id);
        return ResponseEntity.ok(userShowVO);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Long id) {
        int count = userService.delete(id);
        return ResponseEntity.ok(count);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> deleteBatch(@RequestBody Long[] id) {
        if (ArrayUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAM_IS_NULL);
        }
        int count = userService.delete(id);
        return ResponseEntity.ok(count);
    }

}


