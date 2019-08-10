package com.example.travelerpractise.FragmentForTreePage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TreePagesAdapter extends FragmentPagerAdapter {


    public TreePagesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new BudgetFragment();
            case 1 : return new ScheduleFragment();
            case 2 : return new WritingDiaryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 : return "Budget";
            case 1 : return "Schedule";
            case 2 : return "Diary";
        }

        return null;


    }
}
