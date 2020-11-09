package com.example.vegetables.common;



import java.util.Date;

/**
 * 请求失败的返回
 *
 */
public class ErrorResponseData extends ResponseData {

    /**
     *
     * 时间
     */
    private Date timestamp;

    /**
     *
     * 路径
     */
    private String path;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 异常的具体类名称
     */
    private String exceptionClazz;

    public ErrorResponseData(String message) {
        super(false, ResponseData.DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponseData(Integer code, String message) {
        super(false, code, message, null);
    }

    // public ErrorResponseData(Class<? extends Exception> _class, Integer code,
    // String message) {
    // super(false, code, message, null);
    // this.exceptionClazz = _class.getName();
    //
    // }

    public ErrorResponseData(Class<? extends Throwable> _class, Integer code, String message) {
        super(false, code, message, null);
        this.exceptionClazz = _class.getName();

    }

    public ErrorResponseData(Integer code, String message, Date timestamp, String path) {
        super(false, code, message, null);
        this.timestamp = timestamp;
        this.path = path;
    }

    public ErrorResponseData(Class<? extends Throwable> _class, Integer code, String message, Date timestamp,
                             String path) {
        super(false, code, message, null);
        this.exceptionClazz = _class.getName();
        this.timestamp = timestamp;
        this.path = path;
    }

    public ErrorResponseData(Integer code, String message, Object object) {
        super(false, code, message, object);
    }

    public ErrorResponseData(Class<? extends Exception> _class, Integer code, String message, Object object) {
        super(false, code, message, object);
        this.exceptionClazz = _class.getName();
    }

    public String getExceptionClazz() {
        return exceptionClazz;
    }

    public void setExceptionClazz(String exceptionClazz) {
        this.exceptionClazz = exceptionClazz;
    }

}

