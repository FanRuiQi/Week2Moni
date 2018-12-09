package fanruiqi.bwie.com.week2moni.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fanruiqi.bwie.com.week2moni.bean.BannerBean;


public class BannerAdapter extends PagerAdapter{
    ArrayList<BannerBean.DataBean> mList;
    private LayoutInflater mLayoutInflater;

    public BannerAdapter(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
        mList=new ArrayList<>();
    }


    public void setData(List<BannerBean.DataBean> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new ImageView(mLayoutInflater.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mLayoutInflater.getContext()).load(mList.get(position).getImg()).into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
