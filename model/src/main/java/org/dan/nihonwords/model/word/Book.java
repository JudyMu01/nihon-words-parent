package org.dan.nihonwords.model.word;


import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * 词书
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Book extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 词书名称
     */
    private String name;

    /**
     * 词书分类
     */
    private Long bookType;

    /**
     * 图片
     */
    private String imgUrl;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;


}
