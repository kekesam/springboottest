package com.mk.dao;

import com.mk.bean.MKShopping;
import com.mk.bean.MKShoppingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MKShoppingMapper {
    int countByExample(MKShoppingExample example);

    int deleteByExample(MKShoppingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MKShopping record);

    int insertSelective(MKShopping record);

    List<MKShopping> selectByExample(MKShoppingExample example);

    MKShopping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MKShopping record, @Param("example") MKShoppingExample example);

    int updateByExample(@Param("record") MKShopping record, @Param("example") MKShoppingExample example);

    int updateByPrimaryKeySelective(MKShopping record);

    int updateByPrimaryKey(MKShopping record);
}