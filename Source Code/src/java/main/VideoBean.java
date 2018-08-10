
package main;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



/**
 *
 * @author Chris Hyde
 * @date August 7, 2018
 * @class CST-235
 * @instructor Dr. Toure
 * 
 * @about: This JavaBean class was created to support the Simple Video Size 
 *         Calculator project and does all the calculations based on user inputs
 */
@Named
@SessionScoped
public class VideoBean implements Serializable {
    //Variables
    private Integer videoLength = null;
    private String result = null;
    private boolean quality = false;
    private int minimum = 0;
    private int maximum = 36000; //10 hours max video length. 
    
    /**
     * Basic Constructor
     */
    public VideoBean(){
    }
    
    /**
     * Method that returns a result of the size calculation (for error handling)
     * @return 
     */
    public String getResult(){
        this.result = calculateVideoSize();
        if(result == null){
            return "Sorry an error has occured while calculating your video size! /nPlease try again.";
        }else{
            return result;
        }
    
    }
    
    /**
     * Method to return the Calculated size of the video.
     * Video Size Formula:(fps * lengthSeconds) * ((width * height * bitdepth) / (8 * 1024)) = size in kilobytes. 
     * @return String
     */
    public String calculateVideoSize(){
        int numFrames = 30 * videoLength; // Get the number of frames in the video
        int frameSize;
        String resolution;
        
        //Get the size of frames based off of what video quality was chosen
        if(quality){
            frameSize = (int)Math.ceil((1920 * 1080 * 8) / (8 * 1024)); 
            resolution = "1080p";
        }else{
            frameSize = (int)Math.ceil((1280 * 720 * 8) / (8 * 1024));
            resolution = "720p";
        }
        
        //Set the videoSizeMB
        int videoSizeMB = numFrames * frameSize / 1024;
        
        //Set the fileSizeGB
        double videoSizeGB = (double)videoSizeMB / 1024;
        
        //return the results of the calculation in a string
        return String.format("The size of your %d second long %s video will take up: %,d MB / %.2f GB!", videoLength, resolution, videoSizeMB, videoSizeGB);
        
    }
    
    /**
     * Getter Method for videoLength
     * @return Integer
     */
    public Integer getVideoLength(){
        return this.videoLength;
    }
    
    /**
     * Setter Method for videoLength
     * @param videoLength Integer
     */
    public void setVideoLength(Integer videoLength){
        this.videoLength = videoLength;
    }
    
    /**
     * Getter Method for isQuality
     * @return boolean
     */
    public boolean isQuality(){
        return this.quality;
    }
    
    /**
     * Setter Method for isQuality
     * @param videoQuality boolean
     */
    public void setQuality(boolean videoQuality){
        this.quality = videoQuality;
    }
    
    /**
     * Getter Method for minimum video length
     * @return int
     */
    public int getMinimum(){
        return this.minimum;
    }
    
    /**
     * Getter Method for maximum video length
     * @return int
     */
    public int getMaximum(){
        return this.maximum;
    }
}
