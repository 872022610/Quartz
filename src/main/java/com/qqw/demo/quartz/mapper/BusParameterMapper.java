package com.qqw.demo.quartz.mapper;

import com.qqw.demo.quartz.entity.Parameter;
import java.util.List;

public interface BusParameterMapper {
    List<Parameter> selectJobParameterList();
}