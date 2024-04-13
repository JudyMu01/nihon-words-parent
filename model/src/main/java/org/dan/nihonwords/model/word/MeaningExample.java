package org.dan.nihonwords.model.word;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * 例句
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MeaningExample extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 单词id
     */
    private Long wordId;

    /**
     * 释义id
     */
    private Long meaningId;

    /**
     * 句子
     */
    private String sentence;

    /**
     * 句子翻译
     */
    private String translate;

}
