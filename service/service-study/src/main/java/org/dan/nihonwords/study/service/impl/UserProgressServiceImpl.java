package org.dan.nihonwords.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.common.auth.AuthContextHolder;
import org.dan.nihonwords.model.study.UserProgress;
import org.dan.nihonwords.study.mapper.UserProgressMapper;
import org.dan.nihonwords.study.service.UserProgressService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author mcd
 * @create 2024-08-12 16:00
 */
@Service
public class UserProgressServiceImpl extends ServiceImpl<UserProgressMapper, UserProgress> implements UserProgressService {
    @Override
    public void save(Long word_id) {
        UserProgress userProgress = new UserProgress();
        userProgress.setUserId(AuthContextHolder.getUserId());
        userProgress.setWordId(word_id);;
        userProgress.setLearned(true);
        Date now = new Date();
        userProgress.setFirstLearnedAt(now);
        userProgress.setLastReviewDate(now);
        userProgress.setReviewInterval(0);

        this.save(userProgress);
    }

    @Override
    public void update(Long word_id) {
        LambdaQueryWrapper<UserProgress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserProgress::getUserId,AuthContextHolder.getUserId()).eq(UserProgress::getWordId,word_id);
        UserProgress userProgress = this.getOne(wrapper);
        Date now = new Date();
        userProgress.setReviewInterval((int) ((now.getTime() - userProgress.getLastReviewDate().getTime())/24/60/60/1000));
        userProgress.setLastReviewDate(now);
        this.updateById(userProgress);
    }
}
