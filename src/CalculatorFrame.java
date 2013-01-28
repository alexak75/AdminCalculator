import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;


public class CalculatorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea txtrH;
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton rdbtnNewRadioButton = new JRadioButton("Low");
	private JRadioButton rdbtnHigh = new JRadioButton("High");
	private JRadioButton rdbtnHeadOrCa = new JRadioButton("Head or CA");
	private ArrayList<Double> totalGivenArray = new ArrayList<Double>();
	private ArrayList<Integer> totalPotentialArray = new ArrayList<Integer>();
	private int arrayCounter = 0;
	private double totalPointsGiven = 0;
	private double totalPotentialPoints = 0;
	private int finalPercent = 0;
	private JTextField textField_2;
	private double totalPointsGivenTally = 0;
	private DecimalFormat df = new DecimalFormat("#.##");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorFrame frame = new CalculatorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		setTitle("Admin Calculator");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setBounds(11, 64, 38, 16);
		
		JLabel lblNameOfAdmin = new JLabel("Name of Admin:");
		lblNameOfAdmin.setBounds(11, 11, 102, 16);
		
		JLabel lblRank = new JLabel("Rank:");
		lblRank.setBounds(11, 121, 38, 16);
		
		rdbtnNewRadioButton = new JRadioButton("Low");
		rdbtnNewRadioButton.setBounds(11, 186, 57, 23);
		
		rdbtnHigh = new JRadioButton("High");
		rdbtnHigh.setBounds(11, 164, 62, 23);
		
		rdbtnHeadOrCa = new JRadioButton("Head or CA");
		rdbtnHeadOrCa.setBounds(11, 141, 103, 23);
		
		txtrH = new JTextArea("Admin\tType\tScore\tPercentage\tPoints Given\tPoint Tally\n============" +
				"==============================================\n");
		txtrH.setBounds(163, 11, 581, 421);
		txtrH.setEditable(false);
		//JScrollPane scrollPane = new JScrollPane(txtrH);
		//contentPane.add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(11, 26, 134, 28);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(11, 81, 50, 28);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("/ 25");
		label.setBounds(67, 87, 27, 16);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(11, 221, 146, 28);
		btnSubmit.addActionListener(
				
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						submitTaskJButtonActionPerformed(event);
					}
				}
		);
		
		textField_2 = new JTextField();
		textField_2.setBounds(163, 444, 491, 28);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnHigh);
		bg.add(rdbtnHeadOrCa);
		
		JButton btnResetButton = new JButton("Reset");
		btnResetButton.setBounds(11, 252, 146, 29);
		btnResetButton.addActionListener(
					
					new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							resetJButtonActionPerformed(event);
						}
					}
			);
		contentPane.setLayout(null);
		contentPane.add(textField);
		contentPane.add(lblNameOfAdmin);
		contentPane.add(lblScore);
		contentPane.add(textField_1);
		contentPane.add(label);
		contentPane.add(rdbtnHeadOrCa);
		contentPane.add(rdbtnHigh);
		contentPane.add(rdbtnNewRadioButton);
		contentPane.add(btnResetButton);
		contentPane.add(btnSubmit);
		contentPane.add(lblRank);
		contentPane.add(txtrH);
		contentPane.add(textField_2);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setBounds(110, 450, 61, 16);
		contentPane.add(lblResults);
		
		JButton btnWriteToFile = new JButton("Write to File");
		btnWriteToFile.addActionListener(
				
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						writeToFileJButtonActionPerformed(event);
					}
				}
		);
		btnWriteToFile.setBounds(11, 282, 146, 29);
		contentPane.add(btnWriteToFile);
	}
	
	public void submitTaskJButtonActionPerformed(ActionEvent event)
	{
		String adminName = textField.getText();
		double score = 0;
		double pointsGiven;
		int adminValue = 0;
		
		if((score <= 25) && (score >= 0))
		{
			if(rdbtnHeadOrCa.isSelected() || rdbtnHigh.isSelected() || rdbtnNewRadioButton.isSelected())
			{
				if(!(textField_1.getText().equals("")))
				{
		
		score = Double.parseDouble(textField_1.getText());			
					
		if(rdbtnHeadOrCa.isSelected())
		{
			adminValue = 10;
		}
		if(rdbtnHigh.isSelected())
		{
			adminValue = 7;
		}
		if(rdbtnNewRadioButton.isSelected())
		{
			adminValue = 5;
		}
		
		txtrH.append(adminName + "\t");
		
		if(rdbtnHeadOrCa.isSelected())
		{
			txtrH.append("Head/CA\t");
		}
		if(rdbtnHigh.isSelected())
		{
			txtrH.append("High\t");
		}
		if(rdbtnNewRadioButton.isSelected())
		{
			txtrH.append("Low\t");
		}
		
		pointsGiven = (score / 25) * adminValue;
		String percentString = String.valueOf(df.format((pointsGiven / adminValue) * 100) + "%");
		
		// append score out of 25
		txtrH.append((int) score + " / 25\t");
		
		// Append percent
		txtrH.append(percentString + "\t");
		
		// Append points given
        txtrH.append(df.format(pointsGiven));
        txtrH.append(" / " + adminValue + "\t");
		
		
		totalPotentialArray.add(arrayCounter, adminValue);
		totalGivenArray.add(arrayCounter, pointsGiven);
		arrayCounter++;
		
		totalPointsGivenTally += pointsGiven;
		
		// Append total point counter
		txtrH.append(df.format(totalPointsGivenTally) + "\n");
		calculateAdmin();
		
		//txtrH.setCaretPosition(txtrH.getDocument().getLength());
		/*
		if(rdbtnHeadOrCa.isSelected())
		{
			rdbtnHeadOrCa.setSelected(false);
		}
		if(rdbtnHigh.isSelected())
		{
			rdbtnHigh.setSelected(false);
		}
		if(rdbtnNewRadioButton.isSelected())
		{
			rdbtnNewRadioButton.setSelected(false);
		}
		*/
		
		} //end score if statement
		} //end radiobutton condition if statement
		} //end of empty score if statement
	}
	
	public void calculateFinalPercent()
	{
		totalPointsGiven = 0;
		totalPotentialPoints = 0;
		//calculates the total number of points given
				for(int x = 0; x < arrayCounter; x++)
				{
					totalPointsGiven += totalGivenArray.get(x);
					System.out.print("Total points = " + totalPointsGiven+"\n");
				}
				
				//calculates the total number of potential points
				for(int y = 0; y < arrayCounter; y++)
				{
					totalPotentialPoints += totalPotentialArray.get(y);
					System.out.print("Total potential points = " + totalPotentialPoints+"\n");
				}
				
				finalPercent = (int) (totalPointsGiven / totalPotentialPoints * 100);
	}
	
	public void calculateAdmin()
	{	
		calculateFinalPercent();
		String yesOrNo = "";
		
		if(finalPercent > 66)
		{
			yesOrNo = "Yes";
		}
		else if(finalPercent < 66)
		{
			yesOrNo = "No";
		}
		
		textField_2.setText(df.format(totalPointsGivenTally) + " out of " + totalPotentialPoints + " points   Ñ   " + finalPercent + "%"
				+ "   Ñ   " + yesOrNo + " for admin");
		
	}	
	
	public void resetJButtonActionPerformed(ActionEvent event)
	{
		for(int x = arrayCounter - 1; x >= 0; x--)
		{
			totalGivenArray.remove(x);
			totalPotentialArray.remove(x);
		}
		
		txtrH.setText("Admin\tType\tScore\tPercentage\tPoints Given\tPoint Tally\n============" +
				"==============================================\n");
		
		totalPointsGiven = 0;
		totalPotentialPoints = 0;
		finalPercent = 0;
		arrayCounter = 0;
		textField_2.setText("");
	}
	
	public void writeToFileJButtonActionPerformed(ActionEvent event)
	{
		try{
			  // Create file 
			  FileWriter fstream = new FileWriter("adminresults.txt");
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(txtrH.getText());
			  out.close();
		}
		catch (Exception e)
		{//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		}
	}
}
