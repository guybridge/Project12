package au.com.wsit.project12.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import au.com.wsit.project12.ui.fragments.CuriousityFragment;
import au.com.wsit.project12.ui.fragments.OpportunityFragment;
import au.com.wsit.project12.ui.fragments.SpiritFragment;

/**
 * Created by guyb on 28/02/17.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private Context context;

    public SectionsPagerAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new CuriousityFragment();
            case 1:
                return new OpportunityFragment();
            case 2:
                return new SpiritFragment();
        }

        return null;
    }


    @Override
    public int getCount()
    {
        return 3;
    }
}
