package utils;
/**
 *
 * @author jappeah
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public interface GetMetadata {
	public String executeImageSearchResult(String queryparam);
	public String executeComputerVision(File image);
}
