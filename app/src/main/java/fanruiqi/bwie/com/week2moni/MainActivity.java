package fanruiqi.bwie.com.week2moni;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import fanruiqi.bwie.com.week2moni.bean.LoginBean;
import fanruiqi.bwie.com.week2moni.bean.Register;
import fanruiqi.bwie.com.week2moni.util.NetUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mPhone;
    private EditText mPass;
    private CheckBox mRememberPassword;
    private CheckBox mAutoLogin;
    private Button mButton;
    private Button QQ_Share;
    private Button QQ_login;
    String urlString="http://120.27.23.105/user/reg";
    String loginurl="http://www.zhaoapi.cn/user/login";
    private Button mButton_zc;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        //initData();

        mSharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        boolean remeberPass = mSharedPreferences.getBoolean("a", false);
        if (remeberPass){
            String user = mSharedPreferences.getString("user", "");
            String pass = mSharedPreferences.getString("pass", "");
            mPhone.setText(user);
            mPass.setText(pass);
            mRememberPassword.setChecked(true);
        }

        boolean Log = mSharedPreferences.getBoolean("b", false);
        if (Log){
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
            mAutoLogin.setChecked(true);
        }


    }

    private void initData() {

    }

    private void findViews() {
        mPhone = findViewById(R.id.edit_phoneNumber);
        mPass = findViewById(R.id.edit_password);
        mRememberPassword =findViewById(R.id.c1);
        mAutoLogin = findViewById(R.id.c2);
        mButton = findViewById(R.id.btn);
        mButton_zc = findViewById(R.id.zc);
        QQ_Share = findViewById(R.id.QQ_Share);
        QQ_login = findViewById(R.id.QQ_login);

        mButton.setOnClickListener(this);
        mButton_zc.setOnClickListener(this);
        QQ_Share.setOnClickListener(this);
        QQ_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn:   //登录

                Login();

                break;
            case R.id.zc:    //注册

                Register();

                break;
            case R.id.QQ_Share:   //第三方分享
                break;
            case R.id.QQ_login: //第三方登录

                DisanfangLogin();

                break;
        }
    }



    private void Login() {  //登录

        if (mRememberPassword.isChecked()){

            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString("user",mPhone.getText().toString());
            edit.putString("pass",mPass.getText().toString());
            edit.putBoolean("a",true);
            edit.commit();

        }else {

        }

        if (mAutoLogin.isChecked()){

            mRememberPassword.setChecked(true);
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putBoolean("b",true);
            edit.commit();

        }else {

        }

        String phone = mPhone.getText().toString();
        String pass = mPass.getText().toString();

        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pass)){
            Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else {

            HashMap<String, String> map = new HashMap<>();
            map.put("mobile",phone);
            map.put("password",pass);

            NetUtil.getInstance().doPost(loginurl, map, new NetUtil.OnCallback() {
                @Override
                public void onFail() {

                }

                @Override
                public void onResponse(String json) {

                    LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                    String code = loginBean.getCode();
                    if (code.equals("0")){
                        startActivity(new Intent(MainActivity.this,SecondActivity.class));
                        //Toast.makeText(MainActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this,"登录失败!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void Register() {  //注册
        String phone = mPhone.getText().toString();
        String pass = mPass.getText().toString();

        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pass)){
            Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else {

            HashMap<String, String> map = new HashMap<>();
            map.put("mobile",phone);
            map.put("password",pass);

            NetUtil.getInstance().doPost(urlString, map, new NetUtil.OnCallback() {
                @Override
                public void onFail() {

                }

                @Override
                public void onResponse(String json) {

                    Register register = new Gson().fromJson(json, Register.class);
                    String code = register.getCode();
                    if (code.equals("0")){
                        Toast.makeText(MainActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this,"注册失败!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void DisanfangLogin() {

        UMShareAPI umShareAPI =  UMShareAPI.get(MainActivity.this);

        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            /**
             * 开始登录回调
             * @param share_media
             */
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.i("dj", "UMAuthListener onStart");
            }

            /**
             * 登录完成
             * @param share_media
             * @param i
             * @param map
             */
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.i("dj", "UMAuthListener onComplete");

                //获取名字
                String name  = map.get("screen_name");
                //获取头像
                String img  = map.get("profile_image_url");

                Log.i("dj", "name is "+ name);
                Log.i("dj", "img is "+ img);
                SharedPreferences.Editor edit = mSharedPreferences.edit();
                edit.putString("img",img);
                edit.putString("name",name);
                edit.commit();
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }

            /**
             * 登录失败
             * @param share_media
             * @param i
             * @param throwable
             */
            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.i("dj", "UMAuthListener onError" + throwable.getLocalizedMessage());
            }

            /**
             * 登录取消
             * @param share_media
             * @param i
             */
            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.i("dj", "UMAuthListener onCancel");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
