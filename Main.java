import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * Functions:
 * Adds student, saves to StudentData.txt
 * Adds books, saves to BookData.txt
 * Checks out books, saves checkout data to Checkoutdata.txt
 * Student cannot check a book out if they have over $5 in fines
 * Fines students if book is due or lost
 * Fines are saved in FineData.txt
 * Can search students by name or student number, displays all their info and books owned
 * Can search books by title, author, category and ID
 * Delete students if they don't have a fine and they don't have a book checked
 * Delete books if they're not checked out
 * bookLost method only needs bookID, it fines the student automatically and deletes the book that is lost
 * Student can pay a part of their fine off
 * If they pay more than their fine, the system calculates their change 
 * Gives error messages for invalid inputs
 * 
 * Library System Made by Eric and Yi Ming
 */


public class Main {

		/*initializes the student array and book array
		 *sets the maximum number of students to 1000 
		 *sets the maximum number of books to 1000
		 *the array size can be modified if the system starts to hold more than 1000 students and books
		 */		 
        static Student stuArray[] = new Student [1000];     
        static Book bookArray [] = new Book [1000];

        //when adding a new student, array will start adding a student at this spot, this also represents the number of students
        static int stuArraySpot = 0; 
      
        //when adding a new book, array will start adding a book at this spot
        static int bookArraySpot = 0; 
        
      //when adding a new book, bookID will be assigned to this value . Every time a new book is added, this value will +1.
        static int bookIDSpot = 1; 

        //max books the student can take
        final static int maxBooks = 3;
        
        //everyday the book is overdue, the fine is 10 cents
        final static double finePerLateDays = 0.1; 

        //the path of the data files
        static final File StudentData =  new File ("StudentData.txt"); 
        static final File BookData =  new File ("BookData.txt");
        static final File CheckoutData =  new File ("CheckoutData.txt");
        static final File FineData =  new File ("FineData.txt");



        public static void main(String[] args) throws IOException {

        		//loads all the data from the text files, if you're running this the first time, it will automatically create the default data files
                loadStuData (StudentData); 
                loadBookData (BookData);
                loadCheckoutData (CheckoutData);
                loadFineData (FineData);


                SwingUtilities.invokeLater(new Runnable() {
                	
                        public void run() {

                                createAndShowGUI();
                        }
                });
        }

        private static void createAndShowGUI() {
        	
                //Create and set up the window.
                JFrame frame = new JFrame("Student Library System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //Add content to the window.
                frame.getContentPane().add(new TabbedPane(), BorderLayout.CENTER);

                //Display the window.
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1000, 350);
        }


        //if add student submit button is clicked, activate this method
        public static boolean addStu (int stuNum, String fName, String lName) throws IOException
        {
                //student number must be greater than 0; 
                if (stuNum <= 0)
                {
                        return false;           
                }

                //student number already exists
                if (findStu(stuNum) >= 0)
                {
                        return false;
                }
                
                //creates the new student and inputs it into the next available stop in the student array
                //stuArraySpot starts at 0 and increases by 1 each time a student is added
                Student newStu = new Student (stuNum,fName,lName);
                stuArray[stuArraySpot] = newStu;
                
                FileWriter fw = new FileWriter(StudentData,true );
                PrintWriter output = new PrintWriter(fw);
                output.print(stuNum+ " ");//stores the input into the text file, StudentData.txt
                output.print(fName + " ");
                output.print(lName + " @");//adds the first delimiter to separate itself from other students
                System.out.println(stuNum);
                output.close( ); 
                fw.close( ); 
                                
                stuArraySpot++;
                return true;

        }

