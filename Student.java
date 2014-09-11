import java.util.ArrayList;

public class Student {

	private int studentNum;
	private String firstName;
	private String lastName;
	private double fineBalance = 0;
	private int numBooks = 0;

	public Student(int stuNum, String fName, String lName) {
		studentNum = stuNum;
		firstName = fName;
		lastName = lName;

	}

	public int getStuNum() {
		return studentNum;
	}

	public String getfName() {
		return firstName;
	}

	public String getlName() {
		return lastName;
	}

	public Double getFineBalance() {
		return fineBalance;
	}

	public void setFineBalace(Double fine) {
		fineBalance = fine;
	}
	public int getNumBooks() {
		return numBooks;
	}
	public int setNumBooks(int numBooks) {
		return numBooks = this.numBooks;
	}
	

	public ArrayList<Book> getBooksOwned() {
		ArrayList<Book> booksOwned = new ArrayList<Book>();
		for (int i = 0; i < Main.bookArraySpot; i++) // searches through all the
														// books to see if
														// they're owned by this
														// student

		{

			if (Main.bookArray[i].getOwnedBy() == studentNum)// if the student
																// number under
																// the ownedBy
																// variable is
																// this
																// student's
																// number, then
																// the student
																// owns this
																// book
			{
				booksOwned.add(Main.bookArray[i]);
			}
		}

		numBooks = (booksOwned.size()); // sets the numBooks to the size of
										// booksOwned, which is how many books
										// the student has checked out
		return booksOwned;

	}

	

}
