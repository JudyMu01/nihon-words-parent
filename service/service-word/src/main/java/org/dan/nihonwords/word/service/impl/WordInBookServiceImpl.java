package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dan.nihonwords.model.word.WordInBook;
import org.dan.nihonwords.word.mapper.WordInBookMapper;
import org.dan.nihonwords.word.service.WordInBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 词和词书的关系 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class WordInBookServiceImpl extends ServiceImpl<WordInBookMapper, WordInBook> implements WordInBookService {

    @Override
    public void removeByWordId(Long wordId) {
        LambdaQueryWrapper<WordInBook> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WordInBook::getWordId, wordId);
        baseMapper.delete(wrapper);

    }
}
