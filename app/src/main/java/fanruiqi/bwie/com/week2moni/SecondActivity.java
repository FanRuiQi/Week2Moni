package fanruiqi.bwie.com.week2moni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import fanruiqi.bwie.com.week2moni.fragment.Afrag;
import fanruiqi.bwie.com.week2moni.fragment.Bfrag;

public class SecondActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final ViewPager pager =findViewById(R.id.pager);
        RadioGroup radioGroup = findViewById(R.id.group);

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        fragment = new Afrag();
                        break;
                    case 1:
                        fragment = new Bfrag();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.r1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.r2:
                        pager.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}
