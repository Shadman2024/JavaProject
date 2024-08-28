package Server;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


class Restaurant_System{
    ArrayList<Restaurant> Collection_of_Restaurants=new ArrayList<>();
    HashMap<String,ArrayList<String>>category_wise_restaurant=new HashMap<>();

    public void load_data()throws Exception{
        BufferedReader buffer_reader=new BufferedReader(new FileReader("Server/restaurant.txt"));
        String line;
        while ((line=buffer_reader.readLine())!=null){
            String[] splitted =new String[5];
            splitted=line.split(",");
            ArrayList<String> var_arraylist = new ArrayList<>(Arrays.asList(splitted).subList(5, splitted.length));


            Restaurant restaurant=new Restaurant(Integer.parseInt(splitted[0]),splitted[1], Double.parseDouble(splitted[2]),splitted[3],splitted[4],var_arraylist);
            Collection_of_Restaurants.add(restaurant);

            for(String key:var_arraylist){
                if(!category_wise_restaurant.containsKey(key)) category_wise_restaurant.put(key,new ArrayList<>());
                ArrayList<String >item=category_wise_restaurant.get(key);
                item.add(restaurant.getName());
            }

        }
        buffer_reader.close();

    }
    public void load_restaurant_menu()throws Exception{
        BufferedReader buffer_reader=new BufferedReader(new FileReader("Server/menu.txt"));
        String line;
        while ((line=buffer_reader.readLine())!=null){


            String[] splitted =new String[5];
            splitted=line.split(",");
            if(splitted.length==5) {
                splitted[2] =splitted[2]+","+splitted[3];
                splitted[3] = splitted[4];
            }
            Food_Item foodItem=new Food_Item(Integer.parseInt(splitted[0]),splitted[1],splitted[2], Double.parseDouble(splitted[3]));

            for(Restaurant restaurant:Collection_of_Restaurants){
                if(restaurant.getID()==Integer.parseInt(splitted[0])) restaurant.menu.add(foodItem);

            }

        }
        // sort();
        buffer_reader.close();
    }


    public HashMap<String,ArrayList<String >> getCategory_wise_restaurant(){
        return category_wise_restaurant;
    }
    public void Add_restaurant(Restaurant rs)throws Exception{
        Collection_of_Restaurants.add(rs);
        for(String key:rs.Category){
            if (category_wise_restaurant.containsKey(key)){
                ArrayList<String > value_list=category_wise_restaurant.get(key);
                value_list.add(rs.getName());
            }
            else{
                category_wise_restaurant.put(key,new ArrayList<>());
                ArrayList<String > value_list=category_wise_restaurant.get(key);
                value_list.add(rs.getName());
            }
        }
        Save_restaurant_to_file(rs);

    }


    private void Save_restaurant_to_file(Restaurant restaurant)throws Exception{
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("Server/restaurant.txt",true));
        bufferedWriter.write(System.lineSeparator());
        bufferedWriter.write(String.valueOf(restaurant.getID()));
        bufferedWriter.write(","+restaurant.getName()+",");
        bufferedWriter.write(String.valueOf(restaurant.getScore()));
        bufferedWriter.write(","+restaurant.getPrice()+","+restaurant.getZip_code()+",");
        for (String str: restaurant.Category){
            bufferedWriter.write(str+",");
        }
        bufferedWriter.close();
    }

    public boolean Is_Name_present(String Name){
        if(Name.isEmpty()) return false;
        for(Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getName().toLowerCase().contains(Name.toLowerCase())) return true;
        }
        return false;
    }

    public void Add_food_item_to_restaurant(String Name,String category,String food_Name,Double price)throws Exception{
        for(Restaurant restaurant : Collection_of_Restaurants){
            if(restaurant.getName().toLowerCase().contains(Name.toLowerCase())){
                Food_Item fditm=new Food_Item(restaurant.getID(),category,food_Name,price);
                restaurant.Add_to_menu(fditm);
            }
        }
    }


    public Restaurant Search_Restaurants_by_name(String Name){ //searches restaurant by name
        for (Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getName().toLowerCase().contains(Name.toLowerCase())){
                return restaurant;
            }
        }
        return null;
    }


    public void Search_Restaurants_by_score(Double score1,Double score2,ArrayList<Restaurant>output){ //searches restaurant by score
        for (Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getScore()<=score1 && restaurant.getScore()>=score2) {
                output.add(restaurant);
            }
        }
    }


    public void Search_restaurant_by_category(String category,ArrayList<Restaurant>output){//this is a bit problematic , has to be implemented later
        for(Restaurant restaurant:Collection_of_Restaurants){
            for(String cat:restaurant.getCategory()){
                if(cat.toLowerCase().contains(category.toLowerCase()))
                    output.add(restaurant);
            }
        }
    }



    public void Search_by_Price(String Price,ArrayList<Restaurant>output){
        for(Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getPrice().equals(Price)) output.add(restaurant);
        }
    }


    public void Search_by_Zip(String zip_code,ArrayList<Restaurant>output){
        for (Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getZip_code().equals(zip_code)) output.add(restaurant);
        }
    }


    public void Display_all_items_by_name(String str,ArrayList<Food_Item>output){
        for(Restaurant restaurant:Collection_of_Restaurants){
            restaurant.Display_all_items_by_name(str,output);
        }
    }


    public void Search_by_name_in_a_restaurant(String food_name,String restaurant_name,ArrayList<Food_Item>output){
        for(Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getName().toLowerCase().contains(restaurant_name.toLowerCase()))
                restaurant.Search_by_name_in_a_restaurant(food_name,output);
        }
    }


    public void Search_food_of_a_category(String category,ArrayList<Food_Item>output){//what if there is same item of
        // same category in two different shops?
        for (Restaurant restaurant:Collection_of_Restaurants){
            restaurant.Search_food_of_a_category(category,output);
        }
    }


    public void Search_food_of_a_category_in_a_restaurant(String Name,String category,ArrayList<Food_Item>output){
        for (Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getName().toLowerCase().contains(Name.toLowerCase())) restaurant.Search_food_of_a_category_in_a_restaurant(category,output);
        }
    }


    public void Search_food_on_range_whole_database(double sc1,double sc2,ArrayList<Food_Item>output){
        for(Restaurant restaurant:Collection_of_Restaurants){
            restaurant.Search_food_on_range_whole_database(sc1,sc2,output);
        }
    }


    public void Search_food_in_range_in_restaurant(String name,double p1,double p2,ArrayList<Food_Item>output){
        for(Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getName().toLowerCase().contains(name.toLowerCase())) restaurant.Search_food_in_range_in_restaurant(p1,p2,output);

        }
    }


    public void Costliest_item(String name,ArrayList<Food_Item>output){
        for(Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getName().toLowerCase().contains(name.toLowerCase())) restaurant.Costliest_items(output);

        }
    }

    public int getNewId(){
        int lastId=0;
        for(Restaurant restaurant:Collection_of_Restaurants){
            if(restaurant.getID()>lastId) lastId= restaurant.getID();
        }
        return lastId+1;
    }
    public void Show_all_details(HashMap<String ,Integer>output){
        for (Restaurant restaurant:Collection_of_Restaurants){
            output.put(restaurant.getName(), restaurant.Total_food_items());
        }
    }
}
