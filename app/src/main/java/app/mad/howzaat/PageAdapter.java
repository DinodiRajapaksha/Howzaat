package app.mad.howzaat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter
{
    private int noOfTabs;

    PageAdapter(FragmentManager fm, int noOfTabs)
    {
        super(fm);
        this.noOfTabs = noOfTabs;
    }


    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: return new CompletedFragment();
            case 1: return new OngoingFragment();
            case 2: return new UpcomingFragment();

            default: return null;
        }

    }

    @Override
    public int getCount()
    {
        return noOfTabs;
    }
}
