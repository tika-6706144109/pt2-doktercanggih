package team.iyooo.id.doktercanggih.ui.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.apis.ConsulApi;
import team.iyooo.id.doktercanggih.utils.DateHelper;

/**
 * Created by tikafby on 05-Mar-16.
 */
public class ConsulRowViewModel {
    public final ObservableField<String> pasien = new ObservableField<>();
    public final ObservableField<String> doctor = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> ask = new ObservableField<>();
    public final ObservableField<String> date = new ObservableField<>();
//    public final ObservableField<String> imageUrl = new ObservableField<>();

    private Context mContext;
    private ConsulApi.ApiDaoConsult mDataInfo;

    public ConsulRowViewModel(Context mContext, ConsulApi.ApiDaoConsult dataInfo) {
        this.mContext = mContext;
        mDataInfo = dataInfo;

        pasien.set("Oleh: "+mDataInfo.pasien);
        doctor.set(mDataInfo.doctor);
        date.set(DateHelper.dateParseToString(mDataInfo.date, DateHelper.OLDFORMAT, DateHelper.NEWFORMAT));
        title.set(mDataInfo.title);
        ask.set(mDataInfo.ask);
//        imageUrl.set(Constant.PICTUREURL+mDataInfo.photo);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(!imageUrl.isEmpty() || !imageUrl.equals("")) {
            Picasso.with(view.getContext())
                    .load(imageUrl)
                    .error(R.color.grey_background)
                    .into(view);
        }else{
            Picasso.with(view.getContext())
                    .load(R.color.grey_background)
                    .error(R.color.grey_background)
                    .into(view);
        }
    }
}
