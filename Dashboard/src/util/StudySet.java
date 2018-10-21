package util;

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
			for (String line = reader.readLine(); line != null && line != ""; line = reader.readLine()) {
				entry = line.split(":");
				set.add(new Term(entry[0], entry[1]));
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

	public String getDefinitionAt(int index) {
		return set.get(index).getDefinition();
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

	public String getTermAt(int index) {
		return set.get(index).getTerm();
	}

	public int size() {
		return set.size();
	}

	public ArrayList<Term> getSet() {
		return set;
	}

}
