package org.dan.nihonwords.model.word;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * 词书类型
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Booktype extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型介绍
     */
    private String info;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

}
