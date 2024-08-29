package org.dan.nihonwords.study.service;

import org.dan.nihonwords.model.study.UserStudyPlan;
import org.dan.nihonwords.vo.study.UserStudyPlanVo;

import java.util.List;


/**
 * @author mcd
 * @create 2024-08-12 10:40
 */
public interface StudyPlanService {
    void addStudyPlan(UserStudyPlanVo userStudyPlanVo);

    List<Long> getWordList(Long wordbookId);

    UserStudyPlan getPlanByUserId(Long userId);
}
