package Server;
import java.io.PipedReader;
import java.io.Serializable;
public class Food_Item implements Serializable{
    final private String Category;
    final private String Name;
    final private double Price;
    final private int Restaurant_ID;
    //private String Name_of_restaurant_it_belongs;



    Food_Item(int Restaurant_ID,String category,String Name, Double Price){
        this.Restaurant_ID=Restaurant_ID;
        this.Category=category;
        this.Name=Name;
        this.Price= Price;
    }


    public String get_category(){
        return Category;
    }


    public Double getPrice() {
        return Price;
    }


    public String getName() {
        return Name;
    }
    public int getRestaurant_ID(){return Restaurant_ID;}
    public void Show_details(){
        //keep in mind
        // if need be
        // this should be made to return a string
        //return Restaurant_ID+Name+Category+ Price;
        System.out.println("Restaurant ID: "+Restaurant_ID);
        System.out.println("Name: "+Name);
        System.out.println("Category: "+Category);
        System.out.println("Price: "+Price);
    }

}
