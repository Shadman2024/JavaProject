package Server;

import java.util.*;
import java.io.*;
import java.net.*;
import socketWorks.*;

class serveOwner implements Runnable {
    SocketWrapper serveOwnerwWrapper;
    Restaurant restaurant;
    Thread t;
    serveOwner(SocketWrapper serveOwnerwWrapper,Restaurant restaurant) {
        this.serveOwnerwWrapper = serveOwnerwWrapper;
        this.restaurant=restaurant;
        t=new Thread(this);
        t.start();
    }

    public void run() {
        try {
            while (true) {
                serveOwnerwWrapper.write("""
                        1. Add Food Item
                        2.Exit
                    Enter your choice:
                             """);
                int choice = Integer.parseInt(serveOwnerwWrapper.read().toString());
                if (choice == 1) {
                    serveOwnerwWrapper.write("Enter the category:");
                    String category = serveOwnerwWrapper.read().toString();
                    serveOwnerwWrapper.write("Enter the name of food:");
                    String name = serveOwnerwWrapper.read().toString();
                    serveOwnerwWrapper.write("Enter the price:");
                    double price = Double.parseDouble(serveOwnerwWrapper.read().toString());
                    Food_Item foodItem = new Food_Item(restaurant.getID(), name, category, price);
                    restaurant.Add_to_menu(foodItem);
                    serveOwnerwWrapper.write("Food Item Added!");
                } else {
                    serveOwnerwWrapper.closeConnection();
                    break;
           }
        }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage() +" at line number "+e.getStackTrace()[0].getLineNumber());
        }
        }
    }


public class OwnerApp {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2020);
            Restaurant_System restaurant_system = new Restaurant_System();
            restaurant_system.load_data();
            restaurant_system.load_restaurant_menu();
            while (true) {
                Socket socket = serverSocket.accept();
                SocketWrapper serveOwnerwWrapper = new SocketWrapper(socket);
                String identifier = serveOwnerwWrapper.read().toString();
                String restaurantName = serveOwnerwWrapper.read().toString();
                System.out.println(restaurantName);
                Restaurant restaurant = restaurant_system.Search_Restaurants_by_name(restaurantName);
                System.out.println(restaurant.getName());
                HashMap<String, SocketWrapper> socketMap = new HashMap<>();
                socketMap.put(identifier, serveOwnerwWrapper);
                new serveOwner(serveOwnerwWrapper,restaurant);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage()+" at line number "+e.getStackTrace()[0].getLineNumber());
        }

    }
}