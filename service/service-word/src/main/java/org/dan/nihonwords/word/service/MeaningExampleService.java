package org.dan.nihonwords.word.service;

import org.dan.nihonwords.model.word.MeaningExample;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 例句 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface MeaningExampleService extends IService<MeaningExample> {

    void removeByWordId(Long id);
}
