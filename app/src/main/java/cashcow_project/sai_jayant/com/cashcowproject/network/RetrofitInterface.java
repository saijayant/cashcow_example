package cashcow_project.sai_jayant.com.cashcowproject.network;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by macbookpro on 01/11/17.
 */
public interface RetrofitInterface {

    @GET("testing_1.php")
    Call<JsonElement> getTestOne();

    @GET("testing_2.php")
    Call<JsonElement> getTestTwo();
}



