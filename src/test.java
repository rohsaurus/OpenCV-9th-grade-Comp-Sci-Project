import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

public class test {
    public static int largest( int[] array ){ //finds the index of the largest value in an array
        if ( array == null || array.length == 0 ) {
            return -1;
        }

        int largest = 0;
        for ( int i = 0; i < array.length; i++ ) {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest;
    }
    public static void main(String[] args) {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); //load the necessary library for opencv
        for (int i = 1; i <= 374; i++) {
            Mat source = Imgcodecs.imread("C:\\Users\\mpari\\Documents\\coding projects\\Java\\OpenCV Pictures\\IMG_1383.jpg", Imgcodecs.IMREAD_GRAYSCALE ); //(" + i +")   THIS IS FOR THE FINAL IMAGE NAME when using multiple images loads in the image as grayscale - the i will change with each loop of the program, automatically running for each image
            Mat destination = new Mat(source.rows(), source.cols(), source.type());
            source.convertTo(destination, CvType.CV_8S);
            Imgcodecs.imwrite("C:\\Users\\mpari\\Documents\\coding projects\\Java\\OpenCV Pictures\\IMG_1383GRAYSCALE.jpg", destination); //write the image as grayscale to a new file
            System.out.println("rows = " + destination.rows()); //print out amount of rows in the image
            System.out.println("columns = " + destination.cols()); //and the amount of columns
            int channels = destination.channels();
            System.out.printf("Number of channels = %d\n", channels);
            byte[] imgData = new byte[(int) (destination.total() * destination.channels())];
            destination.get(0, 0, imgData);
            int[] intenseArray = new int[destination.rows()]; //this array tracks the intensity of color in each column, with each column having an index.
            int[] intenseArray2 = new int[destination.cols()]; //this one tracks it in each row
            for(int a=0;a<destination.rows();a++){
                for(int j=0;j<destination.cols();j++){
                    byte intensity = imgData[a * destination.cols() + j];
                    //System.out.printf("%d %d %d \n",i,j,intensity ); this prints out the intensity of EACH row and column, but significantly slows the program down, so its commented out for now
                    intenseArray[a] = intenseArray[a] + intensity; //the intensity is added to each columns index, so that you can later see which index had the highest intensity
                    intenseArray2[j] = intenseArray2[j] + intensity; //and same with the rows
                }
            }
            System.out.println(largest(intenseArray)); //height
            System.out.println(largest(intenseArray2)); //rows
            int leftx = largest(intenseArray2) - 531; //to get bottom left corner of where to crop the image, 531 is about how far away from the centerpoint each one is on X and Y (negative and positive)
            int downy = largest(intenseArray) - 531;
            Rect rectCrop = new Rect(leftx, downy, 1050, 1050); //crops the image based on the lower left corner of the image, going 1050 pixels right and 1050 pixels up to generate a square image
            Mat last = Imgcodecs.imread("C:\\Users\\mpari\\Documents\\coding projects\\Java\\OpenCV Pictures\\IMG_1383.jpg"); //Loads in the original image to crop
            Mat image_output  = last.submat(rectCrop); //applies to image
            Imgcodecs.imwrite("C:\\Users\\mpari\\Downloads\\IMG1383ISTundCROPPED.jpg", image_output); //writes the final cropped image
            Mat croppedimage = Imgcodecs.imread("C:\\Users\\mpari\\Downloads\\IMG1383ISTundCROPPED.jpg", Imgcodecs.IMREAD_GRAYSCALE );
            Mat croppedgrayscale = new Mat(croppedimage.rows(), croppedimage.cols(), croppedimage.type());
            croppedimage.convertTo(destination, CvType.CV_8S);
            Imgcodecs.imwrite("C:\\Users\\mpari\\Documents\\coding projects\\Java\\OpenCV Pictures\\IMG_1383CROPPEdGRAYSCALE.jpg", croppedgrayscale);
        }
    }
}