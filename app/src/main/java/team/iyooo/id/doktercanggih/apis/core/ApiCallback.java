package team.iyooo.id.doktercanggih.apis.core;

/**
 * Created by tikafby on 2/28/16.
 */
public interface ApiCallback<T> {
    public void onApiSuccess(T result, String rawJson);
    public void onApiError(String errorMessage);
}
