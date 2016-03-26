package team.iyooo.id.doktercanggih.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import team.iyooo.id.doktercanggih.IYOOApp;
import team.iyooo.id.doktercanggih.apis.UserApi;

/**
 * Created by tikafby on 2/28/2016.
 */
public class PrefHelper {

    public final static String LOGIN = "login";

    private static SharedPreferences getSP(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void savePref(Context context, String key, String value) {
        getSP(context).edit().putString(key, value).commit();
    }

    public static String getPref(Context context, String key) {
        return getSP(context).getString(key, null);
    }

    public static UserApi.ApiDaoUser getLogin(Context ctx) {
        return IYOOApp.GSON.fromJson(getPref(ctx, LOGIN), UserApi.ApiDaoUser.class);
    }

    public static void saveLogin(Context ctx, UserApi.ApiDaoUser mData) {
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(ctx);
        pref.edit().putString(LOGIN, IYOOApp.GSON.toJson(mData)).commit();
    }
}
