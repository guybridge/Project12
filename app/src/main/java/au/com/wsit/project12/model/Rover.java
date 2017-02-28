package au.com.wsit.project12.model;

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
