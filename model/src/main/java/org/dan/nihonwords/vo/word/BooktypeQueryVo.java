package org.dan.nihonwords.vo.word;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mcd
 * @create 2023-08-07 13:44
 */
@Data
public class BooktypeQueryVo {

    @ApiModelProperty(value = "词书类型名称")
    private String name;
}
