package com.test.exception;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.exception
 * @ClassName: AccessForbiddenException
 * @Author: ZhangJunjie
 * @Description: 表示非正常访问
 * @Date: 2020/5/9 0:32
 * @Version: 1.0
 */
public class AccessForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
