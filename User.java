//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AccessControl
// Files:           AccessControl.java; User.java;AccessControlTest.java
// Course:          300,2018,fall
//
// Author:          Ante Du
// Email:           adu3@wisc.edu
// Lecturer's Name: Gary
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class User {

  private final String USERNAME; // The user's name
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers

  //the constructor with initial value
  public User(String username, String password, boolean isAdmin) {
    this.USERNAME = username ;
    this.password = password;
    this.isAdmin = isAdmin;
  }
  // Creates a new user with the given password and admin status
  public boolean isValidLogin(String password) {
    return (this.password.equals(password))  ;
  } // Report whether the password is correct
  public String getUsername() {
    return this.USERNAME ;
  } // Return the user's name
  public boolean getIsAdmin() {
    return this.isAdmin;
  } // Report whether the user is an admin
  public void setPassword(String password) {
    this.password = password;
  } // Set the new password
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  } // Set the new admin status
}
