package au.com.wsit.project12.api;

import android.util.Log;

import java.io.IOException;

import au.com.wsit.project12.json.EarthHelper;
import au.com.wsit.project12.model.Earth;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by guyb on 28/02/17.
 */

public class EarthApi
{
    private static final String TAG = EarthApi.class.getSimpleName();

    public interface EarthCallback
    {
        void onResult(Earth earth);
        void onFail(String errorMessage);
    }

    public void getImages(double latitude, double longitude, String date, final EarthCallback callback)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NasaApiService.NASA_EARTH_BASE_URL)
                .build();


        NasaApiService service = retrofit.create(NasaApiService.class);
        Call<ResponseBody> call = service.getEarthImages(String.valueOf(latitude), String.valueOf(longitude), date);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                Earth earth = EarthHelper.parseEarthData(response);
                if(earth != null)
                {
                    callback.onResult(earth);
                }
                else
                {
                    callback.onFail("Error parsing JSON data");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                callback.onFail(t.getMessage());
            }
        });
    }
}
