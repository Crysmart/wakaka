package com.szb.platform.workflow.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 量房任务转换对象
 *
 * @author Wang.hm
 * @date Created in 18:09 2020/2/7
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class MeasureTaskConverter {
    MeasureTaskConverter INSTANCE = Mappers.getMapper(MeasureTaskConverter.class);
}
