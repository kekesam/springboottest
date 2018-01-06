package com.mk.dao;

import com.mk.bean.MKShopping;

public interface MKShoppingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MKShopping record);

    int insertSelective(MKShopping record);

    MKShopping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MKShopping record);

    int updateByPrimaryKey(MKShopping record);
}