        //if delete student submit button is clicked, activate this method
        //this method will delete this student from the student array
        //if the student has a book checked out or still owes a fine, they cannot be deleted
        public static boolean deleteStu (int stuNum) throws IOException
        {
                int stuIndex = findStu (stuNum);

                //student doesn't exist
                if (stuIndex == -1)
                {               
                        return false;                   
                }
                
                //obtain the student object
                Student s = stuArray[stuIndex];

                //student still owes a fine, they can not be deleted
                if(s.getFineBalance() > 0)
                {
                        return false;
                }
                //checks if the student has a book checked out
                for (int i = 0; i <bookArraySpot; i ++)
                {

                        //if the student has a book checked out, then he or she can not be deleted
                        if (bookArray [i].getOwnedBy() == stuNum)
                        {
                                return false;
                        }
                }
                
                //since every condition is satisfied, student can be deleted
                
                //sets student to deleted
                stuArray[stuIndex] = null;

                //shifts the student array elements down 
                for (int i = stuIndex; i < stuArraySpot; i++)   
                {
                        stuArray[i] = stuArray[i+1];
                }
                //the student array spot for adding adding decreases by 1
                stuArraySpot --;                

                deleteStrInFile (StudentData, stuNum);
                //student was successfully deleted
                return true;    
        }

        //method to find what index in the student array is the student number  
        public static int findStu (int stuNum)
        {

                //if there are no students in the array
                if (stuArraySpot == 0)
                {
                        return -1;
                }
                
                for (int i = 0; i < stuArraySpot; i ++) //searches through stuArray for a student with the input as their student number
                {
                        Student s = stuArray[i];

                        if (s.getStuNum() == stuNum)
                        {
                                return i;                       //returns the index
                        }
                }
                return -1; //the student number doesn't exist in the student array
        }
        
        //method to find what index in the book array is the bookID
        public static int findBook (int bookID)
        {
                //if there are no books in the array
                if (bookArraySpot == 0)
                {
                        return -1;
                }

                for (int i = 0; i < bookArraySpot; i ++) //searches through bookArray for a book with the input as their book ID
                { 
                        Book b = bookArray[i];

                        if (b.getBookID() == bookID)
                        {
                                return i;                       
                        }

                }
                
                return -1; //the book ID doesn't exist in the book Array
        }

        //if add book submit button is clicked, activate this method
        public static boolean addBook (int bookID, String title, String author, double price, String ISBN, double starRating, String category) throws IOException
        {

                //book ID already exists
                if (findBook(bookID) >= 0)
                {
                        return false;
                }
                
                //star rating must be greater than 0 and less than 5
                //price of the book must be greater than 0
                if(starRating > 5.0 ||starRating < 0 || price < 0)
                {
                        return false;
                }
                
                //creates the book object and stores it into the book array
                Book newBook = new Book (bookID, title, author,  price,  ISBN,  starRating, category);
                bookArray[bookArraySpot] = newBook;

                FileWriter fw = new FileWriter(BookData,true );
                PrintWriter output = new PrintWriter(fw);
                
                //stores the input into the text file, BookData.txt
                output.print(bookIDSpot + "/");
                output.print(title + "/");
                output.print(author + "/");
                output.print(starRating + "/");
                output.print(category + "/");
                output.print(price + "/");
                output.print(ISBN + "@");       //adds the first delimiter to separate itself from other students


                output.close( ); 
                fw.close( ); 
                
                bookArraySpot++;
                bookIDSpot ++;
                
                

                return true;

        }

        //if delete book submit button is clicked, activate this method
        //deletes the book from the bookArray if the book has not been checkedOut
        public static boolean deleteBook (int bookID) throws IOException
        {

                int bookIndex = findBook(bookID);

                // book doesn't exist then this method do nothing
                if (bookIndex == -1) 
                {
                        return false;           
                }

                Book b = bookArray[bookIndex];

                //book is checked out then it can not be deleted
                if(b.getIsTaken() == true)
                {
                        return false;
                }

                //sets book to deleted
                bookArray[bookIndex] = null;

                //shifts the book array elements down 
                for (int i = bookIndex; i < bookArraySpot; i++) 
                {
                        bookArray[i] = bookArray[i+1];
                }
                //the book array spot for adding books decreases by 1
                bookArraySpot --;               


                deleteStrInFile (BookData, bookID);
                //book was successfully deleted
                return true; 

        }


