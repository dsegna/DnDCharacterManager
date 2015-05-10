package dps924.ddcharactermanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 4;//5;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return new ProfileFragment();
            case 1:
                return new SkillsFragment();
            case 2:
                return new FeatsFragment();
            case 3:
                return new ItemsFragment();
        }
        return new CharactersFragment();
    }
    @Override
    public int getCount() {
        return NUM_PAGES;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Profile";
            case 1:
                return "Skills";
            case 2:
                return "Feats";
            case 3:
                return "Items";
        }
        return "??";
    }
}
