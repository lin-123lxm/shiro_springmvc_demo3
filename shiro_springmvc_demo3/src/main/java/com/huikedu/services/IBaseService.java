package com.huikedu.services;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBaseService<T> {

    /*
    保存
     */
     int insert(T pojo);

    /**
     * 修改
     * @param pojo
     * @return
     */
     int update(T pojo);

    /**
     * 删除
     * @param id
     * @return
     */
     int delete(Long id);

    /**
     * 不需要参数的查询
     */
     List<T> selectList();

    /**
     * 条件查询
     */
    public List<T> selectList(T pojo);

    /**
     * 条件排序查询，根据name进行排序并查询
     */
    public List<T> selectList(T pojo, String orderBy);

    /**
     * 查询一条数据。如果查询除了多条数据，就抛出throw异常
     */
    public T selectOne(T pojo);

    public T createInstanceAndSetIdOfFirstGeneric(Long id);

    /**
     * 根据id进行查询，查询出多条数据会抛出Runtime异常
     */
    public T selectOne(Long id);

    /**
     * 条件分页查询
     */
    public PageInfo<T> page(int pageNum, int pageSize, T pojo);

    /**
     * 条件分页排序查询
     */
    public PageInfo<T> page(int pageNum, int pageSize, T pojo, String orderBy);

    /**
     * 判断是否已经存在
     */
    public boolean isExisted(T pojo);




}
