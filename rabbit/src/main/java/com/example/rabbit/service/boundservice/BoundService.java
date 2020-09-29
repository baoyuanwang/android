package com.example.rabbit.service.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoundService extends Service {
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();//返回MyBinder对象
    }

    //c创建获取service的内部类
    public class MyBinder extends Binder {
        //创建获取service的方法
        public BoundService getBoundService() {
            return BoundService.this;//返回当前的Service类
        }
    }

    public List<String> getRandomNumber() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String numberStr = (new Random().nextInt(33) + 1) + "";
            if(numberStr.length()==1){
                numberStr="0"+numberStr;
            }
            list.add(numberStr);
        }
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
