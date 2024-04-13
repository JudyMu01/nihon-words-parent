package org.dan.nihonwords.word.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dan.nihonwords.model.acl.Admin;
import org.dan.nihonwords.model.word.Book;
import org.dan.nihonwords.vo.word.BookQueryVo;
import org.dan.nihonwords.word.mapper.BookMapper;
import org.dan.nihonwords.word.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 词书 服务实现类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public IPage<Book> selectPage(Page<Book> pageParam, BookQueryVo bookQueryVo) {
        String bookName = bookQueryVo.getName();
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(bookName)){
            wrapper.like(Book::getName, bookName);
        }

        IPage<Book> bookModel = baseMapper.selectPage(pageParam, wrapper);

        return bookModel;
    }
}
