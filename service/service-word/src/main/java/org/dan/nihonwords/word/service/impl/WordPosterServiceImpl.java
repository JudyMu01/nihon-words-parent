package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dan.nihonwords.model.word.WordPoster;
import org.dan.nihonwords.word.mapper.WordPosterMapper;
import org.dan.nihonwords.word.service.WordPosterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 单词海报表 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class WordPosterServiceImpl extends ServiceImpl<WordPosterMapper, WordPoster> implements WordPosterService {

    @Override
    public List<WordPoster> findByWordId(Long id) {
        LambdaQueryWrapper<WordPoster> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WordPoster::getWordId, id);
        List<WordPoster> wordPosterList = baseMapper.selectList(wrapper);
        return wordPosterList;
    }
}
