package au.com.wsit.project12.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import au.com.wsit.project12.model.Rover;
import au.com.wsit.project12.utils.Constants;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Parses the data from the NASA rover endpoint
 */

public class RoverHelper
{



    public static final String TAG = RoverHelper.class.getSimpleName();

    public static ArrayList<Rover> parseRoverData(retrofit2.Response<ResponseBody> response)
    {
        try
        {
            ArrayList<Rover> roverArrayList = new ArrayList<>();

            JSONObject jsonData = new JSONObject(response.body().string());
            JSONArray photos = jsonData.getJSONArray(Constants.PHOTOS);

            for(int i = 0; i < photos.length(); i++)
            {
                Rover roverItem = new Rover();
                JSONObject roverInstance = photos.getJSONObject(i);

                int sol = roverInstance.getInt(Constants.SOL);
                JSONObject camera = roverInstance.getJSONObject(Constants.CAMERA);
                String cameraName = camera.getString(Constants.FULL_NAME);
                String img_src = roverInstance.getString(Constants.IMG_SRC);
                String earth_date = roverInstance.getString(Constants.EARTH_DATE);

                JSONObject rover = roverInstance.getJSONObject(Constants.ROVER);
                String roverName = rover.getString(Constants.NAME);

                roverItem.setCameraName(cameraName);
                roverItem.setImageUrl(img_src);
                roverItem.setPhotoDate(earth_date);
                roverItem.setSol(sol);
                roverItem.setRoverName(roverName);

                roverArrayList.add(roverItem);

            }

            return roverArrayList;

        }
        catch (NullPointerException | IOException | JSONException e)
        {
            Log.i(TAG, "Error parsing JSON data: " + e.getMessage());
            return null;
        }
    }
}
