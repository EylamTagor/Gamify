package util;

import java.io.File;
import java.io.FilenameFilter;

public class FileUtils {
	public static File[] filesEndingWith(String ending, File directory) {
		File[] files = directory.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(ending);
		    }
		});
		
		return files;
	}
}
