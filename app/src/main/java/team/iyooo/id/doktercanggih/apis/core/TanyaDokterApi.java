package team.iyooo.id.doktercanggih.apis.core;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import team.iyooo.id.doktercanggih.apis.service.ConsulService;
import team.iyooo.id.doktercanggih.apis.service.UserService;
import team.iyooo.id.doktercanggih.utils.Constant;

/**
 * Created by tikafby on 2/28/16.
 */
public class TanyaDokterApi {

    private static TanyaDokterApi INSTANCE;
    private Retrofit retrofit;

    private UserService userService;
    private ConsulService consulService;

    private Context mContext;


    private TanyaDokterApi(Context context) {
        mContext = context;

    }

    public static TanyaDokterApi getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TanyaDokterApi(context);
            INSTANCE.init();
        }
        return INSTANCE;
    }

    public void init() {
        final OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.MINUTES);
        client.setConnectTimeout(1, TimeUnit.MINUTES);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UserService getUserApi() {
        if (userService == null) {
            userService = retrofit.create(UserService.class);
        }
        return userService;
    }

    public ConsulService getConsulApi() {
        if (consulService == null) {
            consulService = retrofit.create(ConsulService.class);
        }
        return consulService;
    }

}
