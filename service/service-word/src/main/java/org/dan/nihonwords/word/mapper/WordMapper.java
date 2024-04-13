package org.dan.nihonwords.word.mapper;

import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.model.word.Word;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 单词信息 Mapper 接口
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Repository
public interface WordMapper extends BaseMapper<Word> {

}
