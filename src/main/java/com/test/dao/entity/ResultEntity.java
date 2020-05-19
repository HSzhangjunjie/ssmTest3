package com.test.dao.entity;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.utils
 * @ClassName: ResultEntity
 * @Author: ZhangJunjie
 * @Description: 结果封装类
 * @Date: 2020/5/7 14:57
 * @Version: 1.0
 */
public class ResultEntity<T> {
    private static final String SUCCESS = "SUCCESS";
    private static final String ERROR = "ERROR";
    /**
     * description:封装处理结果
     * create time: 15:03 2020/5/7
     */
    private String result;
    /**
     * description:封装错误消息
     * create time: 15:03 2020/5/7
     */
    private String message;
    /**
     * description:封装返回数据信息
     * create time: 15:03 2020/5/7
     */
    private T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<>(SUCCESS, null, null);
    }

    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<>(SUCCESS, null, data);
    }

    public static <E> ResultEntity<E> failed(String msg) {
        return new ResultEntity<>(ERROR, msg, null);
    }
}
