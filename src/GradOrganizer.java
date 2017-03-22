import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util. ArrayList;


public class GradOrganizer extends JFrame
{
	private JLabel fileJL, sortBy, searchLabel, showOnlyLabel, lineOne, lineTwo, lineThree, lastLine;
	private JButton loadButton,firstNameButton,lastNameButton,notYetInvited, needtoThank, invitedButton, giftButton, thankedButton, saveButton, searchButton;
	private JTextField dataFileText, searchBox, enterContact;
	private ImageIcon pic;
	private JPanel northPanel, eastPanel, southPanel, CenterPanel;
	private JTextArea displayArea;
	private JScrollPane scroller;
	private ArrayList<Contacts> contactsArray;
	private String inputFile, outputFile;


	public GradOrganizer()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Graduation Orgranizer");
		setSize(600,600);
		setLayout(new BorderLayout());
		buildNorthPanel();
		buildCenterPanel();
		buildSouthPanel();
		buildEastPanel();

		pack();
		setVisible(true);
		inputFile = JOptionPane.showInputDialog("Enter file name. (I'd recommend 'mylist.txt'");
	}

	public static String padTo(String s, int len)
	{
		int x = s.length();for(int i = x; i<len;i++)
		{
			s+=" ";
		}
		return s;
	}



	public void buildNorthPanel()
	{
		//Cap Picture
		pic = new ImageIcon("cap-diploma.jpg"); 
		fileJL= new JLabel("Data File: ", pic, SwingConstants.CENTER);

		//dataFileText = new JTextField(15); // New text field
		loadButton = new JButton("Load"); // Button is labeled "Load"
		loadButton.addActionListener(new ButtonListener());// Implement action listener
		northPanel = new JPanel(); //Can add to components to this panel
		

		//Adding components to north Panel
		northPanel.add(fileJL); //add image
		//northPanel.add(dataFileText); //add text field
		northPanel.add(loadButton); // add load button

		add(northPanel, BorderLayout.NORTH); //add north pane;	
		northPanel.setBackground(Color.BLUE);
	}

	public void buildCenterPanel()
	{
		//Display area
		displayArea = new JTextArea(20, 30);
		displayArea.setEditable(false);
		displayArea.setFont( new Font("Monospaced", Font.PLAIN, 12));

		//Scroller
		scroller = new JScrollPane (displayArea);
		scroller.setPreferredSize( new Dimension(500, 300));
		add(scroller, BorderLayout.CENTER);

		displayArea.setText("new text");
		displayArea.append("more text");

		displayArea.setText(padTo("First Name",15));
		displayArea.append(padTo("Last Name",15));
		displayArea.append(padTo("Address",20));
		displayArea.append(padTo("Invited",10));
		displayArea.append(padTo("Gift",10));
		displayArea.append(padTo("Thanked",10));

		displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
		                           //First name               //lastName                 //address                //invited              //gift             //thanked

	}

	public void buildSouthPanel()
	{
		sortBy= new JLabel("Sort By:");

		//First Name
		firstNameButton = new JButton("First Name");
		firstNameButton.addActionListener(new ButtonListener());
		//firstNameButton.addActionListener(new ButtonListener());

		//Last Name
		lastNameButton = new JButton("Last Name");
		lastNameButton.addActionListener(new ButtonListener());
		
		
		

		//Search
		searchLabel = new JLabel("Search:");
		searchBox = new JTextField(15);
		
		//Search Button
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ButtonListener());

		//Show Only
		showOnlyLabel = new JLabel("Show Only:");
		notYetInvited = new JButton("Not yet invited");
		notYetInvited.addActionListener(new ButtonListener ());
		needtoThank = new JButton("Need to thank");
		needtoThank.addActionListener(new ButtonListener());

		//New South panel and adding to it
		southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.add(firstNameButton);
		southPanel.add(lastNameButton);
		southPanel.add(searchLabel);
		southPanel.add(searchBox);
		southPanel.add(searchButton);
		southPanel.add(showOnlyLabel);
		southPanel.add(notYetInvited);
		southPanel.add(needtoThank);
		southPanel.setBackground(Color.blue); //background color

	}

	public void buildEastPanel()
	{
		//Labels... and a lone text field
		lineOne = new JLabel("Enter first then last ");
		lineTwo = new JLabel("name seperated by a space ");
		lineThree = new JLabel("to identify contact.");
		lastLine = new JLabel("Save your changes to a file");
		enterContact = new JTextField(15);

		//Buttons
		enterContact.addActionListener(new ButtonListener());
		invitedButton = new JButton("Toggle Invited");
		invitedButton.addActionListener(new ButtonListener());
		giftButton = new JButton("Toggle Gift");
		giftButton.addActionListener(new ButtonListener());
		thankedButton = new JButton("Toggle Thanked");
		thankedButton.addActionListener(new ButtonListener());
		saveButton = new JButton("Save Changes");
		saveButton.addActionListener(new ButtonListener());

		//Creating and adding to the panel
		eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(10,1,5,10));

		eastPanel.add(lineOne);
		eastPanel.add(lineTwo);
		eastPanel.add(lineThree);
		eastPanel.add(enterContact);
		eastPanel.add(invitedButton);
		eastPanel.add(giftButton);
		eastPanel.add(thankedButton);
		eastPanel.add(lastLine);
		eastPanel.add(saveButton);
		eastPanel.setBackground(Color.lightGray);

		add(eastPanel, BorderLayout.EAST);

	}

	
	
	
	
	// ***************************Button listeners***************************** \\
	private class ButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				
				File f = new File(inputFile);
				Scanner inF= new Scanner(f);
				
				

				
				DataInputStream in = new DataInputStream(new FileInputStream(inputFile));

				StringTokenizer str;
				String input;
				String firstName, lastName, home;
				boolean invited, gift, length;
				String l = inF.nextLine();
				int a= Integer.parseInt(l);
				

				ArrayList<Contacts> contactsArray = new ArrayList<Contacts>();// ArrayList of contacts
				{
				for(int i = 0; i < a; i++)
				{
					input = inF.nextLine();
					str = new StringTokenizer(input, ",");//new string tokenizer

					firstName = str.nextToken(); //First name = first thing read
					lastName = str.nextToken(); // Last name = 2nd thing read
					home = str.nextToken(); // Home is the next thing read

					//read invited
					if(str.nextToken().compareToIgnoreCase("true") == 0)
					{
						invited = true;
					}
					else
						invited = false;
					//read gift
					if(str.nextToken().compareToIgnoreCase("true") == 0)
					{
						gift = true;
					}
					else
						gift = false;
					//read length
					if(str.nextToken().compareToIgnoreCase("true")== 0)
					{
						length = true;
					}
					else
						length = false;
					contactsArray.add(new Contacts(firstName, lastName, home, invited, gift, length));
				}
				System.out.println(contactsArray); //print array

				
				
				//loadButton
				if(e.getSource() == loadButton)
				{ 
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
									for(int i = 0; i<contactsArray.size(); i++)
									{
										displayArea.append("\n" + contactsArray.get(i));
									}
				}

				//****first name button*****
				if(e.getSource() == firstNameButton)
				{ 
					
					Contacts c = new Contacts();
					boolean check = true;

					while(check)
					{
						check = false;
						for(int i = 0; i < contactsArray.size()-1; i++)
						{
							if(contactsArray.get(i).getFirstName().compareTo(contactsArray.get(i+1).getFirstName())>0)
							{
								c = contactsArray.get(i);
								contactsArray.set(i, contactsArray.get(i + 1));
								contactsArray.set(i+ 1, c);
								check = true;
							}
							if(contactsArray.get(i).getFirstName().compareTo(contactsArray.get(i+1).getFirstName())>0)
							{
								if(contactsArray.get(i).getLastName().compareTo(contactsArray.get(i+1).getLastName())>0)
									
								{
								
									c = contactsArray.get(i);
								
									contactsArray.set(i, contactsArray.get(i + 1));
								
									contactsArray.set(i+ 1, c);
									check = true;
							}
								
							}
						}
					}
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
					//display areas
					//updating the arrayList
					for(int i = 0; i<contactsArray.size();i++)
					{
						displayArea.append("\n" + contactsArray.get(i));
					}
				}
				
				
				//******last name button*****
				if(e.getSource() == lastNameButton)
				{
					
					Contacts c = new Contacts();
					boolean check = true;

					while(check)
					{
						check = false;
						for(int i = 0; i < contactsArray.size()-1; i++)
						{
							if(contactsArray.get(i).getLastName().compareTo(contactsArray.get(i+1).getLastName())>0)
							{
								c = contactsArray.get(i);
								contactsArray.set(i, contactsArray.get(i + 1));
								contactsArray.set(i+ 1, c);
								check = true;
							}
							if(contactsArray.get(i).getLastName().compareTo(contactsArray.get(i+1).getLastName())>0)
									{

							
								if(contactsArray.get(i).getFirstName().compareTo(contactsArray.get(i+1).getFirstName())>0)
							
								{
								
									c = contactsArray.get(i);
								
									contactsArray.set(i, contactsArray.get(i + 1));
								
									contactsArray.set(i+ 1, c);
									check = true;
							}
						
									}
					}
					}
					//updating the arrayList
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
					//display areas
					for(int i = 0; i<contactsArray.size();i++)
					{
						displayArea.append("\n" + contactsArray.get(i));
					}
				}

				
				//******Not Yet Invited button***
				if(e.getSource() == notYetInvited)
				{
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
					
					for(int i = 0; i < contactsArray.size();i ++)
					{
							if(contactsArray.get(i).getInvited() == false)
							{
								displayArea.append("\n" + contactsArray.get(i));
							}
					}
				}
				
				
				//******Need to thank button*****
				if(e.getSource() == needtoThank)
				{
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
					
					for(int i = 0; i <contactsArray.size(); i ++) //Search Array
					{
						if(contactsArray.get(i).getThanked() == false)
						{
							displayArea.append("\n" + contactsArray.get(i));
						}	
					}
				}
				
				//SearchButton (South)
				if(e.getSource() == searchButton)
				{
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
					
					String searchName = searchBox.getText(); //Remember name entered
					for(int i = 0; i < contactsArray.size(); i ++)
					{
						if(searchName.equalsIgnoreCase(contactsArray.get(i).getFirstName()) || searchName.equalsIgnoreCase(contactsArray.get(i).getLastName())) //if first OR last name matches
						{
							displayArea.append("\n" + contactsArray.get(i));
						}
					}
					
				}
				
				
				
				
				//Toggle Invited (East)
				if(e.getSource() == invitedButton)
				{
					
					String name = enterContact.getText(); //Name update
					for(int i = 0; i < contactsArray.size(); i++) //Search Array for...
					{
			
						if (name.equalsIgnoreCase(contactsArray.get(i).getFirstName() + " " + contactsArray.get(i).getLastName()))//.. a matching fist and last name
							{
							
							if( contactsArray.get(i).getInvited() == false)
							{
									contactsArray.get(i).setInvited(true);
							}
								else
									contactsArray.get(i).setInvited(false);
							}
						
							}
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
			
					for(int c = 0; c <contactsArray.size(); c ++) //Search Array
					{
						{
							displayArea.append("\n" + contactsArray.get(c));
						}	
					}}
				
				//Toggle Gift (East)
				if (e.getSource() == giftButton)
				{
					String name = enterContact.getText(); //Name update
					for(int i = 0; i < contactsArray.size(); i++)
					{
						if(name.equalsIgnoreCase(contactsArray.get(i).getFirstName() + " " + contactsArray.get(i).getLastName())) //Search array for a matching first and last name
						{
							if(contactsArray.get(i).getGift() == false) //if gift is false
							 
								contactsArray.get(i).setGift(true);
								else
									contactsArray.get(i).setGift(false);
							
							}
							
						}
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
			
					for(int c = 0; c <contactsArray.size(); c ++) //Search Array
					{
						{
							displayArea.append("\n" + contactsArray.get(c));
						}	
					}
				}
				
					
				
				//Toggle Thanked (East)
				if(e.getSource() == thankedButton)
				{
					
					String name = enterContact.getText();
					for(int i = 0; i <contactsArray.size(); i++)
					{
						if(name.equalsIgnoreCase(contactsArray.get(i).getFirstName()+ " " + contactsArray.get(i).getLastName()))
								{
							
							if(contactsArray.get(i).getThanked()==false)
							{
								contactsArray.get(i).setThanked(true);
							}
									
								else
								{
							
								contactsArray.get(i).setThanked(false);
								}
							}
						
					}
					displayArea.setText(padTo("First Name",15));
					displayArea.append(padTo("Last Name",15));
					displayArea.append(padTo("Address",20));
					displayArea.append(padTo("Invited",10));
					displayArea.append(padTo("Gift",10));
					displayArea.append(padTo("Thanked",10));

					displayArea.append( "\n" + padTo("----------",15)+padTo("----------",15) + padTo("--------", 20) +   padTo("-------", 9) + padTo("-----", 10) +  padTo("-------", 10));
			
					for(int c = 0; c <contactsArray.size(); c ++) //Search Array
					{
						{
							displayArea.append("\n" + contactsArray.get(c));
						}	
					}
				}
				
				//Save changes (East)
				if(e.getSource() == saveButton)
				{
					//print writer
					outputFile = JOptionPane.showInputDialog("What would you like to save your file as? (I'd recommend 'mylist.txt' if you would like to override the old one)");

					PrintWriter output = new PrintWriter(outputFile);
					
					FileWriter fwriter = new FileWriter(outputFile);
					
					for(int c = 0; c <contactsArray.size(); c ++) //Search Array
					{
						{
							output.print(contactsArray.get(c).getFirstName() + ",");
							output.print(contactsArray.get(c).getLastName()+ ",");
							output.print(contactsArray.get(c).getHome()+ ",");
							output.print(contactsArray.get(c).getInvited()+ ",");
							output.print(contactsArray.get(c).getGift()+ ",");
							output.println(contactsArray.get(c).getThanked());
						}
					}
					output.close();
					System.out.println("Content was written to file: " + outputFile);
				}



				
				}}
			catch(IOException a)
			{
				System.out.println("Encountered an error");
			}
		}}}


//pg 93



