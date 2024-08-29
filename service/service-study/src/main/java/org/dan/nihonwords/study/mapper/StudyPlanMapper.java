package org.dan.nihonwords.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.dan.nihonwords.model.study.UserStudyPlan;
import org.springframework.stereotype.Repository;

/**
 * @author mcd
 * @create 2024-08-12 10:43
 */
@Repository
public interface StudyPlanMapper extends BaseMapper<UserStudyPlan> {
}
