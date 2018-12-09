package fanruiqi.bwie.com.week2moni.view;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * 第一步：导入jar包和res文件，将jar包add as lib..
 * 第二步：自定义Application 在onCreate中完成以下两行代码（所有参数没有自己注册过不要改）
 * 第三步：在Manifests中添加配置，详见Manifests注释（不要随意修改）
 * 第四步：在需要调用的地方写调用，详见MainActivity中
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
