package team.iyooo.id.doktercanggih.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import team.iyooo.id.doktercanggih.BR;
import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.apis.ConsulApi;
import team.iyooo.id.doktercanggih.apis.apidao.BaseDao;
import team.iyooo.id.doktercanggih.apis.core.ApiCallback;
import team.iyooo.id.doktercanggih.apis.param.CommentParam;
import team.iyooo.id.doktercanggih.base.BaseActivity;
import team.iyooo.id.doktercanggih.databinding.ActivityConsulDetailBinding;
import team.iyooo.id.doktercanggih.ui.viewmodel.ConsulDetailViewModel;
import team.iyooo.id.doktercanggih.utils.DateHelper;
import team.iyooo.id.doktercanggih.utils.PrefHelper;


/**
 * Created by tikafby on 2/20/2016.
 */
public class ConsulDetailActivity extends BaseActivity {

    private final static String CONSULT = "consult";
    private ConsulApi.ApiDaoConsult dataConsult;
    private ConsulApi.ApiDaoConsultDetail mData;
    private ConsulDetailViewModel viewModel;
    private ActivityConsulDetailBinding mBinding;
    private CommentParam commentParam = new CommentParam();

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_consul_detail);
        toolbar_title.setText("Detail");

        dataConsult = (ConsulApi.ApiDaoConsult) getIntent().getSerializableExtra(CONSULT);
        viewModel = new ConsulDetailViewModel(this);
        mBinding.setVariable(BR.vm, viewModel);
        callApiDetail(dataConsult.consult_id);

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Mohon Tunggu");
        mDialog.setCancelable(false);
    }

    public static void startThisActivity(Context context, ConsulApi.ApiDaoConsult data){
        Intent i = new Intent(context, ConsulDetailActivity.class);
        i.putExtra(CONSULT, data);
        context.startActivity(i);
    }

    public void onClickAnswer(View view){
        if(!mBinding.answer.getText().toString().equals("")){
            callApiAnswer(mBinding.answer.getText().toString());
        }
    }

    public void onClickComment(View view){
        if(!mBinding.commentText.getText().toString().equals("")){
            commentParam.consult_id = mData.consult.consult_id;
            commentParam.user_id = PrefHelper.getLogin(this).user_id;
            commentParam.answer = mBinding.commentText.getText().toString();
            callApiCreateComment(commentParam);
        }
    }

    public View setLayoutComment(String consult_answer_id, String mName, String mDate, String mComment, String mImageUrl){
        View v = this.getLayoutInflater().inflate(R.layout.row_consul_comment,null);
        ImageView imageUrl = (ImageView) v.findViewById(R.id.imageUrl);
        TextView name = (TextView)v.findViewById(R.id.name);
        TextView date = (TextView)v.findViewById(R.id.date);
        TextView comment = (TextView)v.findViewById(R.id.comment);
        LinearLayout layoutComment = (LinearLayout)v.findViewById(R.id.layoutInfo);

        name.setText(mName);
        date.setText(DateHelper.dateParseToString(mDate,DateHelper.OLDFORMAT, DateHelper.NEWFORMAT));
        comment.setText(mComment);
        layoutComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
        return v;
    }

    private ApiCallback<BaseDao<ConsulApi.ApiDaoConsultDetail>> detailListener = new ApiCallback<BaseDao<ConsulApi.ApiDaoConsultDetail>>() {
        @Override
        public void onApiSuccess(BaseDao<ConsulApi.ApiDaoConsultDetail> result, String rawJson) {
            if(result!=null){
                if(result.data!=null){
                    mData = result.data;
                    viewModel.initData(mData);
                    if(!mData.comment.isEmpty()){
                        for(int i=0;i<mData.comment.size();i++) {
                            mBinding.layoutComment.addView(setLayoutComment(mData.comment.get(i).consult_answer_id,
                                    mData.comment.get(i).nama, mData.comment.get(i).date, mData.comment.get(i).answer,""));
                        }
                    }
                }
            }
        }

        @Override
        public void onApiError(String errorMessage) {
            try{
                Log.e("Error Detail", errorMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private void callApiDetail(String consult_id){
        ConsulApi.callApiConsulDetail(mContext, consult_id, detailListener);
    }

    private ApiCallback<BaseDao> answerListener = new ApiCallback<BaseDao>() {
        @Override
        public void onApiSuccess(BaseDao result, String rawJson) {
            mDialog.dismiss();
            if(result!=null){
                if(result.status){
                    Toast.makeText(ConsulDetailActivity.this, "Jawab berhasil", Toast.LENGTH_SHORT).show();
                    viewModel.initAnswer("1", mBinding.answer.getText().toString());
                }
            }
        }

        @Override
        public void onApiError(String errorMessage) {
            mDialog.dismiss();
            try{
                Log.e("Error Answer", errorMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private void callApiAnswer(String answer){
        ConsulApi.callApiAnswer(mContext, PrefHelper.getLogin(this).user_id, answer, answerListener);
    }

    private ApiCallback<BaseDao<ConsulApi.ApiDaoConsultDetail.CommentDao>> commentListener = new ApiCallback<BaseDao<ConsulApi.ApiDaoConsultDetail.CommentDao>>() {
        @Override
        public void onApiSuccess(BaseDao<ConsulApi.ApiDaoConsultDetail.CommentDao> result, String rawJson) {
            mDialog.dismiss();
            if(result!=null){
                if(result.data!=null){
                    Toast.makeText(ConsulDetailActivity.this, "Komen baru berhasil", Toast.LENGTH_SHORT).show();
                    mBinding.commentText.setText("");
                    mBinding.layoutComment.addView(setLayoutComment(result.data.consult_answer_id,
                            result.data.nama, result.data.date, result.data.answer,""),0);
                }
            }
        }

        @Override
        public void onApiError(String errorMessage) {
            mDialog.dismiss();
            try{
                Log.e("Error Comment", errorMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private void callApiCreateComment(CommentParam commentParam){
        mDialog.show();
        ConsulApi.callApiCreateComment(mContext, commentParam, commentListener);
    }
}
