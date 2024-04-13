package org.dan.nihonwords.word.service;

import org.dan.nihonwords.model.word.WordPoster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 单词海报表 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface WordPosterService extends IService<WordPoster> {

    List<WordPoster> findByWordId(Long id);
}
