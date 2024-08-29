package org.dan.nihonwords.vo.study;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author mcd
 * @create 2024-08-12 13:34
 */
@Data
public class UserStudyPlanVo{

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "词书分类")
    private Long wordbookId;

    @ApiModelProperty(value = "学习个数")
    private Integer dailyWordCount;

}
