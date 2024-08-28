package Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

public class Restaurant implements Serializable{
    ArrayList<Food_Item> menu=new ArrayList<>();
    final private String Name;
    final private double Score;
    final private String Zip_code;
    final private String Price;
    final private int ID;
    ArrayList<String > Category=new ArrayList<>(3);

    HashMap<String,ArrayList<String >> category_wise_food=new HashMap<>();

    public int getID(){
        return ID;
    }
    public ArrayList<String> getCategory(){
        return Category;
    }
    Restaurant(int ID,String Name,Double score,String price,String Zip_code,ArrayList<String> arrayList){
        this.ID=ID;
        this.Name=Name;
        Score=score;
        Price=price;
        this.Zip_code=Zip_code;
        Category.addAll(arrayList);
        for(String categoryItem:arrayList){
            if(!category_wise_food.containsKey(categoryItem)) category_wise_food.put(categoryItem,new ArrayList<>());
        }
    }


    public void Add_to_menu(Food_Item fdi)throws Exception{
        menu.add(fdi);
        Save_food_to_file(fdi);
    }
    private void Save_food_to_file(Food_Item fdi)throws Exception{
        BufferedWriter BufferedWriter=new BufferedWriter(new FileWriter("Server/menu.txt",true));
        BufferedWriter.write(System.lineSeparator());
        BufferedWriter.write(String.valueOf(fdi.getRestaurant_ID()));
        BufferedWriter.write(","+fdi.get_category()+",");
        BufferedWriter.write(fdi.getName());
        BufferedWriter.write(","+String.valueOf(fdi.getPrice()) +",");
        BufferedWriter.close();
    }

    public String getName(){
        return Name;
    }

    public double getScore() {
        return Score;
    }

    public ArrayList<Food_Item> getMenu(){
        return menu;
    }

    public void Show_details_with_menu(){
        //might need to return the following
        //Pair<String,ArrayList<Food_Item>> p=new Pair<>(Zip_code,menu);
        System.out.println("ID:"+ID);
        System.out.println("Name:"+Name);
        System.out.println("Score:"+Score);
        System.out.println("Zip Code:"+Zip_code);
        System.out.println("Price:"+Price);
        for (Food_Item fditm:menu){
            fditm.Show_details();
        }
    }


    public void Show_details(){
        System.out.println("ID:"+ID);
        System.out.println("Name:"+Name);
        System.out.println("Score:"+Score);
        System.out.println("Zip Code:"+Zip_code);
        System.out.println("Price:"+Price);

    }

    public String getPrice(){return Price;}


    public String getZip_code(){return Zip_code;}


    public void Display_all_items_by_name(String str,ArrayList<Food_Item>output){
        for (Food_Item fditm:menu){
            if (fditm.getName().toLowerCase().contains(str.toLowerCase()))
                output.add(fditm);
        }
    }



    public void Search_by_name_in_a_restaurant(String food_name,ArrayList<Food_Item>output){
        for(Food_Item fditm:menu){
            if(fditm.getName().toLowerCase().contains(food_name)) output.add(fditm);
        }
    }


    public void Search_food_of_a_category(String category,ArrayList<Food_Item>output){
        for (Food_Item fditm:menu){
            if(fditm.get_category().toLowerCase().contains(category.toLowerCase())) output.add(fditm);
        }
    }


    public void Search_food_of_a_category_in_a_restaurant(String category,ArrayList<Food_Item>output){
        for(Food_Item fditm:menu){
            if(fditm.get_category().toLowerCase().contains(category.toLowerCase())) output.add(fditm);
        }
    }


    public void Search_food_on_range_whole_database(double sc1,double sc2,ArrayList<Food_Item>output){
        for(Food_Item fditm:menu){
            if(fditm.getPrice()<=sc1 && fditm.getPrice()>=sc2) output.add(fditm);
        }
    }


    public void Search_food_in_range_in_restaurant(double p1,double p2,ArrayList<Food_Item>output){
        for(Food_Item fditm:menu){
            if(fditm.getPrice()<=p1 && fditm.getPrice()>=p2) output.add(fditm);
        }
    }


    public void Costliest_items(ArrayList<Food_Item>output){

        double max=0;

        for(Food_Item fditm:menu){
            if(max< fditm.getPrice()) max=fditm.getPrice();
        }

        for(Food_Item fditm:menu){
            if(fditm.getPrice()==max) output.add(fditm);
        }

    }


    public int Total_food_items(){
        return menu.size();
    }


}
