package com.example.lin.dao.edu;

import com.example.lin.pojo.edu.EduPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EduDao {
    int deleteByPrimaryKey(Integer id);

    int insert(EduPo record);

    EduPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(EduPo record);

    List<EduPo> selectAll(EduPo record);
}