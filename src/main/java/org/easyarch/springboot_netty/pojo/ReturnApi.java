package org.easyarch.springboot_netty.pojo;

/**
 * @ClassName ReturnApi
 * @Description TODO
 * @Author Liyihe
 * @Date 2019/09/16 下午9:50
 * @Version 1.0
 */
public class ReturnApi {
    private int status;
    private String message;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ReturnApi(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