        //if checkOut is true, the user successfully checks out book, else, display error
        public static boolean checkOut (int stuNum, int bookID, String date) throws IOException
        {


                int bookIndex = findBook(bookID);
                int studentIndex = findStu (stuNum);

                if (bookIndex < 0 || studentIndex < 0)
                {
                        return false; //book doesn't exist or student doesn't exist
                }

                Book b = bookArray[bookIndex];
                Student s = stuArray[studentIndex];

                //book is not checkout yet
                if(b.getIsTaken () == false)

                {                                       
                        
                        // the student already has more than 3 books checked out
                        if( numberOfBooksOwned (stuNum) == maxBooks) 
                        {
                                return false;
                        }
                        // user cannot checkout a book if their fine's are over $5.00.
                        if(s.getFineBalance() > 5.0)
                        {
                                return false;
                        }

                        b.setOwnedBy (stuNum);
                        b.setCheckoutDate(date);
                        b.SetisTaken (true);    

                        FileWriter fw = new FileWriter(CheckoutData,true );
                        PrintWriter output = new PrintWriter(fw);

                        output.print(bookID + " ");
                        output.print(stuNum+ " ");//stores the input into the text file, CheckoutData.txt
                        output.print(date + "@");//adds the first delimiter to separate itself from other students
                        output.close( ); 
                        fw.close( ); 

                        //book is successfully checked out
                        return true;
                }
                //book is already checkout
                return false;
        }
        public static int bookArraySpot (Book b) //method to find which spot in the book array the book belongs to
        {
                int bookIndex = 0; //Initialize book's spot in array
                for(int i = 0; i < bookArray.length; i++)
                {
                        if (b == bookArray[i])
                        {
                                bookIndex = i;
                                break;
                        }
                }
                return bookIndex;
        }
        public static int numberOfBooksOwned (int stuNum) //finds the number of books already owned by a student
        {
                int BooksOwned = 0; //counter for booksOwned by students

                for (int i = 0; i < bookArraySpot ; i ++)
                {
                        Book b = bookArray[i];

                        if(b.getOwnedBy()== stuNum)
                        {
                                BooksOwned++;
                        }
                }
                return BooksOwned;
        }

        //main method for returning books
        public static boolean returnBook (int bookID, String returnDate) throws IOException
        {
                //finds the book index of the book
                //if it exists, bookIndex is > 0
                int bookIndex = findBook(bookID);

                //book doesn't exist
                if(bookIndex < 0)
                {
                        return false;
                }

                Book b = bookArray[bookIndex];

                //book is not checked out
                if(b.getOwnedBy() == 0)
                {
                        return false;
                }

                //returns the book and determines if the student needs to pay a fine 

                int stuNum = b.getOwnedBy(); 


                String checkoutDate = b.getCheckOutDate();

                //book is not checked out
                if(b.getOwnedBy() == 0)
                {
                        return false;
                }

                //if librarian enters a return date that is before the checkout date of a book by mistake, an error will pop up.
                if (bkCorrectlyReturned (checkoutDate, returnDate) == false)
                {
                        return false;
                }
                Student s = stuArray[findStu(stuNum)];

                b.SetisTaken (false); //removes ownership
                b.setOwnedBy(0);//removes ownership
                b.setReturnDate(returnDate);//sets the return date
                int overDueDays = daysOverdue(b);//gets the amount of days overdue
                double  fineBalance = finePerLateDays * (double) overDueDays; // calculates their fine
                s.setFineBalace (s.getFineBalance() + fineBalance); //sets their fine

                deleteStrInFile (CheckoutData,bookID);

                if (s.getFineBalance() > 0) //if the student owes a fine 
                {
                        FileWriter fw = new FileWriter(FineData);
                        PrintWriter output = new PrintWriter(fw);

                        output.print(stuNum + " " + s.getFineBalance()+ "@"); //saves their fine in the FineData
                        output.close( );
                        fw.close( ); 
                }
                return true;

        }

