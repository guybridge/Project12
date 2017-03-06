package au.com.wsit.project12.api;

import org.junit.Test;

import java.util.ArrayList;

import au.com.wsit.project12.model.Rover;
import au.com.wsit.project12.utils.Constants;

import static org.junit.Assert.*;

/**
 * Created by guyb on 7/03/17.
 */
public class RoverApiTest
{
    @Test
    public void testRoverApi() throws Exception
    {
        RoverApi roverApi = new RoverApi();
        roverApi.getImages(Constants.CURIOUSITY, new RoverApi.RoverCallback()
        {
            @Override
            public void onResult(ArrayList<Rover> roverData)
            {
                assertTrue("We have rover data", roverData.size() > 0);

            }

            @Override
            public void onFail(String errorMessage)
            {

            }
        });
    }
}