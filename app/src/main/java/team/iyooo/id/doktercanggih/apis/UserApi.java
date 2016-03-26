package team.iyooo.id.doktercanggih.apis;

import android.content.Context;

import java.io.Serializable;

import retrofit.Call;
import team.iyooo.id.doktercanggih.apis.apidao.BaseDao;
import team.iyooo.id.doktercanggih.apis.core.ApiCallback;
import team.iyooo.id.doktercanggih.apis.core.MyCallback;
import team.iyooo.id.doktercanggih.apis.core.TanyaDokterApi;

/**
 * Created by tikafby on 2/28/2016.
 */
public class UserApi {
    public static Call<BaseDao<ApiDaoUser>> mCallUser;

    public static void callApiLoginUser(Context context, String username, String password, ApiCallback<BaseDao<ApiDaoUser>> apiCallback){
        mCallUser = TanyaDokterApi.getInstance(context).getUserApi().postLogin(username, password);
        mCallUser.enqueue(new MyCallback<>(apiCallback));
    }

    public class ApiDaoUser implements Serializable{
        public String user_id;
        public String username;
        public String password;
        public String nama;
        public String spesialis;
        public String alamat;
        public String phone;
        public String email;
        public String date;
        public String jk;
        public String photo;
        public String status;
        public String role_id;
        public String role;
    }
}
