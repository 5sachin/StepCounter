package com.example.stepcounter.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.stepcounter.Fragments.LoginFragment;
import com.example.stepcounter.Fragments.SignUpFragment;

public class SignUpAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;
    public SignUpAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginFragment footballFragment = new LoginFragment();
                return footballFragment;
            case 1:
                SignUpFragment cricketFragment = new SignUpFragment();
                return cricketFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
