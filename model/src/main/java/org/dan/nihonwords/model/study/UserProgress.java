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
 * @create 2024-08-12 10:17
 */
@Data
@ApiModel(description = "UserProgress")
public class UserProgress extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学习记录id")
    @TableField("up_id")
    private Long upId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "单词id")
    @TableField("word_id")
    private Long wordId;

    @ApiModelProperty(value = "已学习：0->未学习 1->已学习")
    @TableField("learned")
    private Boolean learned;

    @ApiModelProperty(value = "初次学习时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("first_learned_at")
    private Date firstLearnedAt;

    @ApiModelProperty(value = "最近学习时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("last_review_date")
    private Date lastReviewDate;

    @ApiModelProperty(value = "学习间隔")
    @TableField("review_interval")
    private Integer reviewInterval;

}

