package au.com.wsit.project12.api;

import au.com.wsit.project12.utils.Constants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by guyb on 28/02/17.
 */

public interface NasaApiService
{
    String NASA_BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";

    // Example
    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY

    @GET("{rovername}/photos?api_key=" + Constants.NASA_API_KEY)
    Call<ResponseBody> getImages(
            @Path("rovername") String rovername,
            @Query("sol") String sol);

}

