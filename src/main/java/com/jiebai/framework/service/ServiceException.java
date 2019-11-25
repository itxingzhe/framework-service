package com.jiebai.framework.service;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    /**
     * 构造方法
     *
     * @param message 异常描述
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param message 异常描述
     * @param cause   异常栈
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
