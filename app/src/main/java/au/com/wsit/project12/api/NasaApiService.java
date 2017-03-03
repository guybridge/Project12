package au.com.wsit.project12.api;

import au.com.wsit.project12.utils.Constants;
import okhttp3.Response;
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
    String NASA_ROVER_BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/";
    String NASA_EARTH_BASE_URL = "https://api.nasa.gov/planetary/earth/";

    // Example
    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY

    // Rover API
    @GET("{rovername}/photos?api_key=" + Constants.NASA_API_KEY)
    Call<ResponseBody> getRoverImages(
            @Path("rovername") String rovername,
            @Query("sol") String sol);

    // Earth API
    //https://api.nasa.gov/planetary/earth/imagery?lon=100.75&lat=1.5&date=2014-02-01&cloud_score=True&api_key=DEMO_KEY
    @GET("imagery?api_key=" + Constants.NASA_API_KEY)
    Call<ResponseBody> getEarthImages(@Query("lat") String latitude,
                                      @Query("lon") String longitude,
                                      @Query("date") String date);

}

