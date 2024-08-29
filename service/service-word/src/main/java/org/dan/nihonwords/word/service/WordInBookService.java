package org.dan.nihonwords.word.service;

import org.dan.nihonwords.model.word.WordInBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 词和词书的关系 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface WordInBookService extends IService<WordInBook> {

    void removeByWordId(Long id);

    List<Long> getWordIdListByBookId(Long wordbookId);
}
