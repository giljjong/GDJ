package com.gdu.app15.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app15.domain.BlogDTO;

@Mapper
public interface BlogMapper {
	public List<BlogDTO> selectBlogListByMap(Map<String, Object> map);
	public int selectBlogListCount();
}
