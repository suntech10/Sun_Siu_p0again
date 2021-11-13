package com.revature;

import java.io.Serializable;

public class Persons implements Serializable {
        // social security number, name, address
        protected String personId;
        protected String name;
        protected String userName;
        protected String passWord;
        protected String address;
        protected String birthDate;

        // Constructors to create a new person with varying amount of information
        Persons() {
            // no info provided
        }

        Persons(String social) {
            this.personId = social;
        }

        Persons(String social, String name) {

            this.personId = social;
            this.name = name;
        }

        Persons(String social, String name, String address) {
            this.personId = social;
            this.name = name;
            this.address = address;
        }

        Persons(String social, String name, String address, String date) {
            this.personId = social;
            this.name = name;
            this.address = address;
            this.birthDate = date;
        }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

