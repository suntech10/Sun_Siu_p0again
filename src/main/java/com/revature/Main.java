package com.revature;
 
import com.revature.GetUserInput;
import com.revature.*;

import java.sql.*;
import java.util.Scanner;
import java.lang.String;
// import java.util.regex.PatternSyntaxException;
import java.lang.Math;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//            import java.util.List;


public class Main {

    public static void main(String[] args) {
                // This is an application for starting up and running a basic credit union for a month of salary paid to employees

        // The interactions with it through the displayed menu are:
        //    0. Enter zero to exist this application
        //    1. Register a new user to open an account
        //    5. User check account balance
        //    7. Teller makes deposit into an account for a depositor

        // Classes: Persons have name, social security number, address, birth date

        //  Loop forever unless an arithmetic exception happens when user enters 0 to exit this application
        Scanner sc = new Scanner(System.in);
        int menuItem = 0;
        double bal = 0.0;
        // System.out.println("menuItem is " + menuItem);
        do {
            //    Try to do the menu item user has specified
            prMenu();
            try {
                System.out.println("Choose the menu item number");
                if (sc.hasNext()) {
                    menuItem = sc.nextInt();
                }
                //      Cause an arithmetic exception if user has entered 0 to exit
                int ratio = 1 / menuItem;
                // System.out.println("ratio of 1 to mendepositeduItem is " + ratio);

                //      Check the validity of user input menu item
                if (menuItem > 10 || menuItem < 0) {
                    System.out.println("Please enter choice 0 to 10");
                    if (menuItem == 10) prMenu();
                } else {
                    //      If validity check passes for user input menu item
                    //        Switch based on user input menu item
                    System.out.println("You chose " + menuItem);

                    switch (menuItem) {
                        // Do the user input menu item
                        //    1. Register a new Customer useraccount with 0 balance if given name and SS#
                        // Solicit if possible: info such as address, etc. had been given

                        case 1:
                            System.out.println("Register a new customer user account with the system");
                            // Set up a login with username and password
                            GetUserInput gUserInp;
                            gUserInp = new GetUserInput();
                            Persons ps = new Persons();
                            Accounts acc = new Accounts(0.0); // 0 balance new account

                            if (gUserInp.getUserInputAndSet(sc, "user_name", "password", acc, ps,
                                    true)) { // if get login info succeeded
                                GetUserInput gu = new GetUserInput();
                                if (gu.getUserInputAndSet(sc, "Name", "SS#", acc, ps,
                                        false)) { // if get Name info succeeded
                                    acc.setAccNum( ((int) ( (Math.random() * 9000.0) ) + 100) ); // random generated account number

                                }
                            }

                            System.out.println();

                            UserDao ud = new UserDao(acc);
                            String execStr = "insert into \"Accounts\" (acc_id, balance, user_name, pass_word) values ( 1009, 0.2, 'tom', 'jo')";
                            execStr = "insert into \"Accounts\" (acc_id, balance, user_name, pass_word) values ( " + acc.getAccNum() + ", "
                                    + acc.getBalance() + ", '" + acc.getUserLoginName() + "', '" + acc.getUserLoginPassword() + "')";
                            ud.create(acc, execStr);
                            break;

                        case 5:
                            //    5. Login with my existing credentials
                            ud=null; acc = null;
                            ud = new UserDao(acc);
                            GetUserInput gui = new GetUserInput();
                            ps = gui.getUserLogin(sc);

                            if ( ud.getBalance(ps) == 1.0){
                                System.out.println("Your balance is " + acc.getBalance()   );
                            }

                            break;
                        case 7:
                            System.out.println("Deposit into an existing account");

                            System.out.println("Enter account number");
                            int accNum;
                            if (sc.hasNext()) {
                                accNum = sc.nextInt();

                            }
                            else accNum = 1000;

                            double depAmt = 0.0;
                            System.out.println("Enter desired amount to deposit");
                            if (sc.hasNext()) {
                                depAmt = sc.nextDouble();
                            }

                            int found = 0;
                            if ( found == -1) { // cannot find account
                                System.out.println("cannot find account with number " + accNum);
                                System.out.println("please verify the account number and try again");
                                break;
                            }

                            if (bal + depAmt < 0) {
                                System.out.println("Withdrawal would overdraw account, try a different amount");
                                break;
                            } else {
                                bal = bal + depAmt;

                                System.out.println("Deposited " + depAmt + " into account " + accNum + " so its new balance is " + bal);
                                String s = "update \"Accounts\" set balance = " + bal + "where acc_id = " + accNum;
                                System.out.println(s);
                                Connection conn = ConnectionService.getInstance();
                                PreparedStatement stmt = conn.prepareStatement(s);
                                stmt.executeUpdate();
                                System.out.println("Press 0 to exit or 1 to 10 to continue");
                            }
                            break;
                        } // End Switch
                    }   //    End Try
                    //    Catch the arithmetic exception
                } catch (ArithmeticException e) {
                //      Release Scanner I/O resource

            sc.close();
            //      Break out of the forever loop to exit this application

            System.out.println("You entered 0 to exit so");


                break;
        } catch (NullPointerException ne) {
                //      Release Scanner I/O resource
                sc.close();
                System.out.println("Account not found.  Please choose option 4 or 5 to open an account first on retry.");
                break;
            } catch (IllegalStateException ise) {
                //      Release Scanner I/O resource
                sc.close();
                System.out.println("Java environment or application is not in an appropriate state for the requested operation. Try again,");
                break;
            } catch (SQLException sqle) {
                //      Release Scanner I/O resource
                sc.close();
                sqle.printStackTrace();
                System.out.println("SQL Exception occurred");
                break;
            }

            } while (true) ; //  End Loop forever
            System.out.println("Have a nice day.");

        }

