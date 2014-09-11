import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;


/* 
 * This class is mainly used for the creation of the constructor of a pop up window to show the search results in the GUI
 */
public class BookSearchWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8158868281076659378L;

	JList<String> list;
	DefaultListModel<String> model;
	JLabel info;
	ArrayList<String> searchResults = new ArrayList<String> (); //the arrayList for storing the search results
	ArrayList<Integer> searchResultBookID = new ArrayList<Integer> (); //the arrayList for storing the bookIDs of the books in the search results

	public BookSearchWindow(String search,  final int searchMode) 
	{
		//searchModes: 1 - title / 2 - author / 3 - category / 4 - ID



		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		JScrollPane pane1 = new JScrollPane(list);
		pane1.setPreferredSize(new Dimension(200,200));


		for (int i = 0; i < Main.bookArraySpot; i++) //searches through all possible books
		{
			Book currentBook = Main.bookArray [i];

			int bookID = currentBook.getBookID();

			if (searchMode == 1) //if searching for title,
			{


				String currentBookTitle = currentBook.getTitle(); //gets the title of the current book

				if (currentBookTitle.contains(search)) //if the title contains the search string,
				{
					System.out.println(search);
					searchResults.add(currentBookTitle); //add to results
					searchResultBookID.add(bookID);// keep track of all the book IDs in the results
				}
			}
			else if (searchMode == 2) //if searching by author
			{


				String currentBookAuthor = currentBook.getAuthor(); //gets the author of the current book

				if (currentBookAuthor.toLowerCase().contains(search.toLowerCase())) //if the string (case insensitive) is part of the author String, then add to searchResults
				{
				
					searchResults.add(currentBookAuthor);
					searchResultBookID.add(bookID);
				}
			}
			else if (searchMode == 3) //if searching by category
			{


				String currentBookCat = currentBook.getCategory(); //gets the category of the current book

				if (currentBookCat.equalsIgnoreCase(search)) 
				{
					
					searchResults.add(currentBookCat);
					searchResultBookID.add(bookID);
				}
			}

			else if (searchMode == 4) // if searching by ISBN
			{

				String currentBookID = Integer.toString(currentBook.getBookID());

				if (currentBookID.equals(search))
				{
					searchResults.add(currentBookID);
					searchResultBookID.add(bookID);
				}
			}



		}
		for (int i = 0; i < searchResults.size(); i++) 
		{
			model.addElement(searchResults.get(i)); //adds all the searchResults to the JList display panel
		}
		JComponent pane2a = TabbedPane.makeTextPanel ("Info");
		JComponent pane2b = TabbedPane.makeTextPanel ("");
		info = new JLabel ("");

		pane2b.add (info);

		JPanel pane = new JPanel ();
		pane.setLayout(new BoxLayout (pane, BoxLayout.Y_AXIS));

		list.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent me)
			{
				int indexClicked = list.locationToIndex(me.getPoint()); //if mouse is clicked on on JList panel, get the index of which searchResults is clicked on
				if (indexClicked != -1) // if nothing shows up and the user clicks, getPoint will return -1 which cannot be processed
				setTextOfLabel(indexClicked); //sends the index to update info pane
			}
		});

		pane.add(pane1);
		pane.add(pane2a);
		pane.add(pane2b);
		add(pane);
		pane1.setPreferredSize(new Dimension(200,200));
	}

	public void setTextOfLabel (int index)//displays all info the book has
	{
		
		int bookID = searchResultBookID.get(index);
		int bookSpot = Main.findBook(bookID);
		
		Book book = Main.bookArray[bookSpot];
		String bookTitle = book.getTitle();
		String bookAuthor = book.getAuthor();
		String bookCategory = book.getCategory();
		int bookOwnedBy = book.getOwnedBy();
		double bookStarRating = book.getStarRating();
		double bookPrice = book.getPrice();
		String bookISBN = book.getISBN();

		info.setText( "Title: "+bookTitle +"     Author: " + bookAuthor +"      Category: " +bookCategory
				+"      Book ID: " + bookID + "      Owned by Student number: " + bookOwnedBy + "     Star rating: " + bookStarRating + 
				"     Price: " + bookPrice + "     ISNB: " + bookISBN); 



	}

}
