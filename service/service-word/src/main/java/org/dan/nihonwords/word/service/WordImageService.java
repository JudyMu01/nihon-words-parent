package org.dan.nihonwords.word.service;

import org.dan.nihonwords.model.word.WordImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 单词图片 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface WordImageService extends IService<WordImage> {

    List<WordImage> findByWordId(Long id);
}
