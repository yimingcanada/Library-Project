import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TabbedPane extends JPanel implements ActionListener {


        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        //initialization of buttons and text fields so it can be attached to ActionPerformed
        private JTextField studTextField;
        private JButton studSrchBttn;
        private JRadioButton rdStudName ;
        private JRadioButton rdStudentNumber;


        private JTextField bookTextField;
        private JButton bookSrchBttn;

        private JRadioButton rdBkName;
        private JRadioButton rdBkCat;
        private JRadioButton rdBkAuth;
        private JRadioButton rdBkID;

        private JTextField addStuFNameFd ;
        private JTextField addStuLNameFd ;
        private JTextField      addStuNumFd;
        private JButton addStuButtn;

        private JTextField addBkNameFd;
        private JTextField addBkAuthFd;
        private JTextField addBkCatFd;
        private JTextField addBkSRFd;
        private JTextField addBkPrFd;
        private JTextField addISBN;
        private JButton addBkButtn;

        private JTextField delStuTextField;
        private JButton delStuBttn ;

        private JTextField delBkTextField;
        private JButton delBkBttn;

        private JTextField dateTextField;
        private JButton chkoutBttn;
        private JTextField checkoutReturnBkID;
        private JButton bkLostBttn;
        private JTextField studNum;
        private JButton rtrnBttn;


        private JTextField stuNumTextField;
        private JButton getFine;
        private JTextField fineTextField;
        private JTextField finePayTextField;
        private JButton payFine;
        private JTextField changeTextField;

        private JFrame frame;
        private JButton btnLogOut;
        
        public TabbedPane() {

                JTabbedPane tabbedPane = new JTabbedPane();

                setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));

                {//tab1, search student UI
                        JComponent panel1a = makeTextPanel ("How would you like to search for students?");
                        JComponent panel1b = makeTextPanel ("");

                        ButtonGroup studentRadioGroup = new ButtonGroup ();

                        rdStudName = new JRadioButton("Name");
                        panel1b.add(rdStudName);
                        studentRadioGroup.add(rdStudName);              

                        rdStudentNumber = new JRadioButton("Student Number");
                        panel1b.add(rdStudentNumber);
                        studentRadioGroup.add(rdStudentNumber);

                        studTextField = new JTextField();
                        studTextField.setColumns(10);
                        panel1b.add(studTextField);

                        studSrchBttn = new JButton ("Search");

                        panel1b.add(studSrchBttn);
                        studSrchBttn.addActionListener(this);

                        JPanel panel1 = new JPanel () ;

                        panel1.setLayout(new BoxLayout (panel1, BoxLayout.Y_AXIS));

                        panel1.add(panel1a);
                        panel1.add(panel1b);

                        tabbedPane.addTab("Search Students",  panel1);


                }

                {//tab2, search book UI
                        JComponent panel2a = makeTextPanel("How would you like to search for books?");
                        ButtonGroup bookRadioGroup = new ButtonGroup ();
                        JComponent panel2b = makeTextPanel("");
                        rdBkName = new JRadioButton("Title");
                        panel2b.add(rdBkName);
                        bookRadioGroup.add(rdBkName);           

                        rdBkCat = new JRadioButton("Category");
                        panel2b.add(rdBkCat);
                        bookRadioGroup.add(rdBkCat);

                        rdBkAuth = new JRadioButton ("Author");
                        panel2b.add(rdBkAuth);
                        bookRadioGroup.add(rdBkAuth);

                        rdBkID = new JRadioButton ("ID");
                        panel2b.add(rdBkID);
                        bookRadioGroup.add(rdBkID);

                        bookTextField = new JTextField();
                        bookTextField.setColumns(10);
                        panel2b.add(bookTextField);

                        bookSrchBttn = new JButton ("Search");
                        panel2b.add(bookSrchBttn);
                        bookSrchBttn.addActionListener(this);


                        JPanel panel2 = new JPanel () ;
                        panel2.add(panel2a);
                        panel2.add(panel2b);
                        panel2.setLayout(new BoxLayout (panel2, BoxLayout.Y_AXIS));
                        tabbedPane.addTab("Search for Books", panel2);

                }

                {//tab3, Adding a Student

                        JComponent panel3a=makeTextPanel("    First Name                         Last Name             " +
                                        "           Student Number");
                        JComponent panel3b= makeTextPanel("Add a Student");


                        addStuFNameFd = new JTextField("",10);
                        addStuLNameFd = new JTextField("",10);
                        addStuNumFd = new JTextField("",10);


                        addStuButtn = new JButton ("Add");
                        addStuButtn.addActionListener(this);

                        panel3b.add(addStuFNameFd);
                        panel3b.add(addStuLNameFd);
                        panel3b.add(addStuNumFd);
                        panel3b.add(addStuButtn);
                        JPanel panel3 = new JPanel();
                        panel3.add(panel3a);
                        panel3.add(panel3b);

                        tabbedPane.addTab("Add Student", panel3);

                }


                {//tab4, Adding a book
                        JComponent panel4a = makeTextPanel("Add a Book to the system");


                        addBkNameFd = new JTextField("",9);
                        addBkAuthFd = new JTextField("",9);
                        addBkCatFd = new JTextField("",9);
                        addBkSRFd = new JTextField("",6);
                        addBkPrFd = new JTextField("",6);
                        addISBN = new JTextField("",10);

                        addBkButtn = new JButton ("Add");
                        addBkButtn.addActionListener(this);

                        JComponent panel4b = makeTextPanel ("");
                        panel4b.add(addBkNameFd);
                        panel4b.add(addBkAuthFd);
                        panel4b.add(addBkCatFd);
                        panel4b.add(addBkSRFd);
                        panel4b.add(addBkPrFd);
                        panel4b.add(addISBN);
                        panel4b.add(addBkButtn);

                        JComponent panel4instr = makeTextPanel ("");
                        JComponent panel4c = makeTextPanel ("Book name");
                        JComponent panel4d = makeTextPanel ("Book author");
                        JComponent panel4e = makeTextPanel ("Book category");
                        JComponent panel4f = makeTextPanel ("Book star rating");
                        JComponent panel4g = makeTextPanel ("Book price");
                        JComponent panel4h = makeTextPanel ("ISBN");


                        panel4instr.add(panel4c);
                        panel4instr.add(panel4d);
                        panel4instr.add(panel4e);
                        panel4instr.add(panel4f);
                        panel4instr.add(panel4g);
                        panel4instr.add(panel4h);

                        panel4instr.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 1));
                        JPanel panel4 = new JPanel ();

                        panel4.add(panel4a);
                        panel4.add(panel4instr);
                        panel4.add(panel4b);
                        panel4.setLayout(new BoxLayout (panel4, BoxLayout.Y_AXIS));
                        tabbedPane.addTab("Add Book", panel4);

                }

                { //tab5, Deleting a Student
                        JComponent panel5 = makeTextPanel("Delete a Student by their student number");

                        delStuTextField = new JTextField ("",10);
                        delStuBttn = new JButton ("Delete");


                        panel5.add(delStuTextField);
                        panel5.add(delStuBttn);
                        tabbedPane.addTab("Delete a Student", panel5);
                        delStuBttn.addActionListener(this);

                }

                {//tab6, Deleting a Book
                        JComponent panel6 = makeTextPanel("Delete a Book by the Book ID");
                        delBkTextField = new JTextField ("",10);
                        delBkBttn = new JButton ("Delete");

                        panel6.add(delBkTextField);
                        panel6.add(delBkBttn);
                        tabbedPane.addTab("Delete a Book", panel6);
                        delBkBttn.addActionListener(this);

                }


                {//tab7, checking out and return tab
                        JComponent panel7a = makeTextPanel("Today's date:");
                        dateTextField = new JTextField ("",10);
                        dateTextField.setText("YYYY/MM/DD");

                        panel7a.add(dateTextField);

                        JComponent panel7b = makeTextPanel("Book ID:");
                        checkoutReturnBkID = new JTextField ("",10);
                        panel7b.add(checkoutReturnBkID);

                        JComponent panel7c = makeTextPanel("Student number:");
                        studNum = new JTextField ("",10);
                        panel7c.add(studNum);


                        chkoutBttn = new JButton ("Checkout");
                        rtrnBttn = new JButton ("Return");
                        bkLostBttn = new JButton ("Book lost");

                        JComponent panel7d = makeTextPanel("");
                        panel7d.add(chkoutBttn);
                        panel7d.add(rtrnBttn);
                        panel7d.add(bkLostBttn);


                        JPanel panel6 = new JPanel();
                        panel6.add(panel7a);
                        panel6.add(panel7b);
                        panel6.add(panel7c);
                        panel6.add(panel7d);


                        panel6.setLayout(new BoxLayout (panel6, BoxLayout.Y_AXIS));
                        tabbedPane.addTab("Checkout/Return", panel6);

                        chkoutBttn.addActionListener(this);
                        rtrnBttn.addActionListener(this);
                        bkLostBttn.addActionListener(this);

                }

                {//tab 8, payment tab

                        JPanel panel8 = new JPanel ();

                        JComponent panel8a = makeTextPanel("Student number:");
                        stuNumTextField = new JTextField ("",10);
                        getFine = new JButton ("Get Fine Owed");

                        panel8a.add(stuNumTextField);
                        panel8a.add (getFine);

                        JComponent panel8b = makeTextPanel("Fine:");
                        fineTextField = new JTextField ("",5);
                        panel8b.add(fineTextField);


                        JComponent panel8a1 = makeTextPanel("Type in the amount they would like to pay ($):");
                        finePayTextField = new JTextField ("",5);
                        payFine = new JButton ("Pay Fine ");

                        panel8a1 .add(finePayTextField);
                        panel8a1.add(payFine);

                        JComponent panel8a2 = makeTextPanel("Change:"); 
                        changeTextField = new JTextField ("",5);
                        panel8a2.add(changeTextField);

                        panel8.add(panel8a);
                        panel8.add (panel8b);
                        panel8.add (panel8a1);
                        panel8.add(panel8a2);
                        panel8.setLayout(new BoxLayout (panel8, BoxLayout.Y_AXIS));

                        tabbedPane.addTab("Pay Fine", panel8);

                        getFine.addActionListener(this);
                        payFine.addActionListener(this);


                }

                //Add the tabbed pane to this panel.
                add(tabbedPane);

                //The following line enables to use scrolling tabs.
                tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

                btnLogOut = new JButton("Log Out");
                add(btnLogOut);
                btnLogOut.addActionListener(this);
                tabbedPane.setPreferredSize(new Dimension(950,200));

        }


        //taken from example http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
        public static JPanel panel; 
        static JComponent makeTextPanel(String text) 
        { //creates a JPanel
                panel = new JPanel(false);
                JLabel filler = new JLabel(text);
                filler.setHorizontalAlignment(JLabel.CENTER);
                FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 20, 0);
                panel.setLayout(fl_panel);
                panel.add(filler);
                return panel;
        }





        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from
         * the event dispatch thread.
         */


        JFrame makeStudentSearchWin (String text, String search, boolean isStr) 

        {


                JFrame frame = new JFrame(text);



                frame.setContentPane(new StudentSearchWindow(search, isStr));
                //Display the window.
                frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1020, 400);
                return frame;
        }

        JFrame makeBookSearchWin (String text, String search, int searchMode) 

        {


                JFrame frame = new JFrame(text);



                frame.setContentPane(new BookSearchWindow(search, searchMode ));
                //Display the window.
                frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1020, 400);
                return frame;
        }

        public void  actionPerformed(ActionEvent e)  {//all the actions which the buttons perform
                if (e.getSource() == studSrchBttn)
                {
                        String input = studTextField.getText();

                        if (input.equalsIgnoreCase(""))
                        {
                                
                                JOptionPane.showMessageDialog(frame,
                                           "You must search for a student",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                
                        }
                        else
                        {
                                if (rdStudentNumber.isSelected())
                                {
                                	//makes pop up search windows according to user input
                                        JFrame stuSearchWindow = makeStudentSearchWin ("Student number search results",input,  false );
                                } 
                                else if (rdStudName.isSelected()) 
                                {
                                        System.out.println("Search by student name");
                                        JFrame stuSearchWindow = makeStudentSearchWin ("Student name search results",input,  true);
                                }
                        }
                }
                if (e.getSource() == bookSrchBttn)
                {
                        String input = bookTextField.getText();
                        
                        if (input.equals("")) //if input is nothing, show error
                        {
                                
                                JOptionPane.showMessageDialog(frame,
                                           "You must search for a book",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        
                        else
                        {

                                if (rdBkName.isSelected()) 
                                {
                                        JFrame bookSearchWindow = makeBookSearchWin ("Book Title search results", input, 1);
                                }
                                else if (rdBkAuth.isSelected())
                                {
                                        JFrame bookSearchWindow = makeBookSearchWin ("Book Author search results", input, 2);
                                }
                                else if (rdBkCat.isSelected())
                                {
                                        JFrame bookSearchWindow = makeBookSearchWin ("Book Category search results", input, 3);
                                }
                                else if (rdBkID.isSelected())
                                {
                                        JFrame bookSearchWindow = makeBookSearchWin ("Book ID search results", input, 4);
                                }
                        }
                }
                if (e.getSource() == addStuButtn)
                {
                      
                        String fName = addStuFNameFd.getText();
                        String lName = addStuLNameFd.getText(); 
                        String stringStuNum = addStuNumFd.getText();
                
                        //error message occurs if user did not enter anything
                        if (fName.length() == 0 || lName.length() == 0 || stringStuNum.length() == 0 )
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "One or more of the following errors below have occured:\n" + "1. You have not entered a first name\n" + "2. You have not entered a last name\n" + "3. You have not entered a student number",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                                int      stuNum = Integer.parseInt(addStuNumFd.getText());

                                boolean isAdded;
                                try {
                                        isAdded = Main.addStu(stuNum,fName,lName);
                                        if(     isAdded== true)
                                        {
                                                JOptionPane.showMessageDialog(frame,                                                    
                                                                fName + " " +lName + " has been successfully added." );

                                        }
                                        else
                                        {JOptionPane.showMessageDialog(frame,
                                                        "One or more of the following errors below have occured:\n" + "1. You have entered a student number that already exists\n" + "2. You have entered an invalid student number (Student numbers must be greater than 0)", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        }

                                        addStuFNameFd.setText("");
                                        addStuLNameFd.setText("");
                                        addStuNumFd.setText("");

                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }
                        }

                }

                if (e.getSource() == addBkButtn)
                {
                        int bkID = Main.bookIDSpot; // gets the bookID from the main
                        String bkName = addBkNameFd.getText();
                        String bkAuthor = addBkAuthFd.getText();
                        String bkCat = addBkCatFd.getText();
                        String bkISBN = addISBN.getText();
                        String stringStarRating = addBkSRFd.getText(); 
                        String stringBookPrice = addBkPrFd.getText();
                        
                        //error message occurs if user did not enter anything
                        if (bkName.length() == 0 || bkAuthor.length() == 0 || bkCat.length() == 0 || bkISBN.length() == 0 || stringStarRating.length() == 0 ||stringBookPrice.length() == 0  )
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "One or more of the following errors below have occured:\n" + "1. You have not entered a book name\n" + "2. You have not entered a book author\n" + "3. You have not entered a book category\n" + "4. You have not entered a book ISBN\n" + "5. You have not entered a book star rating\n" + "6. You have not entered a book price",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        
                        else
                        {

                                double starRating =  Double.parseDouble(stringStarRating);
                                double bookPrice =  Double.parseDouble(stringBookPrice);



                                boolean isAdded;
                                try {
                                        isAdded = Main.addBook(bkID,bkName, bkAuthor, bookPrice, bkISBN, starRating, bkCat);
                                        

                                        if(     isAdded== true) //if the book is successfully added
                                        {
                                                JOptionPane.showMessageDialog(frame,                                                    
                                                                bkName + " by " +bkAuthor + " has been successfully added." );
                                        }

                                        else // if it's not, display erorr
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "One or more of the following errors below have occured:\n" +"1. Star ratings must be between 0.0 and 5.0\n" + "2. Book prices must be above $ 0.00",
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                        //resets textfield to nothing
                                        addBkNameFd.setText("");
                                        addBkAuthFd.setText("");
                                        addBkCatFd.setText("");
                                        addISBN.setText("");
                                        addBkSRFd.setText("");
                                        addBkPrFd.setText("");  

                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                        }


                }
                if (e.getSource() == delStuBttn)
                {
                        String stringStuNum = delStuTextField.getText();
                        
                        //error message occurs if user did not enter anything
                        if (stringStuNum.length() == 0)
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "Please type in a student number",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        
                        else {

                                int stuNum = Integer.parseInt(stringStuNum);

                                boolean isDeleted;
                                try {
                                        isDeleted = Main.deleteStu  (stuNum);
                                        if(     isDeleted== true)
                                        {
                                                JOptionPane.showMessageDialog(frame,                                                    
                                                                "Student number " +stuNum + " has been successfully deleted." );

                                        }
                                        else
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "One or more of the following errors below have occured:\n" +"1. You have entered a student number that does not exist\n" + "2. You have entered a student number that still owes a fine\n" + "3. You have entered a student number that still has a book checked out",
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }

                                        delStuTextField.setText("");    

                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                        }

                }

                if (e.getSource() == delBkBttn)
                {
                        String stringBookNum = delBkTextField.getText();
                        //error message occurs if user did not enter anything
                        if (stringBookNum.length() == 0)
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "Please type in a Book ID",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }

                        else
                        {

                                int bookNum = Integer.parseInt(stringBookNum);

                                boolean isDeleted;
                                try 
                                {
                                        isDeleted = Main.deleteBook(bookNum);
                                        if(     isDeleted== true)
                                        {
                                                JOptionPane.showMessageDialog(frame,                                                    
                                                                "book ID " + bookNum + " has been successfully deleted." );

                                        }
                                        else
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "One or more of the following errors below have occured:\n" +"1. You have entered a book ID that does not exist\n" + "2. You have entered a book ID that has been checked out\n",
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                        delBkTextField.setText("");     
                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }
                        }

                }


                if(e.getSource () == chkoutBttn)
                {
                        String date = dateTextField.getText();
                        String stringBookID = checkoutReturnBkID.getText();
                        String stringStuNum = studNum.getText ();
                        
                        //error message occurs if user did not enter anything
                        if (date.equals("YYYY/MM/DD") || stringBookID.length() == 0 || stringStuNum.length() == 0)
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "One or more of the following errors below have occured:\n" + "1. You have not entered a checkout date\n" + "2. You have not entered a book ID\n" + "3. You have not entered a student number\n" ,
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                
                        }
                        else
                        {
                                int bkID = Integer.parseInt(stringBookID);                      
                                int stuNum = Integer.parseInt(studNum.getText ());

                                boolean isCheckedOut;
                                try {
                                        isCheckedOut = Main.checkOut(stuNum, bkID, date);
                                        if(     isCheckedOut== true) // if the book was successfully checked out
                                        {       
                                                JOptionPane.showMessageDialog(frame,
                                                                "Student " + stuNum + " has successfully checked out " + "book ID " + bkID);
                                        }
                                        
                                        else// if failed to check out
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "One or more of the following errors below have occured:\n" + "1. The book ID you have entered does not exist\n" + "2. The student number you have entered does not exist\n" + "3. The book has already been checked out\n" + "4. The student already has 3 books checked out\n" + "5. The student has fines over $5.00" ,
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }

                                        dateTextField.setText("YYYY/MM/DD");
                                        checkoutReturnBkID.setText("");
                                        studNum.setText("");
                                        
                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                                
                                
                        }
                }

                if(e.getSource () == rtrnBttn)
                {
                        String date =  dateTextField.getText();
                        String stringBkID = checkoutReturnBkID.getText();
                        
                        //error message occurs if user did not enter anything
                        if (date.equals("YYYY/MM/DD") || stringBkID.length() == 0 )
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "One or more of the following errors below have occured:\n" + "1. You have not entered a return date\n" + "2. You have not entered a book ID."  ,
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        else{

                                int bkID = Integer.parseInt(stringBkID);

                                boolean isReturned;
                                try {
                                        isReturned = Main.returnBook(bkID, date);
                                        if(     isReturned== true)
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "Book " + bkID + " has been successfully returned.");

                                        }
                                        else
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "One or more of the following errors below have occured:\n" + "1. You have entered a book ID that does not exist\n" + "2. The book you wish to return has not been checked out." + "3. You have entered an invalid return date" ,
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }

                                        dateTextField.setText("YYYY/MM/DD");                            
                                        checkoutReturnBkID.setText("");
                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                                
                        }
                }

                if(e.getSource () == bkLostBttn)
                {
                        String stringBkID = checkoutReturnBkID.getText();
                        
                        //error message occurs if user did not enter anything
                        if ( stringBkID.length() == 0 )
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "You have not entered a book ID", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        
                        else {


                                int bkID = Integer.parseInt(stringBkID);
                                boolean bookIsLost;
                                try {
                                        bookIsLost = Main.bookLost(bkID);
                                        if (bookIsLost == true)
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "book " + bkID + " is lost." + "The price of the book has been added to the student's total fine");
                                        }
                                        else
                                        {
                                                JOptionPane.showMessageDialog(frame,
                                                                "One or more of the following errors below have occured:\n" + "1. You have entered a book ID that does not exist\n" + "2. The book has not been checked out."  ,
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                        }

                                        checkoutReturnBkID.setText("");

                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }
                        }


                }

                if(e.getSource ()== getFine)
                {       
                        String stringStuNum = stuNumTextField.getText ();
                        
                        //error message occurs if user did not enter anything
                        if ( stringStuNum.length() == 0 )
                        {
                                JOptionPane.showMessageDialog(frame,
                                            "You have not entered a student number", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        
                        else
                        {

                                int stuNum = Integer.parseInt(stringStuNum);
                                if(stuNum < 1 || Main.findStu(stuNum) == -1 )
                                {
                                        JOptionPane.showMessageDialog(frame,
                                                        "Please enter a valid student number.", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                        fineTextField.setText("$ " + Main.fineOwed(stuNum));
                                }

                        }
                                        
                }

                if(e.getSource () == payFine)
                {               
                        String stringStuNum = stuNumTextField.getText ();
                        String stringFinePayed = finePayTextField.getText ();
                        
                        //error message occurs if user did not enter anything
                        if ( stringStuNum.length() == 0 || stringFinePayed.length() == 0 )
                                        
                        {
                                JOptionPane.showMessageDialog(frame,
                                                "One or more of the following errors below have occured:\n" + "1. You have not entered a student number\n" + "2. You have not entered how much fine you would like to pay off", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {

                                int stuNum = Integer.parseInt(stringStuNum);
                                double finePayed = Double.parseDouble(stringFinePayed);
                                double fineOwed =  Main.fineOwed(stuNum);
                                try {
                                        Main.payFine (stuNum, finePayed);
                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                                if (finePayed > fineOwed)
                                {

                                        double changeDisplay = Main.round((finePayed - fineOwed));
                                        changeTextField.setText("$ " +changeDisplay);
                                        fineTextField.setText( "$ " + Main.fineOwed(stuNum));
                                        finePayTextField.setText ("");
                                }
                                else
                                {
                                        double newFine = Main.round(fineOwed - finePayed);
                                        fineTextField.setText ("$ " + newFine);
                                        finePayTextField.setText ("");
                                }               
                        }
                        
                }

                if (e.getSource()== btnLogOut)
                {
                        JOptionPane.showMessageDialog(frame,"HA NICE TRY", "You weren't logged in to begin with", JOptionPane.ERROR_MESSAGE);
                }

        }


}

