package org.dan.nihonwords.word.mapper;

import org.dan.nihonwords.model.word.WordInBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 词和词书的关系 Mapper 接口
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Repository
public interface WordInBookMapper extends BaseMapper<WordInBook> {

}
