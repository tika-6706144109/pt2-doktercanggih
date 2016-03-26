package team.iyooo.id.doktercanggih.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.apis.UserApi;
import team.iyooo.id.doktercanggih.apis.apidao.BaseDao;
import team.iyooo.id.doktercanggih.apis.core.ApiCallback;
import team.iyooo.id.doktercanggih.apis.param.LoginParam;
import team.iyooo.id.doktercanggih.base.BaseActivity;
import team.iyooo.id.doktercanggih.databinding.ActivityLoginBinding;
import team.iyooo.id.doktercanggih.utils.PrefHelper;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding mBinding;
    private ProgressDialog mDialog;
    private LoginParam loginParam = new LoginParam();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        toolbar_title.setText("Dokter Canggih");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Mohon Tunggu");
        mDialog.setCancelable(false);
    }

    public static void startThisActivity(Context context){
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }

    public void onClickLogin(View view){
        if(isValid()){
            callApiLogin(loginParam);
        }
    }

    public Boolean isValid(){
        Boolean valid = true;
        loginParam.username = mBinding.username.getText().toString();
        loginParam.password = mBinding.password.getText().toString();
        if(mBinding.username.getText().toString().equals("")&&!mBinding.password.getText().toString().equals("")){
            valid = false;
            Snackbar.make(mBinding.getRoot(), "Username harus diisi", Snackbar.LENGTH_SHORT).show();
        }
        if(mBinding.password.getText().toString().equals("")&&!mBinding.username.getText().toString().equals("")){
            valid = false;
            Snackbar.make(mBinding.getRoot(), "Password harus diisi", Snackbar.LENGTH_SHORT).show();
        }
        if(mBinding.password.getText().toString().equals("")&&mBinding.username.getText().toString().equals("")){
            valid = false;
            Snackbar.make(mBinding.getRoot(), "Username & Password harus diisi", Snackbar.LENGTH_SHORT).show();
        }
        return valid;
    }

    private ApiCallback<BaseDao<UserApi.ApiDaoUser>> loginListener = new ApiCallback<BaseDao<UserApi.ApiDaoUser>>() {
        @Override
        public void onApiSuccess(BaseDao<UserApi.ApiDaoUser> result, String rawJson) {
            mDialog.dismiss();
            if(result!=null){
                if(result.status){
                    PrefHelper.saveLogin(LoginActivity.this,result.data);
                    finish();
                    ConsulActivity.startThisActivity(LoginActivity.this);
                }else{
                    Snackbar.make(mBinding.getRoot(), result.message, Snackbar.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onApiError(String errorMessage) {
            mDialog.dismiss();
            Snackbar.make(mBinding.getRoot(), "Anda Tidak Ada Internet", Snackbar.LENGTH_SHORT).show();
            try{
                Log.e("Erorr Login", errorMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private void callApiLogin(LoginParam loginParam){
        mDialog.show();
        UserApi.callApiLoginUser(mContext, loginParam.username, loginParam.password, loginListener);
    }
}
