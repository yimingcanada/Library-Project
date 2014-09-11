public class Book {

	private boolean isTaken;
	private String title;
	private String author;
	private String category;
	private double price;
	private String ISBN;
	private double starRating;
	private int bookID;
	private String returnDate;
	private String checkoutDate;

	private int ownedBy = 0; // Initializes the book as owned by no one.

	final private static int maxBorrowTime = 14;

	public Book(int bookID, String title, String author, double price,
			String ISBN, double starRating, String category) {

		// when adding a book,
		// ownedBy set to null
		// checkoutDate to a negative date (null)
		// isTaken, isLost to false

		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.starRating = starRating;
		this.category = category;

	}
	public String getAllBookInfo ()
	{
		String allInfo = "BookID: " + bookID + "  Title: " + title +
				"  Author: " + author +  "  Category: " + category +
				"  Price: " + price + "  ISBN: " + ISBN + "  Star rating: " + starRating;


		return allInfo;

	}
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public String getISBN() {
		return ISBN;
	}

	public double getStarRating() {
		return starRating;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBookID() {
		return bookID;
	}

	public int getOwnedBy() {
		return ownedBy;
	}

	public String getCheckOutDate() {
		return checkoutDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public int getMaxBorrowTime() {
		return maxBorrowTime;
	}

	public double getPrice() {
		return price;
	}

	public void setOwnedBy(int stuNum) {
		ownedBy = stuNum;
	}

	public void setCheckoutDate(String date) {
		checkoutDate = date;
	}

	public void setReturnDate(String date) {
		returnDate = date;
	}

	public void SetisTaken(boolean input) {
		isTaken = input;
	}

	public boolean getIsTaken() {
		return isTaken;
	}


}
