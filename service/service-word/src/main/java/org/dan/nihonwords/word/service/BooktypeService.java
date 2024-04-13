package org.dan.nihonwords.word.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.dan.nihonwords.model.word.Booktype;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dan.nihonwords.vo.word.BooktypeQueryVo;

/**
 * <p>
 * 词书类型 服务类
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
public interface BooktypeService extends IService<Booktype> {

    IPage<Booktype> getpage(Page<Booktype> pageParam, BooktypeQueryVo booktypeQueryVo);
}
