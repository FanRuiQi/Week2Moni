package fanruiqi.bwie.com.week2moni.model;

import fanruiqi.bwie.com.week2moni.callback.MyCallBack;

public interface IModel {

    void requestData(String url, String params, MyCallBack myCallBack);
}
