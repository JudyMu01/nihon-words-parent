package org.dan.nihonwords.study.service;

/**
 * @author mcd
 * @create 2024-08-12 15:59
 */
public interface UserProgressService {
    void save(Long word_id);

    void update(Long word_id);
}
