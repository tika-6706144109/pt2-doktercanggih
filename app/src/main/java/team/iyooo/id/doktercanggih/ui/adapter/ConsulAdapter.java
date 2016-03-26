package team.iyooo.id.doktercanggih.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import team.iyooo.id.doktercanggih.BR;
import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.apis.ConsulApi;
import team.iyooo.id.doktercanggih.databinding.RowConsulBinding;
import team.iyooo.id.doktercanggih.ui.viewmodel.ConsulRowViewModel;

/**
 * Created by Tikafby on 2/20/2016.
 */
public class ConsulAdapter extends RecyclerView.Adapter<ConsulAdapter.BindingHolder> {

    private RowConsulBinding mBinding;
    private List<ConsulApi.ApiDaoConsult> data = new ArrayList<>();
    private Context mContext;
    private AdapterListener listener;
    private ConsulRowViewModel viewModel;

    public ConsulAdapter(List<ConsulApi.ApiDaoConsult> data, AdapterListener adapterListener) {
        this.data = data;
        this.listener = adapterListener;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.row_consul, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(final BindingHolder holder, final int position) {
        final ConsulApi.ApiDaoConsult item = data.get(position);
        viewModel = new ConsulRowViewModel(mContext, item);
        holder.getBinding().setVariable(BR.vm, viewModel);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder{

        private RowConsulBinding binding;

        public BindingHolder(RowConsulBinding mBinding) {
            super(mBinding.getRoot());
            binding = mBinding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public interface AdapterListener{
        void onClick(ConsulApi.ApiDaoConsult data, int position);
    }
}
