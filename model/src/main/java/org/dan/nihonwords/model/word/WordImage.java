package org.dan.nihonwords.model.word;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * 单词图片
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WordImage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单词id
     */
    private Long wordId;

    /**
     * 图片名称
     */
    private String imgName;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 排序
     */
    private Integer sort;


}