    public static void prMenu() {
        System.out.println("Enter a number shown below to do");
        System.out.println("0 - Exit this application");
        System.out.println("1 - Register a new Customer user_account");
        // System.out.println("1 - founder Opens an account for the credit union's initial capital");

        System.out.println("5 - Login with my existing credentials");
        // System.out.println("6 - Founder hires a manager employee who can approve loans and deposits");
         System.out.println("7 - Teller makes a deposit into a current account for a depositor");
        //System.out.println("8 - Manager makes a loan from a current account to a borrower");
        // System.out.println("9 - Prints daily total balance");
        System.out.println("10 - Prints this menu");
    }
}


    class Customers1 extends Persons {
        // List of accounts
        VanquishList aryAcc;
    }// List of accounts VanquishList<Accounts> aryAcc; }

    class Borrowers1 extends Customers1 {
        // borrower has creditworthiness and reasons contributing to creditworthiness such as money laundering records so here is the note for capturing these for loan managers' examination
        String note;
        Employees1 loanManager; // the manager who primarily handles this borrower's business
    }

    class Depositors1 extends Customers1 {
        // depositor had depositworthiness and reasons contributing to to
        // depositworthiness such as having used fake checks before so here is the
        // note for capturing these for tellers' examination
        String note;
        String marPromotions; // records marketing promotion constraints imposed on the depositor's list of accounts}
    }

    class Employees1 extends Persons {

        // next identification number to be given to the new employee
        static int identNext = 0;

        // identification
        private int id;

        // paygrade ( Tellers=0, Managers=1..)
        private int paygrade;

        // monthly salary
        private double salary;

        public void setId(int i) {
            this.id = i;
            identNext++; // bump up identification number to be given to the next new employee
        }

        public void setPayGrade(int paygrade) {
            this.paygrade = paygrade;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public int getId() {
            return this.id;
        }

        public int getPayGrade() {
            return this.paygrade;
        }

        public double getSalary() {
            return this.salary;
        }
    }

    class Services {

        public static String getLine(Scanner sc) {

            String sBuf;
            sBuf = new String();
            String s;
            while (sc.hasNext()) {
                System.out.println(s = sc.next());
                System.out.println(sBuf = sBuf.concat(" ").concat(s));

                System.out.println(s = sc.nextLine());
                if (s.length() != 0) {
                    System.out.println(sBuf = sBuf.concat(s));
                    break;
                }
            }
            return sBuf;
        }

    }

