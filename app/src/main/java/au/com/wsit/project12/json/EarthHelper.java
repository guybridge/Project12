package au.com.wsit.project12.json;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import au.com.wsit.project12.model.Earth;
import au.com.wsit.project12.utils.Constants;
import okhttp3.ResponseBody;

/**
 * Created by guyb on 2/03/17.
 */

public class EarthHelper
{
    private static final String TAG = EarthHelper.class.getSimpleName();

    public static Earth parseEarthData(retrofit2.Response<ResponseBody> response)
    {
        try
        {
            Earth earth = new Earth();
            JSONObject jsonData = new JSONObject(response.body().string());
            String date = jsonData.getString(Constants.DATE);
            String url = jsonData.getString(Constants.URL);

            earth.setDate(date);
            earth.setImageUrl(url);

            return earth;
        }
        catch (IOException | NullPointerException | JSONException e)
        {
            Log.i(TAG, "Error parsing json data: " + e.getMessage());
            return null;
        }
    }
}
