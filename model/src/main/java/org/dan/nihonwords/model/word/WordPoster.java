package org.dan.nihonwords.model.word;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * 单词海报表
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WordPoster extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单词id
     */
    private Long wordId;

    /**
     * 文件名称
     */
    private String imgName;

    /**
     * 文件路径
     */
    private String imgUrl;



}