        //method to calculate if the book was overdue, how many days it was overdue
        public static int daysOverdue (Book b)
        {
                int overDueDays = 0;
                int maxBorrowTime = b.getMaxBorrowTime(); //is always 14

                String checkOutDate = b.getCheckOutDate();
                String returnDate = b.getReturnDate(); 
                int totalDaysBorrowed = (int) calculateDays (checkOutDate, returnDate);//finds the number of days the book was borrowed

                if (totalDaysBorrowed >maxBorrowTime) //if the book is overdue
                {
                        overDueDays =  totalDaysBorrowed - maxBorrowTime;       //find the how many days it was overdue
                        return overDueDays;
                }

                return overDueDays; // if book is not overdue, return overDueDays = 0;


        }

        //below is the method for calculating the days between checkout date and return date of a book
        @SuppressWarnings("deprecation")
        public static long calculateDays(String startDate, String endDate) //takes in start and end date
        {
                Date sDate = new Date(startDate);
                Date eDate = new Date(endDate);
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(sDate);// converts string type dates into calendar type dates
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(eDate);
                return daysBetween(cal1, cal2);
        }

        //calculates days in between using calendar type dates 
        public static long daysBetween(Calendar startDate, Calendar endDate) {
                Calendar date = (Calendar) startDate.clone();
                long daysBetween = 0;
                while (date.before(endDate)) //keeps adding days to the start date until it reaches end date, and keeps track of how many days there are
                {
                        date.add(Calendar.DAY_OF_MONTH, 1);
                        daysBetween++;
                }
                return daysBetween;
        }



        //below is a method used in the return book method to determine if the librarian enters a return
        //date that is before the checkout date by mistake. If this occurs, an error will pop up which will 
        //tell the librarian to re-enter the return date

        public static boolean bkCorrectlyReturned (String checkoutDate, String returnDate)
        {
                //changes the original date format (yyyy/mm/dd) to (dd-MM-yyyy) in order to compare if
                //the return date is before the checkout date
                DateFormat dffrom = new SimpleDateFormat("yyyy/mm/dd");
                DateFormat dfto = new SimpleDateFormat("dd-MM-yyyy");  

                Date checkoutDt = null; //to initialize
                Date returnDt = null;
                try 
                {
                        checkoutDt = dffrom.parse(checkoutDate); // changes String dates into SimpleDateFormat
                        returnDt = dffrom.parse(returnDate);
                } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                //reformats the checkout and return dates into dd-MM-yyyy
                String formatedCheckoutDt = dfto.format(checkoutDt);
                String formatedReturnDt = dfto.format (returnDt);


                //below compares the return date to the checkoutDate
                //re-parse had to occur or code did not work
                
                //resets SimpleDateFormat as dd-MM-yyyy
                String myFormatString = "dd-MM-yyyy"; 
                SimpleDateFormat df = new SimpleDateFormat(myFormatString);
              
                //initialize checkout and return date as null
                //re convert formatedCheckoutDt and formatedReturnDt as SimpleDateFormat date
                Date date1 = null;
                Date date2 = null;
                try 
                {
                        date1 = df.parse(formatedCheckoutDt); //
                } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
               
                try 
                {
                        date2 = df.parse(formatedReturnDt);
                } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                //compares checkout and return date
                //return date cannot be before checkout date
                if (date1.equals(date2) || date1.before(date2))  
                {
                        return true;
                }
                else 
                {
                        return false;
                }
        }
        
        //below is a method that activates when the student loses their book. The price of the book is added to 
        //the student's total fine that they have to pay. There will be a button for "Book Lost"
        //This method does NOT call returnBook simply because it is more realistic to not charge people for an overdue lost book, which does not exist anymore

