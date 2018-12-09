package fanruiqi.bwie.com.week2moni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fanruiqi.bwie.com.week2moni.R;
import fanruiqi.bwie.com.week2moni.bean.ResponseBean;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Context mContext;
    private List<ResponseBean.DataBean> mList;

    public ItemAdapter(Context context, List<ResponseBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mTextView.setText(mList.get(position).getAuthor_name());
        holder.mTextView02.setText(mList.get(position).getTitle());
        Glide.with(mContext).load(mList.get(position).getThumbnail_pic_s()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        TextView mTextView02;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_img);
            mTextView = itemView.findViewById(R.id.item_text01);
            mTextView02 = itemView.findViewById(R.id.item_text02);
        }
    }
}
