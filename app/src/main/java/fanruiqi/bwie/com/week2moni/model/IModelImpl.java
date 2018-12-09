package fanruiqi.bwie.com.week2moni.model;

import com.google.gson.Gson;

import java.util.List;

import fanruiqi.bwie.com.week2moni.adapter.BannerAdapter;
import fanruiqi.bwie.com.week2moni.bean.BannerBean;
import fanruiqi.bwie.com.week2moni.callback.MyCallBack;
import fanruiqi.bwie.com.week2moni.util.NetUtil;

public class IModelImpl implements IModel{
    @Override
    public void requestData(String url, String params, final MyCallBack myCallBack) {
        NetUtil.getInstance().doGet(url, new NetUtil.OnCallback() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String json) {

                BannerBean bannerBean = new Gson().fromJson(json, BannerBean.class);
                myCallBack.backData(bannerBean);
            }
        });
    }
}