        public static boolean bookLost (int bookID) throws IOException
        {
                int bookIndex = findBook(bookID);

                //book doesn't exist
                if(bookIndex == -1)
                {
                        return false;
                }

                Book b = bookArray[bookIndex];

                //book wasn't checked out
                if (b.getIsTaken() == false)
                {
                        return false;
                }

                //finds the student number that owns the book
                int stuNum  = b.getOwnedBy();                   
                int stuIndex = findStu(stuNum);
                //finds the student object that owns the book
                Student s = stuArray[stuIndex];

                //adds the price of the book to their fine                              

                s.setFineBalace (s.getFineBalance() + b.getPrice());
                //book is lost so it is now set as "not taken"
                b.SetisTaken(false);

                //delete the book and checkout data because it is lost
                deleteBook(bookID);
                deleteStrInFile (CheckoutData, bookID);
                if (s.getFineBalance() > 0) //if the student owes a fine 
                {
                        FileWriter fw = new FileWriter(FineData);
                        PrintWriter output = new PrintWriter(fw);

                        output.print(stuNum + " " + s.getFineBalance()+ "@"); //saves their fine
                        output.close( );
                        fw.close( ); 
                }

                return true;

        }

        public static void deleteStrInFile(File file, int idToDelete) throws IOException //takes in the file path and a String, 
        //and deletes the object within the file with that String
        {
                Scanner sc = new Scanner (file); //scans the txt file of books or students

                sc.useDelimiter("//@%@%");//uses nothing as delimiter, scan the text as whole since this sequence does not exist in the file
                String dataText = null;//variable for holding raw string data from file


                while (sc.hasNext())
                {
                        dataText = sc.next();// reads entire file,"//@%@%" not found

                }
                sc.close();

                System.out.println(dataText);

                String [] data = dataText.split("@");//splits raw data into individual students/books
                int  dataLength = data.length;//gets length of the array
                System.out.println (" data:  " + Arrays.toString((data)));

                int toDelete = -1;//to initialize, stores the index of the array when idToDelete has been found for deletion later
                for (int i = 0 ; i < dataLength; i ++ ) //searches through every index in the data array
                {


                        Scanner scanModData = new Scanner (data [i]); 

                        if (file == BookData) //since BookData.txt uses spaces in their titles, they must use a different delimiter
                        {
                                scanModData.useDelimiter("/");
                        }


                        int currentID = scanModData.nextInt();
                        // since the ID of the book
                        // and students are both
                        // stored first in the text
                        // file, they are retrieved
                        // easily here using space or "/"
                        // as the delimiter

                        System.out.println(currentID);

                        if (currentID==idToDelete)      
                        {
                                toDelete = i;   //saves the index
                                break;
                        }
                }


                
                String finData = ""; //this is the output of the file, the finished product
                for (int i = 0 ; i < dataLength; i ++ )
                {
                        if (i != toDelete)

                        {
                                finData += data [i] + "@"; //adds the '@' back when rewriting the entire file back without one of the objects
                        }
                }

                FileWriter fw = new FileWriter(file);
                PrintWriter output = new PrintWriter(fw);
                output.print(finData);//rewrites

                output.close( ); //saves
                fw.close( );


                System.out.println("finData: " + finData);
        }
        public static void loadStuData (File file) throws IOException //loads all the students from studentData.txt
        {
                try 
                {

                        Scanner sc = new Scanner (file);
                        sc.useDelimiter("@");
                        ArrayList<String> dataText = new ArrayList<String> ();//variable for holding all the raw student data


                        while (sc.hasNext())
                        {
                                dataText.add(sc.next()); //adds the next student in the file, separated by '@'

                        }
                        sc.close();

                        System.out.println(dataText);

                        for (int i = 0; i <dataText.size() ; i ++)
                        {
                                Scanner scan = new Scanner (dataText.get(i)); //creates a new scanner for every string inside of the dataText ArrayList
                                //using space as the delimiter, the following variables can be obtained:
                                int stuNum = scan.nextInt();  //reads the text as variables
                                String firstName = scan.next();
                                String lastName = scan.next();

                                Student newStu = new Student (stuNum,firstName,lastName);
                                stuArray[stuArraySpot] = newStu; //adds the students using the variables given by the text file
                                stuArraySpot++;
                                scan.close();

                        }

                } 
                catch ( java.io.FileNotFoundException e) //if file does not exist
                {
                        FileWriter fw = new FileWriter(StudentData);
                        PrintWriter output = new PrintWriter(fw);

                        output.print("547352 Henry Thor @284762 David Hay @356664 Richard Gook @213743 Calvin Klein @563261 Daniel Khan @532736 Mary Atkinson @352637 Michael Jones @"); //creates the default student data list
                        output.close( );
                        fw.close( ); 
                        loadStuData (StudentData); //tries to load again

                }

        }

