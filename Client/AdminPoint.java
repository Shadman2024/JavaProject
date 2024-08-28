package Client;

import java.util.*;
import java.io.*;
import java.net.*;
import socketWorks.*;

class handleAdmin implements Runnable {
    String identifier;
    SocketWrapper handleAdminWrapper;
    Thread t;

    handleAdmin(String username, String password) {
        this.identifier = username + password;
        t = new Thread(this);
        t.start();
    }
    
    public void run() {
        try {
            handleAdminWrapper = new SocketWrapper(new Socket("127.0.0.1", 2021));
            handleAdminWrapper.write(identifier);
            Scanner ipt = new Scanner(System.in);
            while (true) {
                System.out.println(handleAdminWrapper.read().toString());
                int choice = Integer.parseInt(ipt.nextLine());
                handleAdminWrapper.write(choice);
                if (choice == 1) {
                    System.out.println(handleAdminWrapper.read().toString());
                    String Owner, password;
                    Owner = ipt.nextLine();
                    handleAdminWrapper.write(Owner);
                    System.out.println(handleAdminWrapper.read().toString());
                    password = ipt.nextLine();
                    handleAdminWrapper.write(password);
                    System.out.println(handleAdminWrapper.read().toString());
                    String Name, Price, Zip_code;
                    Double score;
                    Name = ipt.nextLine();
                    handleAdminWrapper.write(Name);
                    System.out.println(handleAdminWrapper.read().toString());
                    Price = ipt.nextLine();
                    handleAdminWrapper.write(Price);
                    System.out.println(handleAdminWrapper.read().toString());
                    Zip_code = ipt.nextLine();
                    handleAdminWrapper.write(Zip_code);
                    System.out.println(handleAdminWrapper.read().toString());
                    score = Double.parseDouble(ipt.nextLine());
                    handleAdminWrapper.write(score);
                    System.out.println(handleAdminWrapper.read().toString());
                    String Line = ipt.nextLine();
                    handleAdminWrapper.write(Line);
                    System.out.println(handleAdminWrapper.read().toString());
                }
                else {
                    handleAdminWrapper.closeConnection();
                    break;
                }
           }
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage()+" at line "+e.getStackTrace()[0].getLineNumber());
        }
    }
}

public class AdminPoint {
    public static void main(String[] args) {
        String mainPassword = "abid";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = input.nextLine();
        System.out.println("Enter your password: ");
        String password = input.nextLine();
        if (password.equals(mainPassword)) {
            new handleAdmin(username, password);
        }
        
    }
}