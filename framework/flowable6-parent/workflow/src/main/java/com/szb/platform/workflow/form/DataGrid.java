package com.szb.platform.workflow.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 返回前台数据表格
 *
 * @author Wang.hm
 * @date Created in 15:46 2020/2/7
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("分页组件")
public class DataGrid<T> implements Serializable {
    @ApiModelProperty("当前页数")
    private Integer currentPage;
    @ApiModelProperty("每页多少条")
    private Integer pageSize;
    @ApiModelProperty("总页数")
    private Integer totalPage;
    @ApiModelProperty("任务集合")
    private List<T> rows;
}
