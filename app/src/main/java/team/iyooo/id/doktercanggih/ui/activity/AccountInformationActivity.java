package team.iyooo.id.doktercanggih.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.Bind;
import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.base.BaseActivity;
import team.iyooo.id.doktercanggih.ui.fragment.AccountAkunFragment;
import team.iyooo.id.doktercanggih.ui.fragment.AccountBiodataFragment;

/**
 * Created by tikafby on 2/20/2016.
 */
public class AccountInformationActivity extends BaseActivity {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private AccountPagerAdapter adapter;

    private CharSequence titles[] = {"Data Diri","Data Akun"};
    private int numOfTabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        toolbar_title.setText("Informasi Akun");
        initView();
    }

    public static void startThisActivity(Context context){
        Intent i = new Intent(context, AccountInformationActivity.class);
        context.startActivity(i);
    }

    public void initView(){
        adapter = new AccountPagerAdapter(getSupportFragmentManager(), titles, numOfTabs);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(viewPager);
    }

    private class AccountPagerAdapter extends FragmentStatePagerAdapter {

        CharSequence titles[];
        int numbOfTabs;

        public AccountPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
            super(fm);
            this.titles = mTitles;
            this.numbOfTabs = mNumbOfTabsumb;

        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new AccountBiodataFragment();
            } else {
                return new AccountAkunFragment();
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
