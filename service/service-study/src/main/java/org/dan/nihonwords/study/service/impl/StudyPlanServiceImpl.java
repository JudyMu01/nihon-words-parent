package org.dan.nihonwords.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dan.nihonwords.client.word.WordFeignClient;
import org.dan.nihonwords.model.study.UserStudyPlan;
import org.dan.nihonwords.study.mapper.StudyPlanMapper;
import org.dan.nihonwords.study.service.StudyPlanService;
import org.dan.nihonwords.vo.study.UserStudyPlanVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author mcd
 * @create 2024-08-12 10:40
 */
@Service
public class StudyPlanServiceImpl extends ServiceImpl<StudyPlanMapper, UserStudyPlan> implements StudyPlanService {

    @Autowired
    private StudyPlanMapper studyPlanMapper;

    @Autowired
    private WordFeignClient wordFeignClient;
    @Override
    public void addStudyPlan(UserStudyPlanVo userStudyPlanVo) {
        //查找用户的计划，对当前词书是否有计划
        LambdaQueryWrapper<UserStudyPlan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserStudyPlan::getUserId, userStudyPlanVo.getUserId()).eq(UserStudyPlan::getWordbookId, userStudyPlanVo.getWordbookId());
        UserStudyPlan plan = studyPlanMapper.selectOne(queryWrapper);
        if(plan == null){
            plan = new UserStudyPlan();
            BeanUtils.copyProperties(userStudyPlanVo,plan);
            this.save(plan);

        }else{
            plan.setDailyWordCount(userStudyPlanVo.getDailyWordCount());
            plan.setDefault(true);
            this.updateById(plan);
        }


        //将用户对其他词书的计划设为非默认
        LambdaQueryWrapper<UserStudyPlan> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(UserStudyPlan::getUserId, userStudyPlanVo.getUserId()).ne(UserStudyPlan::getStartDate, userStudyPlanVo.getWordbookId());
        List<UserStudyPlan> notDefaultPlan = studyPlanMapper.selectList(queryWrapper1);
        List<UserStudyPlan> updatedPlans = notDefaultPlan.stream()
                .map(updatedPlan -> {
                    updatedPlan.setDefault(false);
                    return updatedPlan;
                })
                .collect(Collectors.toList());
        this.updateBatchById(updatedPlans);
    }

    @Override
    public List<Long> getWordList(Long wordbookId) {
        List<Long> wordIdList = wordFeignClient.getWordList(wordbookId);
        return wordIdList;
    }

    @Override
    public UserStudyPlan getPlanByUserId(Long userId) {
        LambdaQueryWrapper<UserStudyPlan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserStudyPlan::getUserId, userId).eq(UserStudyPlan::isDefault,true);//一个用户只能有一个默认在学习的计划
        UserStudyPlan userStudyPlan = this.getOne(wrapper);
        return userStudyPlan;
    }


}
