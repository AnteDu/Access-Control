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
public class AccessControlTest {
  /*
   * Testing main. Runs each test and prints which (if any) failed.
   */
  public static void main(String[] args) {
    int fails = 0;
    if (!testLogin1()) {
      System.out.println("testLogin1 [bad username] failed");
      fails++;
    }
    if (!testLogin2()) {
      System.out.println("testLogin2 [good login] failed");
      fails++;
    }
    if (!testLogin3()) {
      System.out.println("testLogin1 [bad username with default password] failed");
      fails++;
    }
    if(!testAddUser1() ) {
      System.out.println("testAddUser1 [bad user] failed");
      fails++;
    }

    if (!testRemove1()) {
      System.out.println("testRemove1[bad user] failed");
      fails++;
    }
    if(!testAddUser2() ) {
      System.out.println("testAddUser1 [bad user] failed");
      fails++;
    }
    if(!testresetPassword1()) {
      System.out.println("");
      fails++;
    }
    if(fails == 0)
      System.out.println("All tests passed!");
  }
  /*
   * This test tries to log in a user that doesn't exist
   * @return boolean test passed
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl();// It doesn't look like we use ac, but we need users initialized
    String user = "probablyNotInTheSystem1234";
    String pw = "password";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false

  }
  /*
   * This test tries to log in a user that exist as admin
   * @return boolean test passed
   */
  public static boolean testLogin2() {
    String user = "admin";
    String pw = "root";
    return AccessControl.isValidLogin(user, pw);
  }
  /*
   * This test tries to log in a user that doesn't exist with the default password
   * @return boolean test passed
   */
  public static boolean testLogin3() {
    String user = "probablyabcd";
    String pw = "changeme";
    return !AccessControl.isValidLogin(user, pw);

  }
  /*
   * Create a new AccessControl and do not log in an admin.
   * Verify that addUser(String username) returns false
   * and that the new user is not added.
   * @return boolean test passed
   */
  public static boolean testAddUser1() {
    AccessControl ac = new AccessControl();
    String user = "alexi";
    boolean addUserReport = ac.addUser(user);
    if (addUserReport)
      return false; // addUserReport should be false
    // Make sure user wasn't added anyway
    return !AccessControl.isValidLogin(user, "changeme");
  }
  /*
   * Create a new AccessControl and log in an admin.
   * Verify that removeUser(String username) returns false
   * for there is no user that can be removed exist.
   * @return boolean test passed
   */
  public static boolean testRemove1() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    ac.setCurrentUser(user);
    String usera = "alexi";
    boolean removeReport = ac.removeUser(usera);
    if(removeReport)
      return false;
    return !AccessControl.isValidLogin(user, "changeme");
  }
  /*
   * Create a new AccessControl and  log in an admin.
   * Verify that addUser(String username) returns true
   * and that the new user is added.
   * @return boolean test passed
   */
  public static boolean testAddUser2() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    ac.setCurrentUser(user);
    String userm = "alexi";
    boolean addUserReport = ac.addUser(userm);
    if (addUserReport)
      return true; 
    return AccessControl.isValidLogin(user, "changeme");
  }
  /*
   * Create a new AccessControl and log in an admin.
   * Verify that resetPassword(String username) returns true
   * for there is a user that can be reset password exist.
   * @return boolean test passed
   */
  public static boolean testresetPassword1() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    ac.setCurrentUser(user);
    String userd = "alexi";
    ac.addUser(userd);
    boolean resetReport = ac.resetPassword(userd);
    if(resetReport)
      return true;
    return AccessControl.isValidLogin(user, "changeme");
  }
}
