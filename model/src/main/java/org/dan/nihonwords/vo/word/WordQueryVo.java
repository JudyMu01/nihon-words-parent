package org.dan.nihonwords.vo.word;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mcd
 * @create 2023-08-05 14:05
 */
@Data
public class WordQueryVo {

    @ApiModelProperty(value = "单词")
    private String word;

    @ApiModelProperty(value = "简单翻译")
    private String translate;
}
