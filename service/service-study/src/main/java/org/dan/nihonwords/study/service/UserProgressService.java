package org.dan.nihonwords.study.service;

import org.dan.nihonwords.model.study.UserProgress;

import java.util.List;

/**
 * @author mcd
 * @create 2024-08-12 15:59
 */
public interface UserProgressService {
    void save(Long word_id);

    void update(Long word_id);

    List<UserProgress> getLearnedWords(Long userId, List<Long> wordIdList);

    List<Long> findUnrecordedWordIds(Long userId, List<Long> wordIds);
}
