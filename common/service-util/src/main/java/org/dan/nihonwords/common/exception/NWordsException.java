package org.dan.nihonwords.common.exception;

import lombok.Data;
import org.dan.nihonwords.common.result.ResultCodeEnum;

/**
 * @author mcd
 * @create 2023-07-12 11:45
 */
@Data
public class NWordsException extends RuntimeException{

    //包含属性：异常状态码code和异常信息message，message可以使用父类的属性
    private Integer code;

    /**
     * 通过信息和状态码构造异常类
     * @param message
     * @param code
     */
    public NWordsException(String message, Integer code){
        super(message);
        this.code = code;
    }

    /**
     * 通过枚举类对象构造
     * @param resultCodeEnum
     */
    public NWordsException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "NWordsException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
