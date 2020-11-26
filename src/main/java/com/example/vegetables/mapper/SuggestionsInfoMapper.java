package com.example.vegetables.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.vegetables.model.SuggestionsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SuggestionsInfoMapper extends BaseMapper<SuggestionsInfo> {
List<String> listName();
 SuggestionsInfo  getByName(@Param("name") String name,@Param("time") String time);
     }