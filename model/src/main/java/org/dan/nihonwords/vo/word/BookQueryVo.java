package org.dan.nihonwords.vo.word;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mcd
 * @create 2023-08-02 14:44
 */

@Data
public class BookQueryVo {

    @ApiModelProperty(value = "词书名称")
    private String name;
}
