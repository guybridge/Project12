package au.com.wsit.project12.api;


import org.junit.Test;

import au.com.wsit.project12.model.Earth;
import static org.junit.Assert.*;



/**
 * Created by guyb on 7/03/17.
 */


public class EarthApiTest
{
        @Test
        public void testEarthApi() throws Exception
        {
            EarthApi earthApi = new EarthApi();
            earthApi.getImages(10.0, 10.0, "2012-1-1", new EarthApi.EarthCallback()
            {
                @Override
                public void onResult(Earth earth)
                {
                    assertTrue("Earth data not null", earth != null);
                }

                @Override
                public void onFail(String errorMessage)
                {

                }
            });
        }
}