        public static void loadBookData (File file) throws IOException //loads all the books from BookData.txt
        {

                try 
                {

                        Scanner sc = new Scanner (file);
                        sc.useDelimiter("@");
                        ArrayList<String> dataText = new ArrayList<String> ();//variable for holding all the raw data
                        while (sc.hasNext())
                        {
                                dataText.add(sc.next()); //adds the next book in the file, separated by '/'
                        }
                        sc.close();

                        System.out.println(dataText);
                        
                        int ID = -1; //initialize the bookID, this variable serves two purposes: 
                        //1.to get the bookID from the text file while scanning it
                        //2.to get the largest bookID, since the system messes up if a book is deleted and kept its own bookIDSpot counter
                        
                        for (int i = 0; i <dataText.size() ; i ++) {



                                Scanner scan = new Scanner(dataText.get(i)); //creates a new scanner for every string inside of the dataText ArrayList
                                scan.useDelimiter("/");//using "/" as the delimiter, the following variables can be obtained:
                                //reading the text as variables
                                
                                ID = scan.nextInt();
                                String bookName = scan.next();
                                String bookAuth = scan.next();
                                double bookSR = scan.nextDouble();
                                String bookCat = scan.next();
                                double bookPrice = scan.nextDouble();
                                String bookISBN = scan.next();

                                Book newBook = new Book(ID, bookName, bookAuth,
                                                bookPrice, bookISBN, bookSR, bookCat);

                                bookArray[bookArraySpot] = newBook; //adds the books using the variables given by the text file
                                bookArraySpot++;
                                
                                scan.close();


                        }
                        bookIDSpot = ID + 1; //after the final book had been added, bookIDSpot can resume its normal activity

                } 
                catch ( java.io.FileNotFoundException e) //if file does not exist
                {

                        FileWriter fw = new FileWriter(BookData);
                        PrintWriter output = new PrintWriter(fw);

                        output.print("1/Ulysses/James Royce/5.0/Novel/13.42/1830481050@2/The Great Gatsby/F.Scott Fitzgerald/4.9/Novel/136.42/7365236535 " +
                                        "@3/Lolita/Vladimir Nabokov/4.8/Novel/52.42/4673727646@4/Catch/Jake Shwitz/4.3/Novel/46.2/4678341050@5/Night/Jimmy Jenkins/2.5/Novel/8.52/6383353850@"); //creates the default book data list
                        output.close( );
                        fw.close( ); 
                        loadBookData (BookData); //tries to load again


                }
        }

