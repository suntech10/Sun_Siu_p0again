package com.revature;

import com.revature.Accounts;import com.revature.Persons;

import java.util.Scanner;


public class GetUserInput {
    // Solicit user for desired login info
    // return whether both user_name and password were got

    public boolean getUserInputAndSet(Scanner sc, String s1, String s2,
                                      Accounts acc, Persons ps, boolean usernamepassword) {
        boolean gotUserName = false;
        boolean gotPassWord = false;

        String userName = "";
        System.out.println("Input " + s1 + " :");
        if (sc.hasNext()) {
            userName = sc.next();
            //System.out.println("You want " + s1 + " :" + userName);
            sc.nextLine();
            gotUserName = true;
        }

        String passWord = "";
        System.out.println("Input " + s2 + " :");
        if (sc.hasNext()) {
            passWord = sc.next();
            //System.out.println("You want " + s2 + " :" + passWord);
            sc.nextLine();
            gotPassWord = true;
        }

        if (gotUserName && gotPassWord) {
            if (usernamepassword) {
                acc.setUserLogin(userName, passWord);
            } else {
                ps.name = userName;
                ps.personId = passWord;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean getUserInputNameAndSocial(Scanner sc) {
        boolean gotSocial = false;
        boolean gotName = false;

        String token;
        String name = "";
        System.out.println("Enter your name: ");
        if (sc.hasNext()) {
            token = sc.next();
            name = token + sc.nextLine();
            System.out.println("Your name is: " + name);
            gotName = true;
        }

        int social = 0;
        System.out.println("Enter your SS#: ");
        if (sc.hasNext()) {
            token = sc.next();
            if (token.length() != 9) {
                System.out.println("incorrect SS# length");
                return gotSocial = false;
            }
            System.out.println("Your SS# is: " + token);
            sc.nextLine();
            gotSocial = true;
        }

        if (gotName && gotSocial) {
            return true;
        } else {
            return false;
        }

    }

    public Persons getUserLogin(Scanner sc) {
        boolean gotPassWord = false;
        boolean gotLoginName = false;


        String token = "";
        String name = "";
        System.out.println("Enter your login name: ");
        if (sc.hasNext()) {
            token = sc.next();
            name = token + sc.nextLine();
            gotLoginName = true;
        }

        System.out.println("Enter your password: ");
        if (sc.hasNext()) {
            token = sc.next();
            sc.nextLine();
            gotPassWord = true;
        }

        if (gotLoginName && gotPassWord) {
            Persons ps = new Persons();
            ps.setUserName(name);
            ps.setPassWord(token);
            return ps;
        } else {
            return null;
        }

    }
}
