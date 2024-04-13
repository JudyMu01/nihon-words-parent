package org.dan.nihonwords.word.mapper;

import org.dan.nihonwords.model.word.MqRepeatRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * mq去重表 Mapper 接口
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Repository
public interface MqRepeatRecordMapper extends BaseMapper<MqRepeatRecord> {

}
