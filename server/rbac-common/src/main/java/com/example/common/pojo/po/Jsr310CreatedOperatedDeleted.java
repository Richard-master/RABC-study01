package com.example.common.pojo.po;


/**
 * 逻辑删除+创建人&创建时间+操作人&操作时间-jsr310时间API
 *
 * @author Jack
 * @date 2022/05/10
 */
public interface Jsr310CreatedOperatedDeleted extends Jsr310Created, Jsr310Operated, Deleted {
}

