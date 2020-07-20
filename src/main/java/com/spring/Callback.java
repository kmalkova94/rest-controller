package com.spring;

import lombok.AllArgsConstructor;
import org.springframework.util.concurrent.ListenableFutureCallback;

@AllArgsConstructor
public class Callback implements ListenableFutureCallback {
    private String message;

    @Override
    public void onFailure(Throwable throwable) {
        System.out.println("Unable to send message=["
                + message + "] due to : " + throwable.getMessage());
    }

    @Override
    public void onSuccess(Object o) {
        System.out.println("Sent message=[" + message + "]");
    }
}
