package team.iyooo.id.doktercanggih.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import team.iyooo.id.doktercanggih.R;


/**
 * Created by tikafby on 2/20/2016.
 */
public class AccountBiodataFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_account_biodata,container,false);
        return v;
    }
}
