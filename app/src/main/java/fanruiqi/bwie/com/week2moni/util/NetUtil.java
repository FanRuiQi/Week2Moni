package fanruiqi.bwie.com.week2moni.util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetUtil {

    private static NetUtil mNetUtil;
    Handler handler;
    private final OkHttpClient mOkHttpClient;

    private NetUtil() {
         handler = new Handler(Looper.getMainLooper());

         mOkHttpClient = new OkHttpClient.Builder()
                 .readTimeout(5000, TimeUnit.MILLISECONDS)
                 .writeTimeout(5000,TimeUnit.MILLISECONDS)
                 .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
    }

    public static NetUtil getInstance(){
        if (mNetUtil==null){
            synchronized (NetUtil.class){
                if (mNetUtil==null){
                    return mNetUtil=new NetUtil();
                }
            }
        }
        return mNetUtil;
    }

    public interface OnCallback{
        void onFail();
        void onResponse(String json);
    }

    public void doPost(String url, Map<String,String> map,final OnCallback onCallback){

        FormBody.Builder builder = new FormBody.Builder();

        if (map!=null){
            for (String k:map.keySet()){
                builder.add(k,map.get(k));
            }
        }

        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {



                try {
                    if (response!=null&&response.isSuccessful()){
                        final String json = response.body().string();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (onCallback!=null){
                                    onCallback.onResponse(json);
                                }
                            }
                        });

                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public void doGet(String url,final OnCallback onCallback){


        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {



                try {
                    if (response!=null&&response.isSuccessful()){
                        String json = response.body().string();

                        if (onCallback!=null){
                            onCallback.onResponse(json);
                        }
                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
