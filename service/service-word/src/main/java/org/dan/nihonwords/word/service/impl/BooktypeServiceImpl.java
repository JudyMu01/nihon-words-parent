package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dan.nihonwords.model.word.Booktype;
import org.dan.nihonwords.vo.word.BooktypeQueryVo;
import org.dan.nihonwords.word.mapper.BooktypeMapper;
import org.dan.nihonwords.word.service.BooktypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 词书类型 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class BooktypeServiceImpl extends ServiceImpl<BooktypeMapper, Booktype> implements BooktypeService {

    @Override
    public IPage<Booktype> getpage(Page<Booktype> pageParam, BooktypeQueryVo booktypeQueryVo) {

        LambdaQueryWrapper<Booktype> wrapper = new LambdaQueryWrapper<>();

        String name = booktypeQueryVo.getName();
        if(!StringUtils.isEmpty(name)){
            wrapper.like(Booktype::getName, booktypeQueryVo.getName());
        }

        IPage<Booktype> booktypeIPage = baseMapper.selectPage(pageParam, wrapper);
        return booktypeIPage;
    }
}
