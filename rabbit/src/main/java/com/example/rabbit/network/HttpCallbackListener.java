package com.example.rabbit.network;

public interface HttpCallbackListener {
    public void onSuccess(String response);
    public void onError(Exception e);
}
