package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dan.nihonwords.model.word.WordMeaning;
import org.dan.nihonwords.word.mapper.WordMeaningMapper;
import org.dan.nihonwords.word.service.WordMeaningService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Map<Long, List<String>> findByWordIds(List<Long> wordIdList) {
        Map<Long, List<String>> result = new HashMap<>();
        //wordIdList遍历，得到每个wordId
        wordIdList.forEach(wordId -> {
            //根据wordId进行查询
            List<WordMeaning> wordMeaningList =
                    findByWordId(wordId);
            //数据封装
            if(!CollectionUtils.isEmpty(wordMeaningList)) {
                List<String> meaningList = wordMeaningList.stream().map(WordMeaning::getTranslate).collect(Collectors.toList());
                result.put(wordId,meaningList);
            }
        });
        return result;
    }


}
