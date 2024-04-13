package org.dan.nihonwords.common.result;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

/**
 * @author mcd
 * @create 2023-07-07 18:43
 */
//这个lombok的注解可以不用写getter和setter了
@Data
public class Result<T> {
    //状态码
    private Integer code;

    //信息
    private String message;

    //数据
    private T data;

    //构造私有化
    private Result() { }

    //设置数据,并返回对象的方法
    public static<T> Result<T> build(T data, ResultCodeEnum resultCodeEnum){

        Result<T> result = new Result<>();
        //判断返回结果数据是否为空
        if(data != null){
            result.setData(data);
        }
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());

        return result;
    }

    //成功的方法
    public static<T> Result<T> ok(T data) {
        Result<T> result = build(data, ResultCodeEnum.SUCCESS);
        return result;
    }

    //失败的方法
    public static<T> Result<T> fail(T data) {
        return build(data,ResultCodeEnum.FAIL);
    }
}
