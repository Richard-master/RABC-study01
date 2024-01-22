package com.example.common.pojo.vo;

import com.example.common.util.JsonUtil;

import java.io.Serializable;

/**
 * 抽象VO
 *
 * @author Jack
 * @date 2022/05/10
 */
public abstract class AbstractVO implements Serializable {

    private static final long serialVersionUID = -1417748095004687576L;

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }

}

