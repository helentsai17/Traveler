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
        Fragment page = null;
        switch (position){
            case 0 : page = new BudgetFragment();break;
            case 1 : page = new ScheduleFragment();break;
            case 2 : page = new WritingDiaryFragment();break;

        }
        return page;
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