        public static void loadCheckoutData (File file) throws IOException //loads all the checked out occurrences from CheckoutData.txt
        {
                try 
                {
                        Scanner sc = new Scanner (file);
                        sc.useDelimiter("@");
                        ArrayList<String> dataText = new ArrayList<String> ();//variable for holding all the raw data
                        while (sc.hasNext())
                        {
                                dataText.add(sc.next()); //adds the next string in the file, separated by '@'
                        }
                        sc.close();

                        System.out.println(dataText);

                        for (int i = 0; i <dataText.size() ; i ++)
                        {
                                Scanner scan = new Scanner (dataText.get(i)); //creates a new scanner for every string inside of the dataText ArrayList
                                //using space as the delimiter, the following variables can be obtained:
                                //reading the text as variables
                                int bookID = scan.nextInt();
                                int stuNum = scan.nextInt();
                                String date = scan.next();

                                int bookIndex = findBook(bookID);
                                Book book = Main.bookArray [bookIndex];

                                book.setOwnedBy(stuNum);
                                book.setCheckoutDate(date);
                                book.SetisTaken (true); 


                                scan.close();
                        }
                } 
                catch ( java.io.FileNotFoundException e) //if file does not exist
                {
                        FileWriter fw = new FileWriter(CheckoutData);
                        PrintWriter output = new PrintWriter(fw);

                        output.print("1 547352 2011/11/11@2 547352 2011/12/12@3 547352 2011/12/13@"); //creates the default checkout data list
                        output.close( );
                        fw.close( ); 
                        loadCheckoutData (CheckoutData); //tries to load again
                }

        }
        public static void loadFineData (File file) throws IOException //loads all the checked out occurrences from CheckoutData.txt
        {
                try 
                {
                        Scanner sc = new Scanner (file);
                        sc.useDelimiter("@");
                        ArrayList<String> dataText = new ArrayList<String> ();//variable for holding all the raw data
                        while (sc.hasNext())
                        {
                                dataText.add(sc.next()); //adds the next string in the file, separated by '@'
                        }
                        sc.close();
                        System.out.println(dataText);

                        for (int i = 0; i <dataText.size() ; i ++)
                        {
                                Scanner scan = new Scanner (dataText.get(i)); //creates a new scanner for every string inside of the dataText ArrayList
                                //using space as the delimiter, the following variables can be obtained:
                                //reading the text as variables
                                int stuNum = scan.nextInt();
                                double studentFine = scan.nextDouble();

                                int stuIndex = findStu (stuNum);
                                stuArray [stuIndex].setFineBalace(studentFine);

                                scan.close();
                        }

                } 
                catch ( java.io.FileNotFoundException e) //if file does not exist
                {
                        FileWriter fw = new FileWriter(FineData);
                        PrintWriter output = new PrintWriter(fw);

                        output.print("356664 3.60@284762 2.8@352637 17.53@"); //creates the default fine data list
                        output.close( );
                        fw.close( ); 
                        loadFineData (FineData); //tries to load again

                }
        }

        //method for finding how much fine a student owes       
        public static double fineOwed (int stuNum)
        {
                int stuIndex = findStu(stuNum);


                Student s = stuArray[stuIndex];
                double fine = s.getFineBalance();
                double roundedfine = round(fine);
                return roundedfine;

        }
        //method for rounding fine values to 2 decimal places
        public static double round(double value) {

                double factor = (double) 100;
                value = value * factor;
                double tmp = Math.round(value);
                return (double) tmp / factor;
        }

        //method for paying the fine
        public static void payFine (int stuNum, double amount) throws IOException
        {
                int stuIndex = findStu(stuNum);
                Student s = stuArray[stuIndex];

                //if the student pays more than what they owe, still their fine balance to $0, but give them change.
                if (amount > s.getFineBalance())
                {
                        s.setFineBalace(0.0);
                }
                else
                {
                        //reduce their fine balance in terms of what they paid
                        s.setFineBalace (s.getFineBalance() - amount);
                }
                deleteStrInFile (FineData, stuNum); //deletes the fine


                if (s.getFineBalance() > 0) //if they still have a fine 
                {
                        FileWriter fw = new FileWriter(FineData, true);
                        PrintWriter output = new PrintWriter(fw);
                        output.print(stuNum + " " + s.getFineBalance() + "@"); //adds back the unpaid fine
                        output.close( );
                        fw.close( ); 
                }

        }




}
