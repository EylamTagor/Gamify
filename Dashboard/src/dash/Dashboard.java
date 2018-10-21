package dash;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import util.FileUtils;
import util.StudySet;
import util.Term;

// Made by Kevin Rossel for HSHacks IV

public class Dashboard {

	private JFrame frmGamify;
	private JTable table;
	private JTextField textFieldTerm;
	private JTextField textFieldDefinition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frmGamify.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGamify = new JFrame();
		frmGamify.setTitle("Dashboard");
		frmGamify.setBounds(100, 100, 450, 300);
		frmGamify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGamify.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmGamify.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Launch", null, panel, null);
		panel.setLayout(null);

		JLabel lblGame = new JLabel("Game:");
		lblGame.setBounds(12, 72, 70, 15);
		panel.add(lblGame);

		JLabel lblStudySet = new JLabel("Study Set:");
		lblStudySet.setBounds(236, 72, 115, 15);
		panel.add(lblStudySet);

		JComboBox comboBoxGames = new JComboBox();
		comboBoxGames.setBounds(12, 86, 195, 24);
		panel.add(comboBoxGames);

		JComboBox comboBoxStudySet = new JComboBox();
		comboBoxStudySet.setBounds(236, 86, 195, 24);
		panel.add(comboBoxStudySet);

		JButton btnLaunch = new JButton("Launch");
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Make sure a game and study set are chosen
				if (comboBoxGames.getSelectedItem() == null || comboBoxStudySet.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(panel, "Make sure you select a game and study set.",
							"Selection Error", JOptionPane.ERROR_MESSAGE);
				}

