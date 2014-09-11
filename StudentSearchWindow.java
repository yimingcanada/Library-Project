import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;


/* 
 * This class is mainly used for the creation of the constructor of a pop up window to show the search results in the GUI
 */
public class StudentSearchWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8158868281076659378L;

	JList<String> list;
	DefaultListModel<String> model;
	JLabel info;
	JLabel infoBook1a;
	JLabel infoBook1b;
	JLabel infoBook2a;
	JLabel infoBook2b;
	JLabel infoBook3a;
	JLabel infoBook3b;

	private ArrayList<String> searchResults = new ArrayList<String> ();
	private ArrayList<Integer> searchResultStuNum = new ArrayList<Integer> ();

	public StudentSearchWindow(String search,  final boolean isSearchingName) {
//2 modes : false  - search by student number / true - search by name

		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		JScrollPane pane1 = new JScrollPane(list);
		pane1.setPreferredSize(new Dimension(200,200));


		for (int j = 0; j < Main.stuArraySpot; j++)
		{

			String currentSearch;
			int stuNum = Main.stuArray [j].getStuNum(); // gets the student number of the current student in the for loop

			if (isSearchingName == false) //if it's not searching by name, then it's searching by student number
			{
				currentSearch = Integer.toString(stuNum) ; //converts the current searched variable into String 
				if (currentSearch.equals(search)) 
				{
					searchResults.add(Integer.toString(stuNum));
					searchResultStuNum.add(stuNum);
				}
			}
			else
			{//search by name
				currentSearch = (Main.stuArray [j].getfName()) +" " + (Main.stuArray [j].getlName()); // combines the first and last name with a space in between for simplification
				if ((currentSearch.toLowerCase()).contains(search.toLowerCase())) // if the lowercase of the current variable contains the lower case of the search variable, then add to searchResults
				{
					searchResults.add(currentSearch); //add to searchResults
					searchResultStuNum.add(stuNum);//record the student number along with searchResults 
				}
			}
		}
		for (int i = 0; i < searchResults.size(); i++) 
		{
			model.addElement(searchResults.get(i)); //adds searchResults into model list to display on the GUI
		}
		JComponent pane2a = TabbedPane.makeTextPanel ("Info");
		JComponent pane2b = TabbedPane.makeTextPanel (""); //the pane with all the info

		info = new JLabel ("");
		infoBook1a = new JLabel ("");
		infoBook1b = new JLabel ("");
		infoBook2a = new JLabel ("");
		infoBook2b = new JLabel ("");
		infoBook3a = new JLabel ("");
		infoBook3b = new JLabel ("");
		

		pane2b.add (info);
		pane2b.add(infoBook1a);
		pane2b.add(infoBook1b);
		pane2b.add(infoBook2a);
		pane2b.add(infoBook2b);
		pane2b.add(infoBook3a);
		pane2b.add(infoBook3b);
		

		JPanel pane = new JPanel ();
		pane2b.setLayout(new BoxLayout (pane2b, BoxLayout.Y_AXIS));

		list.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me) //if clicked, send the index on the jList to the setTextOfLabel methods for them to update
			{
				int indexClicked = list.locationToIndex(me.getPoint());
				if (indexClicked != -1) // if nothing shows up and the user clicks, getPoint will return -1 which cannot be processed
				{
					setTextOfLabel1(indexClicked);
					setTextOfLabel2 (indexClicked);
				}
				
			}
		});

		pane.add(pane1);
		pane.add(pane2a);
		pane.add(pane2b);

		pane.setLayout(new BoxLayout (pane, BoxLayout.Y_AXIS));
		add(pane);

	}

	public void setTextOfLabel1 (int index)//displays name, student number, fines
	{ 
		int stuNum = (int) searchResultStuNum.get(index); //gets the student number from the searchResultsStuNum Arraylist, which stored the student numbers as searchResults was accumulating the actual search results
		int stuSpot = Main.findStu(stuNum);//gets the index of the student in stuArray []


		String firstName = Main.stuArray [stuSpot].getfName();
		String lastName = Main.stuArray [stuSpot].getlName();
		double fine = Main.fineOwed(stuNum);
											
		info.setText( "First Name: "+ firstName +"     Last Name: " + lastName +"      Student number: " + stuNum + "     Outstanding fines: $" + fine); 


	}
	public void setTextOfLabel2 (int index)//displays all books
	{ 
		int stuNum = searchResultStuNum.get(index); //gets student number stored previously from when searchResults was accumulating
		int stuSpot = Main.findStu(stuNum); //finds the student index in the stuArray

		ArrayList <Book> ownedBooks = Main.stuArray [stuSpot].getBooksOwned();//gets the ArrayList of Books the student owns
		int numOfBooks = ownedBooks.size(); //gets the number of books there are

		ArrayList <String> allInfo = new ArrayList<String> ();//new ArrayList for displaying all book info


		for (int i = 0 ; i < numOfBooks ; i ++)
		{
			allInfo.add(ownedBooks.get(i).getAllBookInfo());
		}


		String []dateInfo={"","",""}; //array for holding the date checked out for all books
		for (int i = 0 ; i < numOfBooks ; i ++)
		{
			allInfo.add(ownedBooks.get(i).getAllBookInfo());
			dateInfo [i]= "Date checked out: " + ownedBooks.get(i).getCheckOutDate();
		}


		if (numOfBooks >= 1)
		{
			infoBook1a.setText( "Books owned: " + numOfBooks + " " +  allInfo.get (0)); //if student owns 1 book, display info of that book
			infoBook1b.setText(dateInfo[0]);
		}

		else
		{
			infoBook1a.setText("No books owned");// if student doesn't, reset the label
			infoBook1b.setText("");
		}


		if (numOfBooks >= 2 )
		{
			infoBook2a.setText(allInfo.get (1)); // if student owns 2 books, display info of this book and other book
			infoBook2b.setText(dateInfo[1]);
		}
		else
		{
			infoBook2a.setText("");
			infoBook2b.setText("");
		}


		if (numOfBooks == 3 )
		{
			infoBook3a.setText( allInfo .get(2)); // displays 3rd book if student owns 3 books
			infoBook3b.setText(dateInfo[2]);
		}

		else
		{
			infoBook3a.setText("");
			infoBook3b.setText("");
		}

	}


}
