package base;

import java.io.*;
import java.util.HashMap;

public class StudySet {

	private BufferedReader r;

	public StudySet(String fileName) {
		try {
			r = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getHashMap() {
		HashMap<String, String> set = new HashMap<String, String>();

		String line = "";
		try {
			line = r.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; line != null; i++) {
			String[] entry = {};
			try {
				entry = r.readLine().split(":");
			} catch (IOException e) {
				e.printStackTrace();
			}

			set.put(entry[i], entry[i + 1]);
		}

		return set;
	}
}
