package com.wf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}