package com.huikedu.services;



import com.github.pagehelper.PageInfo;
import com.huikedu.mapper.Imaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class IBaseSeviceImpl<T> implements  IBaseService<T> {

// 指定bean名称
    @Qualifier("imaper")
    @Autowired
    private Imaper<T> imaper;

    @Override
    public int insert(T pojo) {
        return imaper.insert(pojo);
    }

    @Override
    public int update(T pojo) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public List selectList() {
        return imaper.select(null);
    }

    @Override
    public List selectList(T pojo) {
        return imaper.select(pojo);
    }

    @Override
    public List selectList(T pojo, String orderBy) {
//        PageHelper.orderBy(orderBy);//排序
        return imaper.select(pojo);
    }

    @Override
    public T selectOne(T pojo) {
        List<T> list=imaper.select(pojo);
        if(list!=null && list.size()>1){
            throw new RuntimeException("selectOne方法查询出了多条数据");
        }
        if(list==null || list.size()==0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public T createInstanceAndSetIdOfFirstGeneric(Long id) {
        return null;
    }

    @Override
    public T selectOne(Long id) {
        return null;
    }

    @Override
    public PageInfo page(int pageNum, int pageSize, Object pojo) {
        return null;
    }

    @Override
    public PageInfo page(int pageNum, int pageSize, Object pojo, String orderBy) {
        return null;
    }

    @Override
    public boolean isExisted(Object pojo) {
        return false;
    }
}
