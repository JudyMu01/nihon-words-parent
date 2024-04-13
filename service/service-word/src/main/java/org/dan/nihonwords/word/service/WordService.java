package org.dan.nihonwords.word.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dan.nihonwords.model.word.Word;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dan.nihonwords.vo.word.WordQueryVo;
import org.dan.nihonwords.vo.word.WordVo;

/**
 * <p>
 * 单词信息 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface WordService extends IService<Word> {

    IPage<Word> selectPage(Page<Word> pageParam, WordQueryVo wordQueryVo);

    void saveWordInfo(WordVo wordVo);

    WordVo getWordVoById(Long id);

    void updateWord(WordVo wordVo);

    void publish(Long id, Boolean status);

    void check(Long id, Boolean status);
}
