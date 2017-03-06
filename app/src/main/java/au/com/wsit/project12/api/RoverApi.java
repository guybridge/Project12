package au.com.wsit.project12.api;

import android.util.Log;

import java.util.ArrayList;

import au.com.wsit.project12.json.RoverHelper;
import au.com.wsit.project12.model.Rover;
import au.com.wsit.project12.utils.Generator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by guyb on 28/02/17.
 */

public class RoverApi
{
    public static final String TAG = RoverApi.class.getSimpleName();
    private ArrayList<Rover> roverImages = new ArrayList<>();


    public interface RoverCallback
    {
        void onResult(ArrayList<Rover> roverData);
        void onFail(String errorMessage);
    }

    public void getImages(final String roverName, final RoverCallback callback)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NasaApiService.NASA_ROVER_BASE_URL)
                .build();

        final String sol = Generator.getRandomSol();

        NasaApiService service = retrofit.create(NasaApiService.class);
        Call<ResponseBody> call = service.getRoverImages(roverName, sol);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {

                ArrayList<Rover> roverImages = RoverHelper.parseRoverData(response);
                if(roverImages != null)
                {
                    callback.onResult(roverImages);
                }
                else
                {
                    callback.onFail("No photos for sol: " + sol);
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
