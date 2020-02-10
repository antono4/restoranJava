/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DELL
 */
public class sessionID {
    private static int userID;
    private static String userName;
    private static int userGrant;
    
    public static void setUserID(int userID) {
        sessionID.userID = userID;
    }
    public static void setUserName(String userName) {
        sessionID.userName = userName;
    }
    public static void setUserGrant(int grant) {
        sessionID.userGrant = grant;
    }
    public static int getUserID() {
        return sessionID.userID;
    }
    public static String getUserName() {
        return sessionID.userName;
    }
    public static int getUserGrant() {
        return sessionID.userGrant;
    }
    public static void logOut() {
        sessionID.userID = 0;
        sessionID.userGrant = 0;
        sessionID.userName = "";
    }
}
