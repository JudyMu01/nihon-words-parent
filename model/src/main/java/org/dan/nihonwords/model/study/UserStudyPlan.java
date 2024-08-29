package org.dan.nihonwords.model.study;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dan.nihonwords.model.base.BaseEntity;

import java.util.Date;

/**
 * @author mcd
 * @create 2024-08-12 10:27
 */
@Data
@ApiModel(description = "学习计划")
public class UserStudyPlan extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学习计划id")
    @TableField("plan_id")
    private Long planId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "词书id")
    @TableField("wordbook_id")
    private Long wordbookId;

    @ApiModelProperty(value = "每日学习个数")
    @TableField("daily_word_count")
    private Integer dailyWordCount;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("start_date")
    private Date startDate;

    @ApiModelProperty(value = "已经学习个数")
    @TableField("learned_word_count")
    private Integer learnedWordCount;

    @ApiModelProperty("是否为默认计划")
    @TableField("is_default")
    private boolean isDefault;



}
