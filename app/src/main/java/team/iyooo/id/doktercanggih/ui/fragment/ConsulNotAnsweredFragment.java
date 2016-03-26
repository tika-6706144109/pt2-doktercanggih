package team.iyooo.id.doktercanggih.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.apis.ConsulApi;
import team.iyooo.id.doktercanggih.apis.apidao.BaseDao;
import team.iyooo.id.doktercanggih.apis.core.ApiCallback;
import team.iyooo.id.doktercanggih.databinding.FragmentConsulBinding;
import team.iyooo.id.doktercanggih.ui.activity.ConsulDetailActivity;
import team.iyooo.id.doktercanggih.ui.adapter.ConsulAdapter;
import team.iyooo.id.doktercanggih.utils.PrefHelper;

/**
 * Created by Tikafby on 2/20/2016.
 */
public class ConsulNotAnsweredFragment extends Fragment implements ConsulAdapter.AdapterListener {

    private FragmentConsulBinding mBinding;
    private ConsulAdapter mAdapter;
    private List<ConsulApi.ApiDaoConsult> listConsul = new ArrayList<>();
    private LinearLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_consul,container,false);
        mBinding = FragmentConsulBinding.bind(v);
        initRecycler();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        callApiConsult(PrefHelper.getLogin(getActivity()).user_id);
    }

    public void initRecycler(){
        mAdapter = new ConsulAdapter(listConsul, this);
        manager = new LinearLayoutManager(getActivity());
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onClick(ConsulApi.ApiDaoConsult data, int position) {
        ConsulDetailActivity.startThisActivity(getActivity(), data);
    }

    private ApiCallback<BaseDao<List<ConsulApi.ApiDaoConsult>>> consultAllListener = new ApiCallback<BaseDao<List<ConsulApi.ApiDaoConsult>>>() {
        @Override
        public void onApiSuccess(BaseDao<List<ConsulApi.ApiDaoConsult>> result, String rawJson) {
            if(result!=null){
                if (result.status){
                    if(!result.data.isEmpty()){
                        listConsul.clear();
                        listConsul.addAll(result.data);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

        @Override
        public void onApiError(String errorMessage) {
            try{
                Log.e("Error Consult All", errorMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private void callApiConsult(String doctor_id) {
        ConsulApi.callApiConsulNotAnswer(getActivity(), doctor_id, consultAllListener);
    }
}
