package team.iyooo.id.doktercanggih.ui.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import team.iyooo.id.doktercanggih.R;
import team.iyooo.id.doktercanggih.apis.ConsulApi;
import team.iyooo.id.doktercanggih.utils.Constant;
import team.iyooo.id.doktercanggih.utils.DateHelper;
import team.iyooo.id.doktercanggih.utils.PrefHelper;

/**
 * Created by tikfby on 19-Mar-16.
 */
public class ConsulDetailViewModel {

    public ObservableField<String> namaPasien = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> dateConsult = new ObservableField<>();
    public ObservableField<Boolean> isAnswer = new ObservableField<>(false);
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> ask = new ObservableField<>();
    public ObservableField<String> nameDokter = new ObservableField<>();
    public ObservableField<String> answer = new ObservableField<>();

    private Context mContext;
    private ConsulApi.ApiDaoConsultDetail mData;

    public ConsulDetailViewModel(Context mContext) {
        this.mContext = mContext;
        imageUrl.set("");
    }

    public void initData(ConsulApi.ApiDaoConsultDetail item){
        mData = item;
        namaPasien.set(mData.user.nama);
        imageUrl.set(Constant.PICTUREURL+mData.user.photo);
        dateConsult.set(DateHelper.dateParseToString(mData.consult.date, DateHelper.OLDFORMAT, DateHelper.NEWFORMAT));
        if(mData.consult.is_answer.equals("1")){
            isAnswer.set(true);
            answer.set(mData.consult.answer);
        }else {
            if(PrefHelper.getLogin(mContext).user_id.equals(mData.dokter.doctor_id)) {
                isAnswer.set(false);
            }else {
                isAnswer.set(true);
            }
            answer.set("-");
        }
        title.set(mData.consult.title);
        ask.set(mData.consult.ask);
        nameDokter.set(mData.dokter.nama);
    }

    public void initAnswer(String ans, String mAnswer){
        if(mData.consult.is_answer.equals(ans)){
            isAnswer.set(true);
            answer.set(mAnswer);
        }else {
            isAnswer.set(false);
            answer.set("-");
        }
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
