package app.sean.baseandroid.httpService.service;


/**
 * Created by sean on 16/12/16.
 */

public class HttpResponse<T> {
    private int code;
    private String message;
    private T value;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getValue() {
        return value;
    }
}
