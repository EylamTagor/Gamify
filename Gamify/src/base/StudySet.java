package base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudySet {

	private ArrayList<Term> set;
	private BufferedReader reader;

	public StudySet(String fileName) {
		set = new ArrayList<Term>();
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String[] entry = {};
		try {
			while (reader.readLine() != null) {
				try {
					entry = reader.readLine().split(":");
					set.add(new Term(entry[0], entry[1]));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDefinitionAt(String term) {
		String definition = "";

		int index = 0;
		while (index < set.size()) {
			if (set.get(index).getTerm() == term)
				definition = set.get(index).getDefinition();
			index++;
		}

		return definition;
	}

	public String getTermAt(String definition) {
		String term = "";

		int index = 0;
		while (index < set.size()) {
			if (set.get(index).getDefinition() == definition)
				term = set.get(index).getTerm();
			index++;
		}

		return term;
	}

}
