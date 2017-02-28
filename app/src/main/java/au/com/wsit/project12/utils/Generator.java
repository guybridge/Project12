package au.com.wsit.project12.utils;

import android.provider.Settings;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for generating random numbers and camera types
 */

public class Generator
{
    public static String getRandomSol()
    {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000));
    }

    public static String getRandomCamera()
    {
        ArrayList<String> cameras = new ArrayList<>();
        cameras.add(Constants.FHAZ);
        cameras.add(Constants.RHAZ);
        cameras.add(Constants.MAST);
        cameras.add(Constants.CHEMCAM);
        cameras.add(Constants.MAHLI);
        cameras.add(Constants.MARDI);
        cameras.add(Constants.NAVCAM);
        cameras.add(Constants.PANCAM);
        cameras.add(Constants.MINITES);

        Random random = new Random();
        int randomValue = random.nextInt(cameras.size());

        return cameras.get(randomValue);

    }
}
