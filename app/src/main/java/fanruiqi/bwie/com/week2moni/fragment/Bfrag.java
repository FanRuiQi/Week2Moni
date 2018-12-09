package fanruiqi.bwie.com.week2moni.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fanruiqi.bwie.com.week2moni.MainActivity;
import fanruiqi.bwie.com.week2moni.R;
import fanruiqi.bwie.com.week2moni.util.ScanActivity;

import static android.content.Context.MODE_PRIVATE;

public class Bfrag extends Fragment{
    private SharedPreferences mSharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.bfrag, null);
        mSharedPreferences = getActivity().getSharedPreferences("sp", MODE_PRIVATE);

        String img = mSharedPreferences.getString("img", null);
        String name = mSharedPreferences.getString("name", null);
        ImageView imageView = view.findViewById(R.id.b_img);
        TextView textView = view.findViewById(R.id.b_text);
        Button qr = view.findViewById(R.id.QR_code);

        Glide.with(getActivity()).load(img).into(imageView);

        textView.setText(name);

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkPermission();
            }
        });
        return view;
    }

    private void checkPermission(){
        startActivity(new Intent(getActivity(), ScanActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100&&grantResults[0]== PackageManager.PERMISSION_GRANTED){

            startActivity(new Intent(getActivity(),ScanActivity.class));
        }
    }
}
