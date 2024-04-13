package org.dan.nihonwords.word.service.impl;

import org.dan.nihonwords.model.word.MqRepeatRecord;
import org.dan.nihonwords.word.mapper.MqRepeatRecordMapper;
import org.dan.nihonwords.word.service.MqRepeatRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * mq去重表 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class MqRepeatRecordServiceImpl extends ServiceImpl<MqRepeatRecordMapper, MqRepeatRecord> implements MqRepeatRecordService {

}
