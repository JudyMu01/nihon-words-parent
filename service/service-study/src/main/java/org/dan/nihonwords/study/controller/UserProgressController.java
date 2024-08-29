package org.dan.nihonwords.study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dan.nihonwords.common.auth.AuthContextHolder;
import org.dan.nihonwords.common.constant.RedisConst;
import org.dan.nihonwords.common.result.Result;
import org.dan.nihonwords.model.study.UserStudyPlan;
import org.dan.nihonwords.study.service.StudyPlanService;
import org.dan.nihonwords.study.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mcd
 * @create 2024-08-12 16:06
 */
@Api(tags = "学习记录接口")
@RestController
@RequestMapping("api/study")
public class UserProgressController {

//    显示单词详情（根据单词id，查询单词具体信息，包括单词翻译列表，图片列表，属于的词书，使用CompletableFuture异步查找）api/study/getword/{word_id}
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StudyPlanService studyPlanService;

    @Autowired
    private UserProgressService userProgressService;

    @ApiOperation("获取待学习词汇列表")
    @GetMapping("/pendinglist")
    public Result getPendingList(){
        //查询用户学习计划表，得到每天学习单词数量count个
        UserStudyPlan userStudyPlan = studyPlanService.getPlanByUserId(AuthContextHolder.getUserId());
        Integer count = userStudyPlan.getDailyWordCount();

        //从redis数据库中查到这个人待学习的列表中前count个
        String key = RedisConst.USER_KEY_PREFIX + AuthContextHolder.getUserId() + RedisConst.STUDY_PENDING_KEY_SUFFIX;
        List<Long> list = redisTemplate.opsForList().range(key, 0, count);

        //远程调用获取每个word的具体信息
        // 待实现
        return Result.ok(list);
    }

    //    获取待复习词汇列表 api/study/learnedlist
    //   从learnedwords中选择n个，去word表中查询
    @ApiOperation("获取待复习词汇列表")
    @GetMapping("/learnedlist")
    public Result getLearnedList(){

        //查询用户学习计划表，得到每天学习单词数量count个
        UserStudyPlan userStudyPlan = studyPlanService.getPlanByUserId(AuthContextHolder.getUserId());
        Integer count = userStudyPlan.getDailyWordCount();

        //从redis数据库中查到这个人待学习的列表中前count个
        String key = RedisConst.USER_KEY_PREFIX + AuthContextHolder.getUserId() + RedisConst.STUDY_LEARNED_KEY_SUFFIX;
        List<Long> list = redisTemplate.opsForList().range(key, 0, count);

        //远程调用获取每个word的具体信息
        // 待实现
        return Result.ok(list);

    }

    @ApiOperation("学习单词")
    @PutMapping("/learn/{word_id}")
    public Result learnAWord(@PathVariable Long word_id){
        //学习单词从Pendingwords中删除，添加到learnedwords
        String pendingkey = RedisConst.USER_KEY_PREFIX + AuthContextHolder.getUserId() + RedisConst.STUDY_PENDING_KEY_SUFFIX;


        String learnedKey = RedisConst.USER_KEY_PREFIX + AuthContextHolder.getUserId() + RedisConst.STUDY_LEARNED_KEY_SUFFIX;
        Long res = redisTemplate.opsForList().rightPush(learnedKey, word_id);

        //并增加userprogress表中的记录
        userProgressService.save(word_id);
        return Result.ok(null);
    }

    //    复习单词（从learnedwords中删除）api/study/review/{word_id}
    //    更新单词学习进度（修改UserProgress表中的时间）(学习单词和复习单词都会触发)
    @ApiOperation("复习单词")
    @PutMapping("/review/{word_id}")
    public Result reviewAWord(@PathVariable Long word_id){
        String learnedKey = RedisConst.USER_KEY_PREFIX + AuthContextHolder.getUserId() + RedisConst.STUDY_LEARNED_KEY_SUFFIX;
        Long wordId = (Long)redisTemplate.opsForList().leftPop(learnedKey);
        redisTemplate.opsForList().rightPush(learnedKey, wordId);
        if(wordId == word_id){
            userProgressService.update(word_id);
        }
        return Result.ok(null);


    }
}
