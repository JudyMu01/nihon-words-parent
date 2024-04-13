package org.dan.nihonwords.common.exception;

import org.dan.nihonwords.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mcd
 * @create 2023-07-12 11:21
 */

//AOP面向切面
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//异常处理器
    @ResponseBody//返回json数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }

    /**
     * 自定义异常处理
     * @param nWordsException
     * @return
     */
    @ExceptionHandler(NWordsException.class)
    @ResponseBody
    public Result error(NWordsException nWordsException){
        nWordsException.printStackTrace();
        return Result.fail(null);
    }
}