				// Launch a jar file with specified args
				try {
					Process proc = Runtime.getRuntime().exec("java -jar " + (String) (comboBoxGames.getSelectedItem())
							+ ".jar " + (String) (comboBoxStudySet.getSelectedItem()) + ".txt");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(panel, "Error launching game. See console for output.",
							"Game Launch Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		btnLaunch.setBounds(167, 211, 117, 25);
		panel.add(btnLaunch);

		JPanel panel_Edit = new JPanel();
		tabbedPane.addTab("Edit", null, panel_Edit, null);
		panel_Edit.setLayout(null);

		table = new JTable();
		table.setModel(new DefaultTableModel(null, new String[] { "Term/Phrase", "Definition/Translation" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(208);
		table.getColumnModel().getColumn(1).setPreferredWidth(208);
		table.setShowHorizontalLines(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(12, 76, 419, 160);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setBounds(12, 76, 419, 160);
		panel_Edit.add(scrollTable);

		JLabel lblTerm = new JLabel("Term/Phrase:");
		lblTerm.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTerm.setBounds(12, 36, 133, 15);
		panel_Edit.add(lblTerm);

		JLabel labelDefinition = new JLabel("Definition/Translation:");
		labelDefinition.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelDefinition.setBounds(12, 55, 169, 15);
		panel_Edit.add(labelDefinition);

		textFieldTerm = new JTextField();
		textFieldTerm.setBounds(177, 34, 180, 19);
		panel_Edit.add(textFieldTerm);
		textFieldTerm.setColumns(10);

		textFieldDefinition = new JTextField();
		textFieldDefinition.setColumns(10);
		textFieldDefinition.setBounds(177, 53, 180, 19);
		panel_Edit.add(textFieldDefinition);

		JButton btnSave = new JButton("Save As...");
		btnSave.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Prompt for study set name
				String fileName = (String) JOptionPane.showInputDialog(panel, "Enter Study Set Name:",
						"Choose Study Set", JOptionPane.PLAIN_MESSAGE, null, null, "");

				if (fileName.equals(null) || fileName.equals("")) {
					JOptionPane.showMessageDialog(panel, "ERROR: Must insert a study set name.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
				}

				if (!fileName.endsWith(".txt")) {
					fileName += ".txt";
				}

				// Create an array list with all of the rows
				ArrayList<String> formattedRows = new ArrayList<String>();

				for (int i = 0; i < table.getRowCount(); i++) {
					formattedRows.add(table.getModel().getValueAt(i, 0) + ":" + table.getModel().getValueAt(i, 1));
				}

				// Save to a text file

				// Create a print writer object
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(fileName, "UTF-8");

					for (String row : formattedRows) {
						writer.println(row);
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}

				writer.close();

				// Update the list of Study Sets

				// Reset the combo box for study sets
				comboBoxStudySet.removeAll();
				
				// Get current directory
				File path = new File(System.getProperty("user.dir"));
				System.out.println("Path: " + path.getPath());

				// Get txt file in directory
				File[] txtFiles = FileUtils.filesEndingWith("txt", path);
				
				// Add the study set names
				for (File studySet : txtFiles) {
					comboBoxStudySet.addItem(studySet.getName().replace(".txt", ""));
				}
			}
		});
		btnSave.setBounds(341, 6, 90, 20);
		panel_Edit.add(btnSave);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// === Get the rows that already exist ===

				// We add 1 to the rows because we are going to make a new one
				Object[][] currentRows = new Object[table.getRowCount() + 1][2];
				for (int i = 0; i < table.getRowCount(); i++) {
					currentRows[i][0] = table.getValueAt(i, 0);
					currentRows[i][1] = table.getValueAt(i, 1);
				}

				// Add the new rows
				currentRows[currentRows.length - 1][0] = textFieldTerm.getText();
				currentRows[currentRows.length - 1][1] = textFieldDefinition.getText();

				table.setModel(
						new DefaultTableModel(currentRows, new String[] { "Term/Phrase", "Definition/Translation" }) {
							Class[] columnTypes = new Class[] { String.class, String.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
				table.getColumnModel().getColumn(0).setPreferredWidth(208);
				table.getColumnModel().getColumn(1).setPreferredWidth(208);

				// Clear the text fields
				textFieldTerm.setText("");
				textFieldDefinition.setText("");
			}
		});
		btnAdd.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAdd.setBounds(361, 36, 70, 34);
		panel_Edit.add(btnAdd);

		JButton buttonLoad = new JButton("Load");
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a file prompt
				File file;
				JFileChooser fChooser = new JFileChooser();
				int returnVal = fChooser.showOpenDialog(panel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fChooser.getSelectedFile();
					System.out.println("Chose file: " + file.getPath() + "    Name: " + file.getName());

					// Read the file
					ArrayList<Term> terms = new StudySet(file.getName()).getSet();

					// Modify the table
					Object[][] rows = new Object[terms.size()][2];
					for (int i = 0; i < terms.size(); i++) {
						rows[i][0] = terms.get(i).getTerm();
						rows[i][1] = terms.get(i).getDefinition();
					}

					table.setModel(
							new DefaultTableModel(rows, new String[] { "Term/Phrase", "Definition/Translation" }) {
								Class[] columnTypes = new Class[] { String.class, String.class };

								public Class getColumnClass(int columnIndex) {
									return columnTypes[columnIndex];
								}
							});
					table.getColumnModel().getColumn(0).setPreferredWidth(208);
					table.getColumnModel().getColumn(1).setPreferredWidth(208);
				}
			}
		});
		buttonLoad.setFont(new Font("Dialog", Font.PLAIN, 10));
		buttonLoad.setBounds(12, 6, 90, 20);
		panel_Edit.add(buttonLoad);

		/**
		 * Our super epic code
		 */
		// Get current directory
		File path = new File(System.getProperty("user.dir"));
		System.out.println("Path: " + path.getPath());

		// Go through all of the files and get ones ending in jar
		File[] jarFiles = FileUtils.filesEndingWith("jar", path);

		// Go through all of the file and get ones ending in txt
		File[] txtFiles = FileUtils.filesEndingWith("txt", path);

		// Add the game names
		for (File game : jarFiles) {
			// Make sure the jar file isn't this program
			if (!game.getName().equals("Dashboard.jar")) {
				comboBoxGames.addItem(game.getName().replaceAll(".jar", ""));
			}
		}

		// Add the study set names
		for (File studySet : txtFiles) {
			comboBoxStudySet.addItem(studySet.getName().replace(".txt", ""));
		}
	}
}
