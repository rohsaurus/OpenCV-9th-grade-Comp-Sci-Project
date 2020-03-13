/*import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.HighGui;
import org.opencv.core.Rect;



public class Main {

    static int width;
    static int height;
    static double alpha = 1;
    static double beta = 50;

    public static void main(String[] args) {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );



        //load image into a mat called "I"
        try{
            //putting the image into greyscale

            // For proper execution of native libraries
            // Core.NATIVE_LIBRARY_NAME must be loaded before
            // calling any of the opencv methods
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

            // input image as grayscale.
            Mat source = Imgcodecs.imread("C:\\Users\\mpari\\Downloads\\IMG_1383.jpg", Imgcodecs.IMREAD_GRAYSCALE );
            // create and save the grayscale file NOTE Java has no UNSIGNED type.  Don't use it
            Mat destination = new Mat(source.rows(), source.cols(), source.type());
            source.convertTo(destination, CvType.CV_8S);
            Imgcodecs.imwrite("C:\\Users\\mpari\\Downloads\\IMG_1383GRAYSCALEGRAY.jpg", destination);
            //I created a 640 by 480 pixal image It should show up as such
            System.out.println("rows = " + destination.rows());
            System.out.println("columns = " + destination.cols());
            int channels = destination.channels();
            int totals = (int) destination.total();
            //should be 1 for black and white.  3 for color
            System.out.printf("Number of channels = %d\n", channels);
            //create an array to hold data for every pixel in the picture
            byte[] imgData = new byte[(int) (destination.total() * destination.channels())];
            //what data to store
            destination.get(0, 0, imgData);

            int addCol = 0;
            int addRow = 0;
            int highestCol = 0;
            int highestRow = 0;

            int highestX = 0;
            int highestY = 0;

            System.out.println("Running...");

            for(int i=0;i<destination.rows();i++){

                byte intensityRows = imgData[i * destination.rows() + i];
                addRow += intensityRows;

                for(int j=0;j<destination.cols();j++){
                    //get the pixel info
                    byte intensity = imgData[i * destination.cols() + j];

                    addCol += intensity;

                    if(addCol > highestCol) {
                        highestCol = addCol;
                        highestX = j;
                    }
                }

                addCol = 0;

                if(addRow > highestRow) {
                    highestRow = addRow;
                    highestY = i;
                }
            }

            //i just need to re crop the image around these two x, y coordinates and in theory, it should work.
            System.out.println(highestX);
            System.out.println(highestY);

            Rect rectCrop = new Rect(highestX - 3000, highestY - 2500, 1800, 1800);
            Mat image_roi = new Mat(destination, rectCrop); //put the original image into the recropped rectangle

            Imgcodecs.imwrite("C:\\Users\\mpari\\Downloads\\IMG_1383Cropped.jpg", image_roi);

        }
        catch(Exception e) {
            System.out.println("Error!");
            System.out.println(e.toString());
        }
    }

}

 */
