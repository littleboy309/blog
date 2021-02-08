package com.heli;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Description: 自定义 异常
 * date: 2021/1/30 11:42
 *
 * @author heli
 * @since JDK 1.8
 */
/**
* Description: NOT_FOUND springboot会把自定义异常类 定义为找不到的异常处理 返回404
* @author: Administrator
* @date: 2021/1/30 11:49
* @param:
* @return:
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
