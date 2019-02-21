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
import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {

  private static ArrayList<User> users; // An ArrayList of valid users.
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "changeme";

  // Default password given to
  // new users or when we reset a user's password.
  //A no-parameter constructor
  public AccessControl() {
    User user = new User("admin", "root", true);
    users = new ArrayList<User>();
    users.add(user);
    this.currentUser = null;
  } 

  //Report whether a
  // given username/password pair is a valid login
  public static boolean isValidLogin(String username, String password) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        if (users.get(i).isValidLogin(password)) {
          return true;
        }
      }
    }
    return false;
  } 
  //Change the current user's password
  public void changePassword(String newPassword) {
    for (int i = 0; i < users.size(); ++i) {
      if (currentUser.getUsername().equals(users.get(i).getUsername())) {
        users.get(i).setPassword(newPassword);
        System.out.println("change successfully");
      } else {
        System.out.println("not change");
      }
    }
  } 
  //Log out the current user
  public void logout() {
    if (currentUser != null) {
      currentUser = null;
    }
  }
  //A mutator you can use to write tests
  // without simulating user input
  public void setCurrentUser(String username) {
    for (int i = 0; i < users.size(); ++i) {
      if (users.get(i).getUsername().equals(username)) {
        currentUser = users.get(i);
      }
    }
  } 
  //Create a new user
  // With the default password and isAdmin==false
  public boolean addUser(String username) {
    if (currentUser != null && currentUser.getIsAdmin() == true) {//make sure current user is  a admin
      for (int i = 0; i < users.size(); ++i) {
        if (users.get(i).getUsername().equals(username)) {//make sure it is exist or not
          System.out.println("Existed");
          return false;
        }
      }
      users.add(new User(username, "changeme", false));
      System.out.println("add successfully");
      return true;
    }
    return false;
  } 
  //Create a new user
  // and specify their admin status
  public boolean addUser(String username, boolean isAdmin) {
    if (currentUser != null && currentUser.getIsAdmin() == true) {
      for (int i = 0; i < users.size(); ++i) {
        if (users.get(i).getUsername().equals(username)) {//make sure it is exist or not
          System.out.println("Existed");
          return false;
        }
      }
      users.add(new User(username, "changeme", true));
      System.out.println("add successfully");
      return true;
    }


    return false;
  } 
  //Remove a user (names should be unique)
  public boolean removeUser(String username) {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); ++i) {
        if (users.get(i).getUsername().equals(username)) {//make sure it is the right people
          users.remove(i);
          System.out.println("remove successfully");
          return true;
        }
      }
      System.out.println("user does not exist");
      return false;
    }
    return false;
  } 
  //Give a user admin power
  public boolean giveAdmin(String username) {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); ++i) {
        if (users.get(i).getUsername().equals(username)) {//make sure it is the right people
          users.get(i).setIsAdmin(true);
          System.out.println("set successfully");
          return true;
        }
      }
      System.out.println("cannot give");
      return false;
    }
    return false;

  }
  //Remove a user's admin power
  public boolean takeAdmin(String username) {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); ++i) {
        if (users.get(i).getUsername().equals(username)) {//make sure it is the right people
          users.get(i).setIsAdmin(false);
          System.out.println("take away successfully");
          return true;
        }
      }
      System.out.println("cannot take away");
      return false;
    }
    return false;
  } 
  //Reset a user's password
  public boolean resetPassword(String username) {
    if (currentUser != null && currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); ++i) {
        if (users.get(i).getUsername().equals(username)) {// make sure it is the right people
          users.get(i).setPassword(DEFAULT_PASSWORD);
          System.out.println("reset successfully");
          return true;
        }
      }
      System.out.println("cannot reset");
      return false; 
    }
    return false;
  } 
  //Main driver loop.
  // Prompt the user for login information
  // calls the isValidLogin method
  // If the login is valid, call the sessionScreen method
  public void loginScreen(Scanner userInputScanner) {
    boolean login = true;
    while (login) {
      System.out.println(
          "============== Welcome to AccessControl. Please login screen. =================");
      System.out.print("Username: ");
      String usernames = userInputScanner.nextLine();// get input
      System.out.print("Password: ");
      String passwords = userInputScanner.nextLine();
      if (isValidLogin(usernames, passwords)) {
        sessionScreen(usernames, userInputScanner);
      } else {
        System.out.println("Invaild Login");

      }
    }
  }
  //Set the currentUser
  // Allows them to changePassword or logout
  // If they are an admin, gives access to admin methods
  public void sessionScreen(String username, Scanner userInputScanner) {
    setCurrentUser(username);
    boolean finishlogin = true;
    while (finishlogin) {
      System.out.println("==========AccessControl Menus===========");
      String[] command = userInputScanner.nextLine().split(" ");//seperate the input command 
      System.out.print("Commands: ");
      System.out.println("logout");
      System.out.println("newpw[password]");
      System.out.println("adduser[username]");
      System.out.println("adduser[username] [true/false]");
      System.out.println("rmuser[username]");
      System.out.println("giveadmin[username]");
      System.out.println("rmadmin[username]");
      System.out.println("resetpw[username]");
      if (command[0].equals("logout")) {
        logout();
        break;//stop the accesscontrol
      } else if (command[0].equals("newpw") && command[1] != null) {
        changePassword(command[1]);
      } else if (command[0].equals("adduser") && command[1] != null) {
        addUser(command[1]);
      } else if (command[0].equals("adduser") && command[1] != null
          && command[2].trim().equals("true")) {
        addUser(command[1], true);
      } else if (command[0].equals("rmuser") && command[1] != null) {
        removeUser(command[1]);
      } else if (command[0].equals("giveadmin") && command[1] != null) {
        giveAdmin(command[1]);
      } else if (command[0].equals("rmadmin") && command[1] != null) {
        takeAdmin(command[1]);
      } else if (command[0].equals("resetpw") && command[1] != null) {
        resetPassword(command[1]);
      }
    }

  }


  /*
   * Launch an AccessControl instance
   */
  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    // If we have any persistent information to lead
    // this is where we load it.
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  }
}

