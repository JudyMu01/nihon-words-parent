package org.dan.nihonwords.word.mapper;

import org.dan.nihonwords.model.word.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 词书 Mapper 接口
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Repository
public interface BookMapper extends BaseMapper<Book> {

}
