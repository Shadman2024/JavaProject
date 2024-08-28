package Server;

import java.util.*;
import java.net.*;
import socketWorks.*;
import java.io.*;

class serveAdmin implements Runnable {
    SocketWrapper serveAdminWrapper;
    Restaurant_System restaurant_system;
    Thread t;
    serveAdmin(SocketWrapper serveAdminWrapper,Restaurant_System restaurant_system) {
        this.serveAdminWrapper = serveAdminWrapper;
        this.restaurant_system=restaurant_system;
        t=new Thread(this);
        t.start();
    }

    public void run() {
        try {
            while (true) {
                serveAdminWrapper.write("""
                        1. Add a new restaurant
                        2.Exit
                        Enter your choice:""");
                int choice = Integer.parseInt(serveAdminWrapper.read().toString());
                if (choice == 1) {
                    serveAdminWrapper.write("Enter Owner name:");
                    String Owner = serveAdminWrapper.read().toString();
                    System.out.println(Owner);
                    serveAdminWrapper.write("Enter his/her password:");
                    String password = serveAdminWrapper.read().toString();
                    serveAdminWrapper.write("Enter the name of restaurant:");
                    String Name = serveAdminWrapper.read().toString();
                    serveAdminWrapper.write("Enter the price(string in $):");
                    String Price = serveAdminWrapper.read().toString();
                    serveAdminWrapper.write("Enter the zip code:");
                    String Zip_code = serveAdminWrapper.read().toString();
                    serveAdminWrapper.write("Enter score of restaurant(number):");
                    Double score = Double.parseDouble(serveAdminWrapper.read().toString());
                    //adding category
                    serveAdminWrapper.write("Enter category separated by whitespaces:");
                    String Line = serveAdminWrapper.read().toString();
                    String[] splitted = Line.split("\\s+");
                    ArrayList<String> input_array = new ArrayList<>(Arrays.asList(splitted));
                    int ID = restaurant_system.getNewId();
                    Restaurant res = new Restaurant(ID, Name, score, Price, Zip_code, input_array);
                    restaurant_system.Add_restaurant(res);
                    serveAdminWrapper.write("Restaurant Added!");
                    BufferedWriter br = new BufferedWriter(new FileWriter("Server/login.txt", true));
                    br.write(System.lineSeparator());
                    br.write(Owner + password + " " + Name);
                    br.close();

                }
                else {
                    serveAdminWrapper.closeConnection();
                    break;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage()+" at line number "+e.getStackTrace()[0].getLineNumber());
        }
    }
}
public class AdminApp {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2021);
            Restaurant_System restaurant_system = new Restaurant_System();
            restaurant_system.load_data();
            restaurant_system.load_restaurant_menu();
            while (true) {
                Socket socket = serverSocket.accept();
                SocketWrapper serveAdminWrapper = new SocketWrapper(socket);
                String identifier = serveAdminWrapper.read().toString();
                HashMap<String, SocketWrapper> socketMap = new HashMap<>();
                socketMap.put(identifier, serveAdminWrapper);
                System.out.println(identifier);
                new serveAdmin(serveAdminWrapper,restaurant_system);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }

    }
}