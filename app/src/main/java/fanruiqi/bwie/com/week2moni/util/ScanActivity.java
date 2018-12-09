package fanruiqi.bwie.com.week2moni.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import fanruiqi.bwie.com.week2moni.R;

public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate{

    private ZXingView mZXingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan);
         mZXingView= findViewById(R.id.zv);
         mZXingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mZXingView.startCamera();
        mZXingView.startSpotAndShowRect();
        mZXingView.openFlashlight();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mZXingView.startCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZXingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
