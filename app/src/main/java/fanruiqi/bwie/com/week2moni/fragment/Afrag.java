package fanruiqi.bwie.com.week2moni.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.List;

import fanruiqi.bwie.com.week2moni.R;
import fanruiqi.bwie.com.week2moni.adapter.BannerAdapter;
import fanruiqi.bwie.com.week2moni.adapter.ItemAdapter;
import fanruiqi.bwie.com.week2moni.bean.BannerBean;
import fanruiqi.bwie.com.week2moni.bean.ResponseBean;
import fanruiqi.bwie.com.week2moni.precenter.IPrecenterImpl;
import fanruiqi.bwie.com.week2moni.util.NetUtil;
import fanruiqi.bwie.com.week2moni.view.IView;

public class Afrag extends Fragment implements IView{
    private ViewPager mPager;
    private RecyclerView mRecyclerView;
    String URL_BANNER="http://www.xieast.com/api/banner.php";
    String URL_TELANGPU="http://www.xieast.com/api/news/news.php";
    IPrecenterImpl iPrecenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.afrag, null);

        mPager = view.findViewById(R.id.pager);
        mRecyclerView = view.findViewById(R.id.rv);


        iPrecenter = new IPrecenterImpl(this);
        initData();
        return view;
    }

    private void initData() {

        iPrecenter.startRequest(URL_BANNER,null);
        /*NetUtil.getInstance().doGet(URL_BANNER, new NetUtil.OnCallback() {
            @Override
            public void onFail() {
                
            }

            @Override
            public void onResponse(String json) {

                BannerBean bannerBean = new Gson().fromJson(json, BannerBean.class);
                List<BannerBean.DataBean> data = bannerBean.getData();
                BannerAdapter bannerAdapter = new BannerAdapter(getLayoutInflater());
                bannerAdapter.setData(data);
                mPager.setAdapter(bannerAdapter);
            }
        });*/
        
        NetUtil.getInstance().doGet(URL_TELANGPU, new NetUtil.OnCallback() {
            @Override
            public void onFail() {
                
            }

            @Override
            public void onResponse(String json) {

                ResponseBean responseBean = new Gson().fromJson(json, ResponseBean.class);
                List<ResponseBean.DataBean> data = responseBean.getData();
                ItemAdapter itemAdapter = new ItemAdapter(getActivity(), data);
                mRecyclerView.setAdapter(itemAdapter);

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            }
        });
    }

    @Override
    public void shouData(Object data) {
        BannerBean bannerBean = (BannerBean) data;
        List<BannerBean.DataBean> data1 = bannerBean.getData();
        BannerAdapter bannerAdapter = new BannerAdapter(getLayoutInflater());
        bannerAdapter.setData(data1);
        mPager.setAdapter(bannerAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPrecenter.Detoch();
    }
}
