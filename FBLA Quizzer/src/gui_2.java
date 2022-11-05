import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.*;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Choice;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;

public class gui_2 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textFieldFillBlank;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					try {
					    UIManager.setLookAndFeel( new FlatLightLaf() );
					    FlatArcIJTheme.install();
					    //UIManager.put("TextComponent.arc",  10);
					    //UIManager.put("TextArea.arc", 100);
					} catch( Exception ex ) {
					    System.err.println( "Failed to initialize LaF" );
					}
					
					gui_2 window = new gui_2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public gui_2() throws FileNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() throws FileNotFoundException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE
				);
		frame.getContentPane().setForeground(Color.BLACK);
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\rhanson\\Documents\\Projects\\pixLab\\beach.jpg"));
		frame.setBounds(100, 100, 1167, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JOptionPane.showMessageDialog(frame, "Please select your question.");
		
		
		/*JFileChooser chooser = new JFileChooser("C:\\Users\\dakot\\Desktop"); // PROVIDING QUESTION BANK TO USE
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Select Question Bank...");
		
		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open: " + chooser.getSelectedFile().getName());
		}*/
		
		JLabel lblFillBlankAnswer = new JLabel("Answer:");
		lblFillBlankAnswer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFillBlankAnswer.setVisible(false);
		
		textFieldFillBlank = new JTextField();
		textFieldFillBlank.setColumns(10);
		textFieldFillBlank.setVisible(false);
		
		//Multiple Choice Buttons
		JRadioButton rdbtnAnswer1 = new JRadioButton("");
		rdbtnAnswer1.setVisible(false);
		
		JRadioButton rdbtnAnswer2 = new JRadioButton("");
		rdbtnAnswer2.setVisible(false);
		
		JRadioButton rdbtnAnswer3 = new JRadioButton("");
		rdbtnAnswer3.setVisible(false);
		
		JRadioButton rdbtnAnswer4 = new JRadioButton("");
		rdbtnAnswer4.setVisible(false);
		
		ButtonGroup multiChoiceAnswerGroup = new ButtonGroup();
		multiChoiceAnswerGroup.add(rdbtnAnswer1);
		multiChoiceAnswerGroup.add(rdbtnAnswer2);
		multiChoiceAnswerGroup.add(rdbtnAnswer3);
		multiChoiceAnswerGroup.add(rdbtnAnswer4);
		
		//Matching's Buttons and Text Fields
		JComboBox comboBoxAnswer1 = new JComboBox();
		comboBoxAnswer1.setModel(new DefaultComboBoxModel(new String[] {"-", "a", "b", "c"}));
		comboBoxAnswer1.setVisible(false);
		
		JComboBox comboBoxAnswer2 = new JComboBox();
		comboBoxAnswer2.setModel(new DefaultComboBoxModel(new String[] {"-", "a", "b", "c"}));
		comboBoxAnswer2.setVisible(false);
		
		JComboBox comboBoxAnswer3 = new JComboBox();
		comboBoxAnswer3.setModel(new DefaultComboBoxModel(new String[] {"-", "a", "b", "c"}));
		comboBoxAnswer3.setVisible(false);
		
		JLabel lblA = new JLabel("a.");
		lblA.setVisible(false);
		
		JLabel lblB = new JLabel("b.");
		lblB.setVisible(false);
		
		JLabel lblC = new JLabel("c.");
		lblC.setVisible(false);
		
		//Menu Bar and Items
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(SystemColor.scrollbar);
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);
		
		Scanner frQuestions = new Scanner(new File("files/questionBank.txt"));
		//Scanner frQuestions = new Scanner(chooser.getSelectedFile());
		String questionBank[] = new String[51];
		int count = -1;
		while (frQuestions.hasNextLine()) {
			count++;
			questionBank[count] = frQuestions.nextLine();
		}
		
		Scanner frAnswers = new Scanner(new File("files/answerBank.txt"));
		String answerBank[] = new String[51];
		count = -1;
		while (frAnswers.hasNextLine()) {
			count++; 
			answerBank[count] = frAnswers.nextLine();
		}
		
		//Choosing Q&As and setting up QAndA class
		QAndA testClass = new QAndA(questionBank, answerBank);
		testClass.chooseQuestions();
		testClass.chooseAnswers();
		
		String multiChoiceQuestions[] = new String[5];
		for (int a = 0; a < multiChoiceQuestions.length; a++) {
			multiChoiceQuestions[a] = testClass.showQuestion(a);
		}
		
		String multiChoiceAnswers[] = new String[5];
		for (int a = 0; a < multiChoiceAnswers.length; a++) {
			multiChoiceAnswers[a] = testClass.showAnswer(a);
		}
		
		rdbtnAnswer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAnswer1.getText().equals("True")) {
					testClass.storeAnswer("true", numbers.currentQuestion);
				} else {
				testClass.storeAnswer(rdbtnAnswer1.getText(), numbers.currentQuestion);
				//textAreaTest3.setText(testClass.testCheckAnswer(numbers.currentQuestion));
				}
			}
		});
		
		rdbtnAnswer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAnswer2.getText().equals("False") ) {
					testClass.storeAnswer("false",  numbers.currentQuestion);
				} else {
				testClass.storeAnswer(rdbtnAnswer2.getText(), numbers.currentQuestion);
				//textAreaTest3.setText(testClass.testCheckAnswer(numbers.currentQuestion));
				}
			}
		});
		
		rdbtnAnswer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testClass.storeAnswer(rdbtnAnswer3.getText(), numbers.currentQuestion);
				//textAreaTest3.setText(testClass.testCheckAnswer(numbers.currentQuestion));
			}
		});
		
		rdbtnAnswer4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testClass.storeAnswer(rdbtnAnswer4.getText(), numbers.currentQuestion);
				//textAreaTest3.setText(testClass.testCheckAnswer(numbers.currentQuestion));
			}
		});
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(38, 121, 219));
				
				JTextArea textAreaMatching = new JTextArea();
				textAreaMatching.setBackground(Color.WHITE);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				
		
				GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1151, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnAnswer1)
												.addComponent(rdbtnAnswer2))
											.addGap(37)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(comboBoxAnswer3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(lblC))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(comboBoxAnswer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(lblB))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(comboBoxAnswer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(lblA))))
										.addComponent(rdbtnAnswer3)
										.addComponent(rdbtnAnswer4))
									.addGap(51)
									.addComponent(textAreaMatching, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFillBlankAnswer)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldFillBlank, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(564, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(742, Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFillBlankAnswer)
										.addComponent(textFieldFillBlank, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
									.addGap(86)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(rdbtnAnswer1)
											.addGap(18)
											.addComponent(rdbtnAnswer2)
											.addGap(18)
											.addComponent(rdbtnAnswer3)
											.addGap(18)
											.addComponent(rdbtnAnswer4))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBoxAnswer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblA))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBoxAnswer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblB))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBoxAnswer3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblC)))
										.addComponent(textAreaMatching, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
									.addGap(255))
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)))
				);
				panel_1.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setIcon(new ImageIcon(gui_2.class.getResource("/images/FBLA one inch wide.png")));
				lblNewLabel.setBounds(53, 0, 308, 254);
				panel_1.add(lblNewLabel);
				
				JLabel lblQuestion = new JLabel("Question :");
				lblQuestion.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblQuestion.setForeground(Color.WHITE);
				
				JScrollPane scrpQuestion = new JScrollPane();
				
				
				JTextArea textAreaQuestion = new JTextArea();
				textAreaQuestion.setBackground(Color.WHITE);
				textAreaQuestion.setEditable(false);
				textAreaQuestion.setWrapStyleWord(true);
				textAreaQuestion.setLineWrap(true);
				textAreaQuestion.setColumns(15);
				
				JButton btnProgressor = new JButton("Confirm Answer");
				btnProgressor.setVisible(false);
				btnProgressor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (numbers.currentQuestion < 4) {
							numbers.currentQuestion++;
						} else if (numbers.currentQuestion == 0) {
						}
						
						if (numbers.currentQuestionNumber == 5) {
							int test = JOptionPane.showConfirmDialog(frame, "You scored: " + testClass.checkAnswer() + "/" + numbers.finalDenominator + ". Print results?");
							
							if (test == 0) {
								try {
									DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" MM-dd-yyyy HH mm ss a");
									LocalDateTime now = LocalDateTime.now();
									String fileName = "Results" + dtf.format(now) + ".txt";
									FileWriter fw = new FileWriter(fileName);
									//FileWriter fw = new FileWriter("Results.txt");
									PrintWriter writer = new PrintWriter(fw);
									
									writer.print("Score: " + testClass.checkAnswer() + "/" + numbers.finalDenominator);
									writer.print("\nYou answered: \n" + testClass.printUserAnswers() + "\n\n" + "Correct Answers:\n" + testClass.printCorrectAnswers() + "\n\nGood Job!");
									
									writer.close();
									fw.close();
									
									JOptionPane.showMessageDialog(frame, "Results have been printed to the program's directory folder.");
									System.exit(0);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								System.exit(0);
							}
						} else {
							textAreaQuestion.setText(testClass.showQuestion(numbers.currentQuestion));
							
							textAreaMatching.setVisible(false);
							lblA.setVisible(false);
							lblB.setVisible(false);
							lblC.setVisible(false);
							comboBoxAnswer1.setVisible(false);
							comboBoxAnswer2.setVisible(false);
							comboBoxAnswer3.setVisible(false);
							
							
							if (testClass.showAnswer(numbers.currentQuestion).equals("true") || testClass.showAnswer(numbers.currentQuestion).equals("false")) { //TRUE OR FALSE HANDLING
								
								if (numbers.fillBlankCheck == 1) { //GRABBING ANSWERS FROM FILL-IN-THE-BLANK
									
									if (textFieldFillBlank.getText().equals(null)) {
										testClass.storeAnswer("",  numbers.currentQuestion - 1);
									} else {
										testClass.storeAnswer(textFieldFillBlank.getText().toLowerCase(), numbers.currentQuestion - 1);
									}
									
									textFieldFillBlank.setVisible(false);
									lblFillBlankAnswer.setVisible(false);
									rdbtnAnswer1.setVisible(true);
									rdbtnAnswer2.setVisible(true);
									rdbtnAnswer3.setVisible(true);
									rdbtnAnswer4.setVisible(true);
									multiChoiceAnswerGroup.clearSelection();
									
									numbers.fillBlankCheck = 0;
									
								}
								
								rdbtnAnswer1.setText("True");
								rdbtnAnswer2.setText("False");
								rdbtnAnswer3.setVisible(false);
								rdbtnAnswer4.setVisible(false);
								textFieldFillBlank.setVisible(false);
								multiChoiceAnswerGroup.clearSelection();
								
								numbers.currentQuestionNumber++;
								lblQuestion.setText("Question " + (numbers.currentQuestionNumber) + ":");
								
							} else if (testClass.showQuestion(numbers.currentQuestion).contains("_")) { //FILL IN THE BLANK HANDLING
								
								if (numbers.fillBlankCheck == 1) { //GRABBING ANSWERS FROM FILL-IN-THE-BLANK
									
									testClass.storeAnswer(textFieldFillBlank.getText(), numbers.currentQuestion - 1);
									
									textFieldFillBlank.setVisible(false);
									lblFillBlankAnswer.setVisible(false);
									rdbtnAnswer1.setVisible(true);
									rdbtnAnswer2.setVisible(true);
									rdbtnAnswer3.setVisible(true);
									rdbtnAnswer4.setVisible(true);
									multiChoiceAnswerGroup.clearSelection();
									
									numbers.fillBlankCheck = 0;
									
								} if (numbers.matchingCheck == 1) { //GRABBING ANSWERS FROM MATCHING
									
									if (comboBoxAnswer1.getSelectedItem().toString().equals("-")) {
										testClass.storeMatchingAnswer("zxc", 0);
									} else {
										testClass.storeMatchingAnswer(lblA.getText(), (int)(comboBoxAnswer1.getSelectedItem().toString().charAt(0) - 97));
									}
									
									if (comboBoxAnswer2.getSelectedItem().toString().equals("-")) {
										testClass.storeMatchingAnswer("zxc", 1);
									} else {
										testClass.storeMatchingAnswer(lblB.getText(), (int)(comboBoxAnswer2.getSelectedItem().toString().charAt(0) - 97));
									}
									
									if (comboBoxAnswer3.getSelectedItem().toString().equals("-")) {
										testClass.storeMatchingAnswer("zxc", 2);
									} else {
										testClass.storeMatchingAnswer(lblC.getText(), (int)(comboBoxAnswer3.getSelectedItem().toString().charAt(0) - 97));
									}
									
									
									numbers.matchingCheck = 0;
									
								}
								
								textFieldFillBlank.setVisible(true);
								textFieldFillBlank.setText("");
								lblFillBlankAnswer.setVisible(true);
								rdbtnAnswer1.setVisible(false);
								rdbtnAnswer2.setVisible(false);
								rdbtnAnswer3.setVisible(false);
								rdbtnAnswer4.setVisible(false);
								
								numbers.fillBlankCheck = 1;
								numbers.currentQuestionNumber++;
								lblQuestion.setText("Question " + (numbers.currentQuestionNumber) + ":");
								
							} else if (testClass.showQuestion(numbers.currentQuestion).contains("Match the ")) { //MATCHING HANDLING
								
								if (numbers.fillBlankCheck == 1) { //GRABBING ANSWERS FROM FILL-IN-THE-BLANK
									
									testClass.storeAnswer(textFieldFillBlank.getText(), numbers.currentQuestion - 1);
									
									textFieldFillBlank.setVisible(false);
									lblFillBlankAnswer.setVisible(false);
									rdbtnAnswer1.setVisible(true);
									rdbtnAnswer2.setVisible(true);
									rdbtnAnswer3.setVisible(true);
									rdbtnAnswer4.setVisible(true);
									multiChoiceAnswerGroup.clearSelection();
									
									numbers.fillBlankCheck = 0;
									
								}
								
								numbers.matchingCheck = 1;
								numbers.finalDenominator = 7;
								
								textAreaMatching.setVisible(true);
								textAreaMatching.setText(testClass.returnMatchingQuestions());
								testClass.randomizeMatchingAnswerOrder();
								
								rdbtnAnswer1.setVisible(false);
								rdbtnAnswer2.setVisible(false);
								rdbtnAnswer3.setVisible(false);
								rdbtnAnswer4.setVisible(false);
								
								comboBoxAnswer1.setVisible(true);
								comboBoxAnswer2.setVisible(true);
								comboBoxAnswer3.setVisible(true);
								lblA.setVisible(true);
								lblA.setText(testClass.getRandomMatchingAnswer());
								lblB.setVisible(true);
								lblB.setText(testClass.getRandomMatchingAnswer());
								lblC.setVisible(true);
								lblC.setText(testClass.getRandomMatchingAnswer());
								
								textFieldFillBlank.setVisible(false);
								numbers.currentQuestionNumber++;
								lblQuestion.setText("Question " + (numbers.currentQuestionNumber) + ":");
								
							} else {																		//MULTIPLE CHOICE HANDLING
								
								if (numbers.fillBlankCheck == 1) { //GRABBING ANSWERS FROM FILL-IN-THE-BLANK
									
									testClass.storeAnswer(textFieldFillBlank.getText(), numbers.currentQuestion - 1);
									
									textFieldFillBlank.setVisible(false);
									lblFillBlankAnswer.setVisible(false);
									rdbtnAnswer1.setVisible(true);
									rdbtnAnswer2.setVisible(true);
									rdbtnAnswer3.setVisible(true);
									rdbtnAnswer4.setVisible(true);
									multiChoiceAnswerGroup.clearSelection();
									
									numbers.fillBlankCheck = 0;
									
								}
								
								String choices[] = testClass.genGivenAnswers(numbers.currentQuestion);
								
								rdbtnAnswer1.setText(choices[0]);
								rdbtnAnswer2.setText(choices[1]);
								rdbtnAnswer3.setText(choices[2]);
								rdbtnAnswer4.setText(choices[3]);
								rdbtnAnswer3.setVisible(true);
								rdbtnAnswer4.setVisible(true);
								textFieldFillBlank.setVisible(false);
								multiChoiceAnswerGroup.clearSelection();
								
								numbers.currentQuestionNumber++;
								lblQuestion.setText("Question " + (numbers.currentQuestionNumber) + ":");
								}
							}
						textAreaQuestion.setCaretPosition(0);
					}
				});
				
				JButton btnBegin = new JButton("Begin");
				btnBegin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						numbers.currentQuestion++;
						btnProgressor.setVisible(true);
						//btnPreviousQuestion.setVisible(true);
						btnBegin.setVisible(false);
						rdbtnAnswer1.setVisible(true);
						rdbtnAnswer2.setVisible(true);
						rdbtnAnswer3.setVisible(true);
						rdbtnAnswer4.setVisible(true);
						lblQuestion.setText("Question 1:");
						lblFillBlankAnswer.setVisible(false);
						
						textAreaQuestion.setText(testClass.showQuestion(numbers.currentQuestion));
						
						String[] choices= testClass.genGivenAnswers(numbers.currentQuestion);
						
						rdbtnAnswer1.setText(choices[0]);
						rdbtnAnswer2.setText(choices[1]);
						rdbtnAnswer3.setText(choices[2]);
						rdbtnAnswer4.setText(choices[3]);
						
						/*for (String question : multiChoiceQuestions) {
							textAreaTest2.append(question + "\n");
						}
						
						for (String answer : multiChoiceAnswers) {
							textAreaTest.append(answer + "\n");
						}*/
						
						multiChoiceAnswerGroup.clearSelection();
					}
				});
				
				
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblQuestion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrpQuestion, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnProgressor, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBegin, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(656, Short.MAX_VALUE))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrpQuestion, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnBegin)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnProgressor))
								.addComponent(lblQuestion))
							.addContainerGap(27, Short.MAX_VALUE))
				);
				
				
				scrpQuestion.setViewportView(textAreaQuestion);
				panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		
	}
	
	//Universal number storage
	public abstract static class numbers {
		private static int currentQuestion = -1;
		private static int currentQuestionNumber = 1;
		private static int fillBlankCheck = 0;
		private static int matchingCheck = 0;
		private static int finalDenominator = 5;
	}
}
