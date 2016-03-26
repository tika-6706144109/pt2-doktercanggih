package team.iyooo.id.doktercanggih;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.SimpleLineIconsModule;

/**
 * Created by tikafby on 2/14/2016.
 */
public class IYOOApp extends Application {
    public static Gson GSON;
    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule());
        Iconify.with(new MaterialModule());
        Iconify.with(new SimpleLineIconsModule());
        GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
