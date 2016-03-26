package team.iyooo.id.doktercanggih.base;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.ButterKnife;
import team.iyooo.id.doktercanggih.R;


/**
 * Created by tikafby on 2/14/2016.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Toolbar toolbar;
    protected TextView toolbar_title;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initActivity();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void initActivity() {
        //Set default Shared Preferences
        // prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //init toolbar and setting it as the actionbar
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_tittle);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }else {
            return false;
        }
    }
}
