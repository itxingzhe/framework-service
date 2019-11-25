package com.jiebai.framework.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @author lizhihui
 * @version 1.0.0
 */
public interface Service<T> {
    /**
     * 持久化
     *
     * @param model model实体
     */
    void save(T model);

    /**
     * 批量持久化
     *
     * @param models model实体列表
     */
    void save(List<T> models);

    /**
     * 根据主鍵刪除
     *
     * @param id 主键
     */
    void removeById(Integer id);

    /**
     * 更新
     *
     * @param model model实体
     */
    void updateById(T model);

    /**
     * 根据主键查找
     *
     * @param id 主键
     * @return T
     */
    T getById(Integer id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     *
     * @param fieldName 成员变量名称
     * @param value     值
     * @return T
     * @throws TooManyResultsException 多个结果异常
     */
    T getBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 通过多个ID查找
     *
     * @param ids 如："1,2,3,4"
     * @return List
     */
    List<T> listByIds(String ids);

    /**
     * 根据条件查找
     *
     * @param condition 自定义条件
     * @return List
     */
    List<T> listByCondition(Condition condition);

    /**
     * 查询所有结果
     *
     * @return list
     */
    List<T> listAll();
}
