package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dan.nihonwords.model.word.MeaningExample;
import org.dan.nihonwords.word.mapper.MeaningExampleMapper;
import org.dan.nihonwords.word.service.MeaningExampleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 例句 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class MeaningExampleServiceImpl extends ServiceImpl<MeaningExampleMapper, MeaningExample> implements MeaningExampleService {

    @Override
    public void removeByWordId(Long id) {
        LambdaQueryWrapper<MeaningExample> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MeaningExample::getWordId,id);
        baseMapper.delete(wrapper);
    }
}
