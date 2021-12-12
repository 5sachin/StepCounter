package com.example.stepcounter.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.stepcounter.Adapters.SignUpAdapter;
import com.example.stepcounter.R;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class SignUpActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("SignIn"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final SignUpAdapter adapter = new SignUpAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}