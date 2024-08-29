package org.dan.nihonwords.study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.auth.AuthContextHolder;
import org.dan.nihonwords.common.constant.RedisConst;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.study.service.StudyPlanService;
import org.dan.nihonwords.vo.study.UserStudyPlanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mcd
 * @create 2024-08-12 10:37
 */
@Api(tags = "学习计划接口")
@RestController
@RequestMapping("api/study/plan")
public class StudyPlanController {


    @Autowired
    private StudyPlanService studyPlanService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("添加用户学习计划")
    @PostMapping("/save")
    public Result saveStudyPlan(@RequestBody UserStudyPlanVo userStudyPlanVo){

        //增加一条学习计划记录
        studyPlanService.addStudyPlan(userStudyPlanVo);

        //在redis中创建该用户的pendingwords数据和learnedwords数据
        String pendingKey = RedisConst.USER_KEY_PREFIX
                + AuthContextHolder.getUserId()
                + RedisConst.STUDY_PENDING_KEY_SUFFIX;//user:{user_id}:pendingwords

        String learnedKey = RedisConst.USER_KEY_PREFIX
                + AuthContextHolder.getUserId()
                + RedisConst.STUDY_LEARNED_KEY_SUFFIX;//user:{user_id}:learnedwords
        //远程调用获取词书中全部单词id的list
        List<Long> wordIdList = studyPlanService.getWordList(userStudyPlanVo.getWordbookId());
        List<Long> learnedList = null;
        redisTemplate.opsForList().leftPushAll(pendingKey,wordIdList);
        redisTemplate.opsForList().leftPushAll(learnedKey,learnedList);
        return Result.ok(null);
    }



}
