 class options {
    private String clientOptionsString = """
        Main Menu:
                1) Search Restaurants                                        
                2) Search Food Items""";
    private String  adminOptionString="""
        Admin Menu:
                1) Add Restaurant""";
    private String ownerOptionString="""
        Owner Menu:
                1) Add Food Item""";
    
    private String searchRestaurantOptions = """
        Restaurant Searching Options:
           1) By Name
           2) By Score (Double)
           3) By Category
           4) By Price (String)
           5) By Zip Code
           6) Different Category Wise List of Restaurants
           7) Back to Main Menu
            """;
     
    private String searchFoodItemOptions = """
        Food Item Searching Options:
                1) By Name
                2) By Name in a Given Restaurant
                3) By Category
                4) By Category in a Given Restaurant
                5) By Price Range
                6) By Price Range in a Given Restaurant
                7) Costliest Food Item(s) on the Menu in a Given Restaurant
                8) List of Restaurants and Total Food Item on the Menu
                9) Back to Main Menu
                """;   
    public String getClientOptionsString() {
        return clientOptionsString;
    }

    public String getAdminOptionString() {
        return adminOptionString;
    }

    public String getOwnerOptionString() {
        return ownerOptionString;
    }

    public String searchRestaurantOptions() {
        return searchRestaurantOptions;
    }
    public String searchFoodItemOptions() {
        return searchFoodItemOptions;
    }
    
            
}
