package org.dan.nihonwords.model.word;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * 单词信息
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Word extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单词
     */
    private String word;

    /**
     * 声调
     */
    private Integer accent;

    /**
     * 假名
     */
    private String kana;

    /**
     * 基本含义
     */
    private String translate;

    /**
     * 展示图片
     */
    private String imgUrl;

    /**
     * 上架状态：0->下架；1->上架
     */
    private Boolean publishStatus;

    /**
     * 审核状态：0->未审核；1->审核通过
     */
    private Boolean checkStatus;



}
