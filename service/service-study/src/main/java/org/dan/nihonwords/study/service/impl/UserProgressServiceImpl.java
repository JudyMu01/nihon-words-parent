package org.dan.nihonwords.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.common.auth.AuthContextHolder;
import org.dan.nihonwords.model.study.UserProgress;
import org.dan.nihonwords.study.mapper.UserProgressMapper;
import org.dan.nihonwords.study.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mcd
 * @create 2024-08-12 16:00
 */
@Service
public class UserProgressServiceImpl extends ServiceImpl<UserProgressMapper, UserProgress> implements UserProgressService {
    @Autowired
    private UserProgressMapper userProgressMapper;
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

    public List<UserProgress> getLearnedWords(Long userId, List<Long> wordIdList) {

        // 构建查询条件
        QueryWrapper<UserProgress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)  // 查询条件 user_id = 指定值
                .in("word_id", wordIdList)  // 查询条件 word_id 在指定的列表中
                .eq("learned", 1);  // 查询条件 learned = 1

        // 执行查询并返回结果
        return userProgressMapper.selectList(queryWrapper);
    }

    public List<Long> findUnrecordedWordIds(Long userId, List<Long> wordIds) {
        // 查询user_progress表中已有的word_id
        QueryWrapper<UserProgress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .in("word_id", wordIds);

        List<UserProgress> existingRecords = userProgressMapper.selectList(queryWrapper);
        Set<Long> existingWordIds = existingRecords.stream()
                .map(UserProgress::getWordId)
                .collect(Collectors.toSet());

        // 找出不在existingWordIds中的word_id
        return wordIds.stream()
                .filter(wordId -> !existingWordIds.contains(wordId))
                .collect(Collectors.toList());
    }
}
