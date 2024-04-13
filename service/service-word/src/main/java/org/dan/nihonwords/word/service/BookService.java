package org.dan.nihonwords.word.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dan.nihonwords.model.word.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dan.nihonwords.vo.word.BookQueryVo;

/**
 * <p>
 * 词书 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface BookService extends IService<Book> {

    IPage<Book> selectPage(Page<Book> pageParam, BookQueryVo bookQueryVo);
}
