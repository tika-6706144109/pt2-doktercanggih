package team.iyooo.id.doktercanggih.apis.service;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import team.iyooo.id.doktercanggih.apis.UserApi;
import team.iyooo.id.doktercanggih.apis.apidao.BaseDao;

/**
 * Created by tikafby on 2/28/2016.
 */
public interface UserService {
    @FormUrlEncoded
    @POST("user/loginDokter")
    Call<BaseDao<UserApi.ApiDaoUser>> postLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user/register")
    Call<BaseDao<UserApi.ApiDaoUser>> postRegister(
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("date") String date,
            @Field("jk") String jk,
            @Field("username") String username,
            @Field("password") String password
    );
}
