package org.dan.nihonwords.model.word;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * 词和词书的关系
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WordInBook extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 单词id
     */
    private Long wordId;

    /**
     * 词书id
     */
    private Long bookId;



}
