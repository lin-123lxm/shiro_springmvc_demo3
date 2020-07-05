package com.huikedu.mapper;

import java.util.List;

/*
  BaseMapper   将基础的添加、修改、删除、检索方法定义此接口中，其它的Mapper接口从该接口派生
 */
public interface Imaper<T> {

    /*
    添加
     */
    int insert(T o);
    /*
    修改
     */
    int update(T o);

    /*
    删除
     */
    int  delete(Long id);

    /*
    列表
    */
    List<T> select(T o);
}
