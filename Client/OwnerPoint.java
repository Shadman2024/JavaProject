package Client;

import java.util.*;
import java.io.*;
import java.net.*;
import socketWorks.*;

class handleOwner implements Runnable {
    String identifier;
    SocketWrapper handleOwnerWrapper;
    Thread t;
    String restaurantName;

    handleOwner(String username, String password, String restaurantName) {
        this.identifier = username + password;
        this.restaurantName = restaurantName;
        t = new Thread(this);
        t.start();
    }
    
    public void run() {
        try {
            handleOwnerWrapper = new SocketWrapper("localhost", 2020);
            handleOwnerWrapper.write(identifier);
            handleOwnerWrapper.write(restaurantName);
            while (true) {
                System.out.println(handleOwnerWrapper.read().toString());
                Scanner input = new Scanner(System.in);
                int choice = Integer.parseInt(input.nextLine());
                handleOwnerWrapper.write(choice);
                if (choice == 1) {
                    System.out.println(handleOwnerWrapper.read().toString());
                    String category = input.nextLine();
                    handleOwnerWrapper.write(category);
                    System.out.println(handleOwnerWrapper.read().toString());
                    String name = input.nextLine();
                    handleOwnerWrapper.write(name);
                    System.out.println(handleOwnerWrapper.read().toString());
                    double price = Double.parseDouble(input.nextLine());
                    handleOwnerWrapper.write(price);
                    System.out.println(handleOwnerWrapper.read().toString());
                } else {
                    handleOwnerWrapper.closeConnection();
                    break;
                }
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
    }
}

public class OwnerPoint {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = input.nextLine();
        System.out.println("Enter your password: ");
        String password = input.nextLine();
        HashMap<String, String> restaurantMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Server/login.txt"));
            String Line;
            while((Line = br.readLine()) != null) {
                String[] splitted = Line.split(" ");
                restaurantMap.put(splitted[0], splitted[1]);
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage()+" at line number  "+e.getStackTrace()[0].getLineNumber()); 
        }
        if (restaurantMap.containsKey((username+password)) && restaurantMap.get((username+password))!=null) {
            new handleOwner(username, password,restaurantMap.get((username+password)));
        }
        
    }
}