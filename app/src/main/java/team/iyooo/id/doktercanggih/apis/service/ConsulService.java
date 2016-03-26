package team.iyooo.id.doktercanggih.apis.service;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;
import team.iyooo.id.doktercanggih.apis.ConsulApi;
import team.iyooo.id.doktercanggih.apis.apidao.BaseDao;

/**
 * Created by tikafby on 05-Mar-16.
 */
public interface ConsulService {

    @Headers("Cache-Control: max-age=640000")
    @GET("consult/consultAll")
    Call<BaseDao<List<ConsulApi.ApiDaoConsult>>> getListConsul(
    );

    @Headers("Cache-Control: max-age=640000")
    @GET("consult/consultDokterAnswer")
    Call<BaseDao<List<ConsulApi.ApiDaoConsult>>> getConsulAnswer(
            @Query("doctor_id") String doctor_id
    );

    @Headers("Cache-Control: max-age=640000")
    @GET("consult/consultDokterNotAnswer")
    Call<BaseDao<List<ConsulApi.ApiDaoConsult>>> getConsulNotAnswer(
            @Query("doctor_id") String doctor_id
    );

    @Headers("Cache-Control: max-age=640000")
    @GET("consult/consultDetail")
    Call<BaseDao<ConsulApi.ApiDaoConsultDetail>> getConsulDetail(
            @Query("consult_id") String consult_id
    );

    @FormUrlEncoded
    @POST("consult/createComment")
    Call<BaseDao<ConsulApi.ApiDaoConsultDetail.CommentDao>> postComment(
            @Field("consult_id") String consult_id,
            @Field("answer") String answer,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("consult/consultAnswer")
    Call<BaseDao> postAnswer(
            @Field("consult_id") String consult_id,
            @Field("answer") String answer
    );
}
