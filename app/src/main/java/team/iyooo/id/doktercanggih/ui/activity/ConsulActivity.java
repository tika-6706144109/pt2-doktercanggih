package team.iyooo.id.doktercanggih.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import butterknife.Bind;
import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.base.BaseActivity;
import team.iyooo.id.doktercanggih.ui.fragment.ConsulAllFragment;
import team.iyooo.id.doktercanggih.ui.fragment.ConsulAnsweredFragment;
import team.iyooo.id.doktercanggih.ui.fragment.ConsulNotAnsweredFragment;
import team.iyooo.id.doktercanggih.utils.PrefHelper;

/**
 * Created by Tikafby on 2/20/2016.
 */
public class ConsulActivity extends BaseActivity {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    ConsulPagerAdapter adapter;

    private CharSequence titles[] = {"Semua","Sudah Terjawab", "Belum Terjawab"};
    private int numOfTabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PrefHelper.getLogin(this)==null){
            finish();
            LoginActivity.startThisActivity(this);
        }

        setContentView(R.layout.activity_consul);
        toolbar_title.setText("Konsultasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        initView();
    }

    public static void startThisActivity(Context context){
        Intent i = new Intent(context, ConsulActivity.class);
        context.startActivity(i);
    }

    public void initView(){
        adapter = new ConsulPagerAdapter(getSupportFragmentManager(), titles, numOfTabs);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(viewPager);
    }

    private class ConsulPagerAdapter extends FragmentStatePagerAdapter {

        CharSequence titles[];
        int numbOfTabs;

        public ConsulPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
            super(fm);
            this.titles = mTitles;
            this.numbOfTabs = mNumbOfTabsumb;

        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ConsulAllFragment();
            }else if (position == 1) {
                return new ConsulAnsweredFragment();
            } else {
                return new ConsulNotAnsweredFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return numbOfTabs;
        }
    }

    public void switchPager(int i) {
        viewPager.setCurrentItem(i);
    }
}
