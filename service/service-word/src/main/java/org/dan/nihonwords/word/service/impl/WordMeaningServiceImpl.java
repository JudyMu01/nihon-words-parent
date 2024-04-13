package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dan.nihonwords.model.word.WordMeaning;
import org.dan.nihonwords.word.mapper.WordMeaningMapper;
import org.dan.nihonwords.word.service.WordMeaningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 详细释义 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class WordMeaningServiceImpl extends ServiceImpl<WordMeaningMapper, WordMeaning> implements WordMeaningService {

    @Override
    public void saveWordMeanings(Long wordId, List<WordMeaning> meanings) {
        for(WordMeaning wordMeaning:meanings){
            wordMeaning.setWordId(wordId);
            baseMapper.insert(wordMeaning);
        }

    }

    @Override
    public void removeByWordId(Long id) {
        LambdaQueryWrapper<WordMeaning> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WordMeaning::getWordId, id);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<WordMeaning> findByWordId(Long id) {
        LambdaQueryWrapper<WordMeaning> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WordMeaning::getWordId, id);
        List<WordMeaning> wordMeaningList = baseMapper.selectList(wrapper);
        return wordMeaningList;
    }
}
