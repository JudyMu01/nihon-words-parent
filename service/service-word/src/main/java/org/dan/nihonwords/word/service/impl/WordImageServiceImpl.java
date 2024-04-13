package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dan.nihonwords.model.word.WordImage;
import org.dan.nihonwords.word.mapper.WordImageMapper;
import org.dan.nihonwords.word.service.WordImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 单词图片 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class WordImageServiceImpl extends ServiceImpl<WordImageMapper, WordImage> implements WordImageService {

    @Override
    public List<WordImage> findByWordId(Long id) {
        LambdaQueryWrapper<WordImage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WordImage::getWordId, id);
        List<WordImage> wordImageList = baseMapper.selectList(wrapper);
        return wordImageList;
    }
}
