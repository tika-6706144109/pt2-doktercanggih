package team.iyooo.id.doktercanggih.apis;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

import retrofit.Call;
import team.iyooo.id.doktercanggih.apis.apidao.BaseDao;
import team.iyooo.id.doktercanggih.apis.core.ApiCallback;
import team.iyooo.id.doktercanggih.apis.core.MyCallback;
import team.iyooo.id.doktercanggih.apis.core.TanyaDokterApi;
import team.iyooo.id.doktercanggih.apis.param.CommentParam;

/**
 * Created by tikafby on 2/28/2016.
 */
public class ConsulApi {
    public static Call<BaseDao<List<ApiDaoConsult>>> mCallConsulAll;
    public static Call<BaseDao<List<ApiDaoConsult>>> mCallConsulAnswer;
    public static Call<BaseDao<List<ApiDaoConsult>>> mCallConsulNotAnswer;
    public static Call<BaseDao<ApiDaoConsultDetail>> mCallConsulDetail;
    public static Call<BaseDao<ApiDaoConsultDetail.CommentDao>> mCallCreateComment;
    public static Call<BaseDao> mCallAnswer;

    public static void callApiListConsul(Context context, ApiCallback<BaseDao<List<ApiDaoConsult>>> apiCallback){
        mCallConsulAll = TanyaDokterApi.getInstance(context).getConsulApi().getListConsul();
        mCallConsulAll.enqueue(new MyCallback<>(apiCallback));
    }

    public static void callApiConsulAnswer(Context context, String doctor_id, ApiCallback<BaseDao<List<ApiDaoConsult>>> apiCallback){
        mCallConsulAnswer = TanyaDokterApi.getInstance(context).getConsulApi().getConsulAnswer(doctor_id);
        mCallConsulAnswer.enqueue(new MyCallback<>(apiCallback));
    }

    public static void callApiConsulNotAnswer(Context context, String doctor_id, ApiCallback<BaseDao<List<ApiDaoConsult>>> apiCallback){
        mCallConsulNotAnswer = TanyaDokterApi.getInstance(context).getConsulApi().getConsulNotAnswer(doctor_id);
        mCallConsulNotAnswer.enqueue(new MyCallback<>(apiCallback));
    }

    public static void callApiConsulDetail(Context context, String consult_id, ApiCallback<BaseDao<ApiDaoConsultDetail>> apiCallback){
        mCallConsulDetail = TanyaDokterApi.getInstance(context).getConsulApi().getConsulDetail(consult_id);
        mCallConsulDetail.enqueue(new MyCallback<>(apiCallback));
    }

    public static void callApiCreateComment(Context context, CommentParam mParam, ApiCallback<BaseDao<ApiDaoConsultDetail.CommentDao>> apiCallback){
        mCallCreateComment = TanyaDokterApi.getInstance(context).getConsulApi().postComment(mParam.consult_id, mParam.answer, mParam.user_id);
        mCallCreateComment.enqueue(new MyCallback<>(apiCallback));
    }

    public static void callApiAnswer(Context context, String consult_id, String answer, ApiCallback<BaseDao> apiCallback){
        mCallAnswer = TanyaDokterApi.getInstance(context).getConsulApi().postAnswer(consult_id, answer);
        mCallAnswer.enqueue(new MyCallback<>(apiCallback));
    }

    public class ApiDaoDokter implements Serializable{
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

    public class ApiDaoConsult implements Serializable{
        public String consult_id;
        public String title;
        public String ask;
        public String answer;
        public String date;
        public String is_answer;
        public String pasien_id;
        public String pasien;
        public String pasien_photo;
        public String doctor_id;
        public String doctor;
        public String doctor_spesialis;
        public String doctor_photo;
    }

    public class ApiDaoConsultDetail implements Serializable {
        public DokterDao dokter;
        public PasienDao user;
        public ConsultDao consult;
        public List<CommentDao> comment;

        public class DokterDao implements Serializable {
            public String doctor_id;
            public String nama;
            public String spesialis;
            public String photo;
        }

        public class PasienDao implements Serializable {
            public String pasien_id;
            public String nama;
            public String photo;
        }

        public class ConsultDao implements Serializable {
            public String consult_id;
            public String title;
            public String ask;
            public String answer;
            public String date;
            public String is_answer;
            public String pasien_id;
            public String doctor_id;
        }

        public class CommentDao implements Serializable {
            public String consult_answer_id;
            public String consult_id;
            public String answer;
            public String date;
            public String user_id;
            public String username;
            public String nama;
        }
    }
}
