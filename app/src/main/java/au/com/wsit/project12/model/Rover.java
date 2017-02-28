package au.com.wsit.project12.model;

import java.util.ArrayList;

import au.com.wsit.project12.utils.Constants;

/**
 * Created by guyb on 28/02/17.
 */

public class Rover
{
    private String roverName;
    private String cameraName;
    private String photoDate;
    private String imageUrl;
    private int sol;
    private ArrayList<String> curiosityCameras;
    private ArrayList<String> opportunityCameras;
    private ArrayList<String> spiritCameras;

    public ArrayList<String> getCuriosityCameras()
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
        return cameras;
    }

    public ArrayList<String> getOpportunityCameras()
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
        return opportunityCameras;
    }

    public ArrayList<String> getSpiritCameras()
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
        return spiritCameras;
    }



    public String getRoverName()
    {
        return roverName;
    }

    public void setRoverName(String roverName)
    {
        this.roverName = roverName;
    }

    public String getCameraName()
    {
        return cameraName;
    }

    public void setCameraName(String cameraName)
    {
        this.cameraName = cameraName;
    }

    public String getPhotoDate()
    {
        return photoDate;
    }

    public void setPhotoDate(String photoDate)
    {
        this.photoDate = photoDate;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public int getSol()
    {
        return sol;
    }

    public void setSol(int sol)
    {
        this.sol = sol;
    }
}
