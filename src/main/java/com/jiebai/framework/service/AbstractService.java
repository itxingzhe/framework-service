package com.jiebai.framework.service;


import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 *
 * @author lizhihui
 * @version 1.0.0
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected Mapper<T> mapper;

    /**
     * 当前泛型真实类型的Class
     */
    private Class<T> modelClass;

    /**
     * 构造方法
     */
    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T model) {
        mapper.insertSelective(model);
    }

    @Override
    public void save(List<T> models) {
        mapper.insertList(models);
    }

    @Override
    public void removeById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T getBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> listByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> listByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> listAll() {
        return mapper.selectAll();
    }
}
