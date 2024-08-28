package Server;
import java.util.*;
import java.net.*;
import java.io.*;
import socketWorks.*;
//main function execution starts
 class serveClient implements Runnable {

     SocketWrapper restaurantSocketWrapper;
     Thread t;
     Restaurant_System restaurant_system;
        
        String Name;
        String category;
        String Price;
        int ID;
       public serveClient(SocketWrapper restauranSocketWrapper,Restaurant_System restaurant_system) throws Exception {
            this.restaurantSocketWrapper = restauranSocketWrapper;
            this.restaurant_system=restaurant_system;
            t = new Thread(this);
            t.start();
        }

        //loading data  from files

        // restaurant_system.load_data();//admin section
        // restaurant_system.load_restaurant_menu(); //admin section

        //starting the main function body
        public void run() {
            try {
                while (true) {
                    //main menu
                    restaurantSocketWrapper.write("""
                            Main Menu:
                            1) Search Restaurants
                            2) Search Food Items
                            3) Add Restaurant
                            4) Add Food Item to the Menu
                            5) Exit System
                            Enter the option: """);
                    //1,2,5 -->customer section
                    //3-->admin section
                    //4-->owner section

                 //   restaurantSocketWrapper.write("Enter the option:");
                    // String[] option = restaurantSocketWrapper.read().toString().split(" ");
                    // int primaryOption = Integer.parseInt(option[0]);
                    // int secondaryOption = Integer.parseInt(option[1]);
                    int option = Integer.parseInt(restaurantSocketWrapper.read().toString());
                    // while (option > 5 || option < 1) {
                    //     restaurantSocketWrapper.write("You entered an invalid input,try again!");
                    //     option = Integer.parseInt(restaurantSocketWrapper.read().toString());
                    // }

                    switch (option) {
                        case 1 -> { //option 1 submenu , searches for restaurant //customer section
                            int choice; //choice of options stored here
                            while (true) {
                                restaurantSocketWrapper.write("Restaurant Searching Options:\n" + //options
                                        "1) By Name\n" +
                                        "2) By Score(Double3)\n" +
                                        "3) By Category\n" +
                                        "4) By Price(String)\n" +
                                        "5) By Zip Code\n" +
                                        "6) Different Category Wise List of Restaurants\n" +
                                        "7) Back to Main Menu\n" +
                                        "Enter your choice:");
                                // restaurantSocketWrapper.write("Enter your choice:");
                                choice = Integer.parseInt(restaurantSocketWrapper.read().toString());

                                // while (choice > 7 || choice < 1) {
                                //     restaurantSocketWrapper.write("You entered an invalid input,try again!");
                                //     choice = Integer.parseInt(restaurantSocketWrapper.read().toString());
                                // }

                                if (choice == 1) { //searches for a restaurant with the given name and shows
                                    restaurantSocketWrapper.write("Input the name of the restaurant:");//all the information
                                    Name = (String) restaurantSocketWrapper.read(); //about that restaurant
                                    if (restaurant_system.Is_Name_present(Name)) {
                                        restaurantSocketWrapper
                                                .write(restaurant_system.Search_Restaurants_by_name(Name));
                                        //System.out.println();
                                    } else
                                        restaurantSocketWrapper.write("No such restaurant with this name!");
                                }

                                else if (choice == 2) { //Displays restaurant names within a range of score
                                    restaurantSocketWrapper.write("Input the upper limit score:");
                                    Double score1 = Double.parseDouble(restaurantSocketWrapper.read().toString()); //upper limit
                                    restaurantSocketWrapper.write("Input the lower limit score:");
                                    Double score2 = Double.parseDouble(restaurantSocketWrapper.read().toString());//lower limit
                                    ArrayList<Restaurant> output = new ArrayList<>();
                                    restaurant_system.Search_Restaurants_by_score(score1, score2, output);

                                    if (output.isEmpty())
                                        restaurantSocketWrapper.write("No such restaurant within this limit.");
                                    else {
                                       restaurantSocketWrapper.write(output);
                                    }
                                }

                                else if (choice == 3) {
                                    restaurantSocketWrapper.write("Enter category of restaurant to be found:"); //searches for restaurants having a given category
                                    category = restaurantSocketWrapper.read().toString();
                                    ArrayList<Restaurant> output = new ArrayList<>();
                                    restaurant_system.Search_restaurant_by_category(category, output); //needs to be modified(maybe)
                                    if (output.isEmpty())
                                        restaurantSocketWrapper.write("No restaurant with the category.");
                                    else {
                                       restaurantSocketWrapper.write(output);
                                    }
                                }

                                else if (choice == 4) { //searches restaurant with a specific price
                                    restaurantSocketWrapper.write("Enter Price:");//displays their names
                                    Price = restaurantSocketWrapper.read().toString();
                                    ArrayList<Restaurant> output = new ArrayList<>();
                                    restaurant_system.Search_by_Price(Price, output);
                                    if (output.isEmpty())
                                        restaurantSocketWrapper.write("No restaurant with the Price.");
                                    else {
                                        restaurantSocketWrapper.write(output);
                                    }
                                }

                                else if (choice == 5) {//searches restaurant by Zip code , displays their names
                                    ArrayList<Restaurant> output = new ArrayList<>();
                                    restaurantSocketWrapper.write("Enter the zip_code of restaurant:");
                                    String zip = restaurantSocketWrapper.read().toString();
                                    restaurant_system.Search_by_Zip(zip, output);
                                    if (output.isEmpty())
                                        restaurantSocketWrapper.write("No restaurant with the Zip code.");
                                    else {
                                        restaurantSocketWrapper.write(output);
                                    }
                                }

                                else if (choice == 6) { //category wise restaurant finding , category wise restaurant names
                                    // for (Map.Entry<String, ArrayList<String>> entry : restaurant_system
                                    //         .getCategory_wise_restaurant().entrySet()) {
                                    //     Name = entry.getKey();
                                    //     ArrayList<String> list = entry.getValue();
                                    //     System.out.println("Name of category: " + Name);
                                    //     for (String str : list)
                                    //         System.out.printf("%s ", str);
                                    //     System.out.println();
                                    // }
                                    restaurantSocketWrapper.write(restaurant_system.getCategory_wise_restaurant());
                                }

                                else
                                    break;
                            }
                        } //above codes have been tested in 16/8/2023 at 11:10 pm at party house mosque, works fine

                        case 2 -> { //second sub-option of main menu , searches for food in restaurants
                            int choice;
                            boolean Finished = false;
                            while (!Finished) {
                                restaurantSocketWrapper.write("""
                                        1) By Name
                                        2) By Name in a Given Restaurant
                                        3) By Category
                                        4) By Category in a Given Restaurant
                                        5) By Price Range
                                        6) By Price Range in a Given Restaurant
                                        7) Costliest Food Item(s) on the Menu in a Given Restaurant
                                        8) List of Restaurants and Total Food Item on the Menu
                                        9) Back to Main Menu
                                        Enter your choice:
                                        """);
                                
                                choice = Integer.parseInt(restaurantSocketWrapper.read().toString());
                                // while (choice > 9 || choice < 1) {
                                //     restaurantSocketWrapper.write("You entered an invalid input,try again!");
                                //     choice = Integer.parseInt(restaurantSocketWrapper.read().toString());
                                // }
                                switch (choice) {
                                    case 1 -> { //displays all food items by same name
                                        restaurantSocketWrapper.write("Enter Name of food:"); //displays all info about all the foods with that
                                        Name = restaurantSocketWrapper.read().toString();//name
                                       ArrayList<Food_Item> output = new ArrayList<>();
                                       restaurant_system.Display_all_items_by_name(Name, output);
                                        if (output.isEmpty())
                                            restaurantSocketWrapper.write("No food with that name is found.");
                                        else {
                                            restaurantSocketWrapper.write(output);
                                            for (Food_Item fditm : output) {
                                                
                                                ArrayList<String> name_of_restaurants = new ArrayList<>();
                                                for (Restaurant restaurant : restaurant_system.Collection_of_Restaurants) {
                                                    if (restaurant.getID() == fditm.getRestaurant_ID()) {
                                                        if (!name_of_restaurants.contains(fditm.getName()))
                                                            name_of_restaurants.add(restaurant.getName());
                                                    }
                                                } //please check the documentation to be sure what == above here does
                                               restaurantSocketWrapper.write(name_of_restaurants);
                                            }

                                        }
                                    }

                                    case 2 -> { //displays all the info all food items in that restaurant
                                        restaurantSocketWrapper.write("Enter food name:");
                                        String food_name = restaurantSocketWrapper.read().toString();
                                        restaurantSocketWrapper.write("Enter restaurant name:");
                                        String restaurant_name = restaurantSocketWrapper.read().toString();
                                        ArrayList<Food_Item> output = new ArrayList<>();
                                        restaurant_system.Search_by_name_in_a_restaurant(food_name, restaurant_name,
                                                output);
                                        if (output.isEmpty())
                                            restaurantSocketWrapper
                                                    .write("No food item of that name in this restaurant.");
                                        else {
                                            restaurantSocketWrapper.write(output);
                                        }
                                    }

                                    case 3 -> { //displays all info about all the food item of that category
                                        restaurantSocketWrapper.write("Enter a food Category:");
                                        category = restaurantSocketWrapper.read().toString();
                                        ArrayList<Food_Item> output = new ArrayList<>();
                                        restaurant_system.Search_food_of_a_category(category, output);
                                        if (output.isEmpty())
                                            restaurantSocketWrapper.write("No food item of that category.");
                                        else {
                                         restaurantSocketWrapper.write(output);
                                        }
                                    }

                                    case 4 -> { //displays all the foods of a category of a restaurant
                                        restaurantSocketWrapper.write("Enter Name of Restaurant:");
                                        Name = restaurantSocketWrapper.read().toString();
                                        restaurantSocketWrapper.write("Enter category:");
                                        category = restaurantSocketWrapper.read().toString();
                                        ;
                                        ArrayList<Food_Item> output = new ArrayList<>();
                                        restaurant_system.Search_food_of_a_category_in_a_restaurant(Name, category,
                                                output);
                                        if (output.isEmpty())
                                            restaurantSocketWrapper
                                                    .write("No food of that category in the restaurant.");
                                        else {
                                            restaurantSocketWrapper.write(output);
                                        }
                                    }

                                    case 5 -> { //displays all info about all food in that range
                                        restaurantSocketWrapper.write("Enter the upper price limit:");
                                        double price1 = Double.parseDouble(restaurantSocketWrapper.read().toString());//upper limit
                                        restaurantSocketWrapper.write("Enter the lower price limit:");
                                        double price2 = Double.parseDouble(restaurantSocketWrapper.read().toString());//lower limit
                                        ArrayList<Food_Item> output = new ArrayList<>();
                                        restaurant_system.Search_food_on_range_whole_database(price1, price2, output);
                                        if (output.isEmpty())
                                            restaurantSocketWrapper.write("No food item found in that list.");
                                        else {
                                            restaurantSocketWrapper.write(output);
                                        }
                                    }

                                    case 6 -> {//fixed  //searches and displays all the foods in a restaurant in that
                                        restaurantSocketWrapper.write("Enter Restaurant Name:"); //range
                                        Name = restaurantSocketWrapper.read().toString();
                                        restaurantSocketWrapper.write("Enter upper Limit:");
                                        double price1 = Double.parseDouble(restaurantSocketWrapper.read().toString());//upper limit
                                        restaurantSocketWrapper.write("Enter lower limit:");
                                        double price2 = Double.parseDouble(restaurantSocketWrapper.read().toString());//lower limit
                                        ArrayList<Food_Item> output = new ArrayList<>();
                                        restaurant_system.Search_food_in_range_in_restaurant(Name, price1, price2,
                                                output);
                                        if (output.isEmpty())
                                            restaurantSocketWrapper.write("No food found in that range.");
                                        else {
                                           restaurantSocketWrapper.write(output);
                                        }
                                    }

                                    case 7 -> { //displays costliest food in the restaurant
                                        restaurantSocketWrapper.write("Enter a Restaurant Name:");
                                        Name = restaurantSocketWrapper.read().toString();
                                        ArrayList<Food_Item> output = new ArrayList<>();
                                        restaurant_system.Costliest_item(Name, output);
                                        restaurantSocketWrapper.write(output);
                                    }

                                    case 8 -> { //shows total food number for all the restaurants
                                        HashMap<String, Integer> output = new HashMap<>();
                                        restaurant_system.Show_all_details(output);
                                        // for (Map.Entry<String, Integer> entry : output.entrySet()) {
                                        //     String key = entry.getKey();
                                        //     Integer total_num = entry.getValue();
                                        //     System.out.println(key + " : " + total_num);
                                        //     System.out.println();
                                        // }
                                        restaurantSocketWrapper.write(output);
                                    }

                                    case 9 ->
                                            Finished = true;
                                }
                            } //above codes have been fixed and tested in 16/8/2023 at 11:35 pm at party house mosque
                        }

                        case 3 -> { //adding restaurant--->Admin section
                            restaurantSocketWrapper.write("Enter the name of restaurant:");
                            Name = restaurantSocketWrapper.read().toString();
                            restaurantSocketWrapper.write("Enter the price(string in $):");
                            Price = restaurantSocketWrapper.read().toString();
                            ;
                            restaurantSocketWrapper.write("Enter the zip code:");
                            String Zip_code = restaurantSocketWrapper.read().toString();
                            ;
                            restaurantSocketWrapper.write("Enter score of restaurant(number):");
                            Double score = Double.parseDouble(restaurantSocketWrapper.read().toString());
                            //adding category
                            restaurantSocketWrapper.write("Enter category separated by whitespaces:");
                            String Line = restaurantSocketWrapper.read().toString();
                            ;
                            String[] splitted = Line.split("\\s+");
                            ArrayList<String> input_array = new ArrayList<>(Arrays.asList(splitted));
                            ID = restaurant_system.getNewId();
                            Restaurant res = new Restaurant(ID, Name, score, Price, Zip_code, input_array);
                            restaurant_system.Add_restaurant(res);
                            restaurantSocketWrapper.write("Restaurant Added!");

                        }

                        //adding food item to menu
                        case 4 -> { // owner section
                            //ArrayList<String> input_to_file=new ArrayList<>();
                            // System.out.println("Enter Restaurant ID:");
                            //ID =Integer.parseInt(sc.nextLine());//
                            restaurantSocketWrapper.write("Enter Category:");
                            category = restaurantSocketWrapper.read().toString();
                            restaurantSocketWrapper.write("Enter Name of food:");
                            Name = restaurantSocketWrapper.read().toString();
                            restaurantSocketWrapper.write("Enter the Price:");
                            double price = Double.parseDouble(restaurantSocketWrapper.read().toString());
                            restaurantSocketWrapper.write("Enter Restaurant name where food to be added:");
                            String restaurant_name_to_be_found = restaurantSocketWrapper.read().toString();
                            ;

                            if (restaurant_system.Is_Name_present(restaurant_name_to_be_found)) {
                                restaurant_system.Add_food_item_to_restaurant(restaurant_name_to_be_found, category,
                                        Name,
                                        price);
                                restaurantSocketWrapper.write("Food Added to menu of " + restaurant_name_to_be_found);
                            } else {
                                restaurantSocketWrapper.write("Restaurant Name not found.");
                            }

                        }

                        //option 5 exits the system
                        case 5 -> { //for all

                            restaurantSocketWrapper.closeConnection();
                            System.exit(1);
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

public class RestaurantApp {
    public static void main(String[] args) {
      //  HashMap<String, SocketWrapper> clientList = new HashMap<>();
       try{
           ServerSocket serverSocket = new ServerSocket(2024);
           Restaurant_System restaurant_system = new Restaurant_System();
           restaurant_system.load_data();
           restaurant_system.load_restaurant_menu(); 
        while (true) {
            Socket socket = serverSocket.accept();
             SocketWrapper serveSocketWrapper = new SocketWrapper(socket);
            String identifier = (String) serveSocketWrapper.read();
             System.out.println(identifier);
         //   clientList.put(identifier, servSocketWrapper);
            new serveClient(serveSocketWrapper,restaurant_system);
       }

       }
       catch (Exception e) {
           System.out.println(e);
            System.out.println("At line number "+ e.getStackTrace()[0].getLineNumber());
       } 
        
    }
}

