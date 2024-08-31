package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dan.nihonwords.common.constant.MqConst;
import org.dan.nihonwords.model.word.*;
import org.dan.nihonwords.mq.service.RabbitService;
import org.dan.nihonwords.vo.word.WordQueryVo;
import org.dan.nihonwords.vo.word.WordVo;
import org.dan.nihonwords.word.mapper.WordMapper;
import org.dan.nihonwords.word.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 单词信息 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService {

    @Autowired
    private WordImageService wordImageService;

    @Autowired
    private WordPosterService wordPosterService;

    @Autowired
    private WordMeaningService wordMeaningService;

    @Autowired
    private WordInBookService wordInBookService;

    @Autowired
    private RabbitService rabbitService;
    /**
     * 分页查询单词，条件查询为单词或翻译
     * @param pageParam
     * @param wordQueryVo
     * @return 分页类
     */
    @Override
    public IPage<Word> selectPage(Page<Word> pageParam, WordQueryVo wordQueryVo) {

        String word = wordQueryVo.getWord();
        String translate = wordQueryVo.getTranslate();

        LambdaQueryWrapper<Word> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(word)){
            wrapper.like(Word::getWord, word);
        }
        if(!StringUtils.isEmpty(translate)){
            wrapper.like(Word::getTranslate, translate);
        }

        IPage<Word> wordIPage = baseMapper.selectPage(pageParam, wrapper);


        return wordIPage;
    }

    @Override
    public void saveWordInfo(WordVo wordVo) {

        //存储单词基本信息后得到生成id
        Word word = new Word();
        BeanUtils.copyProperties(wordVo, word);
        this.save(word);

        //将单词和词书的关系存入表
        WordInBook wordInBook = new WordInBook();
        wordInBook.setWordId(wordVo.getId());
        wordInBook.setBookId(wordVo.getBookId());
        wordInBookService.save(wordInBook);

        //将单词释义存入
        wordMeaningService.saveWordMeanings(wordVo.getId(), wordVo.getWordMeaningList());

        //最后存例句的意思
//        wordMeaningService.saveWordMeanings(meanings);
//        meaningExampleService.saveExamples(examples);
    }

    @Override
    public WordVo getWordVoById(Long id) {

        WordVo wordVo = new WordVo();

        Word word = baseMapper.selectById(id);
        List<WordImage> wordImageList = wordImageService.findByWordId(id);
        List<WordPoster> wordPosterList = wordPosterService.findByWordId(id);
        List<WordMeaning> wordMeaningList = wordMeaningService.findByWordId(id);

        BeanUtils.copyProperties(word, wordVo);
        wordVo.setWordImagesList(wordImageList);
        wordVo.setWordPosterList(wordPosterList);
        wordVo.setWordMeaningList(wordMeaningList);
        return wordVo;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void updateWord(WordVo wordVo) {
        //更新单词基本信息
        Long id = wordVo.getId();
        baseMapper.updateById(wordVo);

        //更新图片列表
        wordImageService.remove(new LambdaQueryWrapper<WordImage>().eq(WordImage::getWordId, id));
        List<WordImage> wordImageList = wordVo.getWordImagesList();
        if(!CollectionUtils.isEmpty(wordImageList)) {
            for(WordImage wordImage : wordImageList) {
                wordImage.setWordId(id);
            }
            wordImageService.saveBatch(wordImageList);
        }

        //更新海报列表
        wordPosterService.remove(new LambdaQueryWrapper<WordPoster>().eq(WordPoster::getWordId,id));
        List<WordPoster> wordPosterList = wordVo.getWordPosterList();
        if(!CollectionUtils.isEmpty(wordPosterList)){
            for(WordPoster wordPoster:wordPosterList){
                wordPoster.setWordId(id);
            }
            wordPosterService.saveBatch(wordPosterList);
        }

        //更新单词释义
        wordMeaningService.removeByWordId(id);
        List<WordMeaning> wordMeaningList = wordVo.getWordMeaningList();
        if(!CollectionUtils.isEmpty(wordMeaningList)){
            for(WordMeaning wordMeaning:wordMeaningList){
                wordMeaning.setWordId(id);
            }
            wordMeaningService.saveBatch(wordMeaningList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void publish(Long id, Boolean status) {
        if(status == true){
            // 更改发布状态
            Word wordUp = new Word();
            wordUp.setId(id);
            wordUp.setPublishStatus(true);
            baseMapper.updateById(wordUp);
            //发送mq消息更新es数据， 发布单词
            rabbitService.sendMessage(MqConst.EXCHANGE_GOODS_DIRECT,MqConst.ROUTING_GOODS_UPPER, id);
        }else{
            // 更改发布状态
            Word wordUp = new Word();
            wordUp.setId(id);
            wordUp.setPublishStatus(false);
            baseMapper.updateById(wordUp);

            //发送mq消息更新es数据， 删除单词
            rabbitService.sendMessage(MqConst.EXCHANGE_GOODS_DIRECT,MqConst.ROUTING_GOODS_LOWER, id);
        }



    }

    @Override
    public void check(Long id, Boolean status) {
        // 更改发布状态
        Word wordUp = new Word();
        wordUp.setId(id);
        wordUp.setCheckStatus(status);
        baseMapper.updateById(wordUp);
    }

}
