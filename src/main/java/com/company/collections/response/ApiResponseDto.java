package com.company.collections.response;

public class ApiResponseDto {

    private String msg;
    private Object data;
    private Object errors;
    private Boolean status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object error) {
        this.errors = error;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean flag) {
        this.status = flag;
    }

    @Override
    public String toString() {
        return "ApiResponseDto{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", error=" + errors +
                ", status=" + status +
                '}';
    }

}
