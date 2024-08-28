package Client;

import socketWorks.SocketWrapper;
import Server.*;
import java.util.*;

/**
 * handleClient
 */ 
class handleClient implements Runnable {
    SocketWrapper clienSocketWrapper;
    Thread t;
    String identifier;

    handleClient(String username, String password) {
        identifier = username+password;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            Scanner input= new Scanner(System.in);
            clienSocketWrapper = new SocketWrapper("127.0.0.1",2024);
            clienSocketWrapper.write(identifier);
            while (true) {
                System.out.println( clienSocketWrapper.read().toString());
                int choice = Integer.parseInt(input.nextLine());
                clienSocketWrapper.write(choice);
                switch (choice) {
                    case 1:
                        while (true) {
                            System.out.println(clienSocketWrapper.read().toString());
                            int choice1 = Integer.parseInt(input.nextLine());
                            clienSocketWrapper.write(choice1);
                            if (choice1 == 1) {
                                System.out.println(clienSocketWrapper.read().toString());
                                String restaurantName = input.nextLine();
                                clienSocketWrapper.write(restaurantName);
                                Object response = clienSocketWrapper.read();
                                if (response instanceof Restaurant) {
                                    ((Restaurant)response).Show_details_with_menu();
                                } else
                                    System.out.println(response);
                            }
                            else if (choice1 == 2) {
                                System.out.println(clienSocketWrapper.read().toString());
                                Double score1 = Double.parseDouble(input.nextLine());
                                clienSocketWrapper.write(score1);
                                Double score2 = Double.parseDouble(input.nextLine());
                                clienSocketWrapper.write(score2);
                                Object response = clienSocketWrapper.read();
                                if (response instanceof ArrayList
                                        && ((ArrayList) response).get(0) instanceof Restaurant) {
                                    for (Restaurant restaurant : (ArrayList<Restaurant>) response) {
                                        restaurant.Show_details_with_menu();
                                    }
                                } else
                                    System.out.println(response);
                            }
                            else if (choice1 == 3) {
                                System.out.println(clienSocketWrapper.read().toString());
                                String category = input.nextLine();
                                clienSocketWrapper.write(category);
                                Object response = clienSocketWrapper.read();
                                if (response instanceof ArrayList
                                        && ((ArrayList) response).get(0) instanceof Restaurant) {
                                    for (Restaurant restaurant : (ArrayList<Restaurant>) response) {
                                        restaurant.Show_details_with_menu();
                                    }
                                } else
                                    System.out.println(response);
                            }
                            else if (choice1 == 4) {
                                System.out.println(clienSocketWrapper.read().toString());
                                String price = input.nextLine();
                                clienSocketWrapper.write(price);
                                Object response = clienSocketWrapper.read();
                                if (response instanceof ArrayList
                                        && ((ArrayList) response).get(0) instanceof Restaurant) {
                                    for (Restaurant restaurant : (ArrayList<Restaurant>) response) {
                                        restaurant.Show_details_with_menu();
                                    }
                                } else
                                    System.out.println(response);
                            }
                            else if (choice1 == 5) {
                                System.out.println(clienSocketWrapper.read().toString());
                                String zipcode = input.nextLine();
                                clienSocketWrapper.write(zipcode);
                                Object response = clienSocketWrapper.read();
                                if (response instanceof Restaurant) {
                                    ((Restaurant) response).Show_details_with_menu();
                                } else
                                    System.out.println(response);
                            }
                            else if (choice1 == 6) {
                                HashMap<String, ArrayList<String>> category_wise_restaurant = (HashMap<String, ArrayList<String>>) clienSocketWrapper
                                        .read();
                                for (Map.Entry<String, ArrayList<String>> entry : category_wise_restaurant.entrySet()) {
                                    String Name = entry.getKey();
                                    System.out.println(Name);
                                    for (String restaurantName : entry.getValue()) {
                                        System.out.println(restaurantName);
                                    }
                                }

                            }
                            else break;
                       }
                   case 2:
                        while (true) {
                            System.out.println(clienSocketWrapper.read().toString());
                            int choice2 = Integer.parseInt(input.nextLine());
                            clienSocketWrapper.write(choice2);
                            if (choice2 == 1) {
                                System.out.println(clienSocketWrapper.read().toString());
                                String foodName = input.nextLine();
                                clienSocketWrapper.write(foodName);
                                Object response = clienSocketWrapper.read();
                                if (response instanceof String) {
                                    System.out.println(response);
                                } else{
                                  ArrayList<Food_Item> output=(ArrayList<Food_Item>)response;
                                    for(Food_Item foodItem:output){
                                        foodItem.Show_details();
                                        ArrayList<String> name_of_restaurants=(ArrayList<String>)clienSocketWrapper.read();
                                       System.out.println("Available in :"+name_of_restaurants);
                                    } 
                                }
                                    
                                }
                            else if (choice2 == 2) {
                                System.out.println(clienSocketWrapper.read().toString());
                                String foodName=input.nextLine();
                                clienSocketWrapper.write(foodName);
                                System.out.println(clienSocketWrapper.read().toString());
                                String restaurantName=input.nextLine();
                                clienSocketWrapper.write(restaurantName);
                                Object response = clienSocketWrapper.read();
                                if(response instanceof String) System.out.println(response);
                                else{
                                    ArrayList<Food_Item> output=(ArrayList<Food_Item>)response;
                                    for(Food_Item foodItem:output){
                                        foodItem.Show_details();
                                    }
                                }
                            }
                            else if(choice2==3){
                                System.out.println(clienSocketWrapper.read().toString());
                                String category=input.nextLine();
                                clienSocketWrapper.write(category);
                                Object response = clienSocketWrapper.read();
                                if(response instanceof ArrayList){
                                    ArrayList<Food_Item> output=(ArrayList<Food_Item>)response;
                                    for(Food_Item foodItem:output){
                                        foodItem.Show_details();
                                    }
                                }
                                else System.out.println(response);
                            }
                            else if(choice2==4){
                                System.out.println(clienSocketWrapper.read().toString());
                                String Name=input.nextLine();
                                clienSocketWrapper.write(Name);
                                String category=input.nextLine();
                                clienSocketWrapper.write(category);
                                Object response = clienSocketWrapper.read();
                                if(response instanceof ArrayList){
                                    ArrayList<Food_Item> output=(ArrayList<Food_Item>)response;
                                    for(Food_Item foodItem:output){
                                        foodItem.Show_details();
                                    }
                                }
                                else System.out.println(response);
                            }
                            else if(choice2==5){
                                System.out.println(clienSocketWrapper.read().toString());
                                Double sc1=Double.parseDouble(input.nextLine());
                                clienSocketWrapper.write(sc1);
                                Double sc2=Double.parseDouble(input.nextLine());
                                clienSocketWrapper.write(sc2);
                                Object response = clienSocketWrapper.read();
                                if(response instanceof ArrayList){
                                    ArrayList<Food_Item> output=(ArrayList<Food_Item>)response;
                                    for(Food_Item foodItem:output){
                                        foodItem.Show_details();
                                    }
                                }
                                else System.out.println(response);
                            }
                            else if(choice2==6){
                                System.out.println(clienSocketWrapper.read().toString());
                                String name=input.nextLine();
                                clienSocketWrapper.write(name);
                                Double p1=Double.parseDouble(input.nextLine());
                                clienSocketWrapper.write(p1);
                                Double p2=Double.parseDouble(input.nextLine());
                                clienSocketWrapper.write(p2);
                                Object response = clienSocketWrapper.read();
                                if(response instanceof ArrayList){
                                    ArrayList<Food_Item> output=(ArrayList<Food_Item>)response;
                                    for(Food_Item foodItem:output){
                                        foodItem.Show_details();
                                    }
                                }
                                else System.out.println(response);
                            }
                            else if (choice2 == 7) {
                                System.out.println(clienSocketWrapper.read().toString());
                                String name = input.nextLine();
                                clienSocketWrapper.write(name);
                                Object response = clienSocketWrapper.read();
                                if (response instanceof ArrayList) {
                                    ArrayList<Food_Item> output = (ArrayList<Food_Item>) response;
                                    for (Food_Item foodItem : output) {
                                        foodItem.Show_details();
                                    }
                                } else
                                    System.out.println(response);
                            }
                            else if (choice2 == 8) {
                                HashMap<String, Integer> output = (HashMap<String, Integer>) clienSocketWrapper.read();
                                for (Map.Entry<String, Integer> entry : output.entrySet()) {
                                    System.out.println(entry.getKey() + " " + entry.getValue());
                                }
                            }
                            else break;
                            }
                            break;
                    default:
                        break;   
                }
           }
        }catch (Exception e) {
            System.out.println("Error: "+e+ " at line "+ e.getStackTrace()[0].getMethodName());
        }
        
    }
}
public class entryPoint {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = input.nextLine();
        System.out.println("Enter your password: ");
        String password = input.nextLine();
        if (true) {
            new handleClient(username, password);
        }
        
    }
}