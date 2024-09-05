package org.dan.nihonwords.word.service;

import org.dan.nihonwords.model.word.WordMeaning;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 详细释义 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface WordMeaningService extends IService<WordMeaning> {

    void saveWordMeanings(Long wordId, List<WordMeaning> meanings);

    void removeByWordId(Long id);

    List<WordMeaning> findByWordId(Long id);

    Map<Long, List<String>> findByWordIds(List<Long> wordIdList);
}
