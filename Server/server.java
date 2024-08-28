

import socketWorks.*;
import java.net.*;
import java.util.*;


// class serveClient implements Runnable {
//     SocketWrapper clientSocketWrapper;
//     SocketWrapper restaurantSocketWrapper;
//     Thread t;

//     serveClient(SocketWrapper clientSocketWrapper) throws Exception {
//         this.clientSocketWrapper = clientSocketWrapper;
//         restaurantSocketWrapper=new SocketWrapper("127.0.0.1",2025);
//         t = new Thread(this);
//         t.start();
//     }

//     public void run() {
//         try {
//             options option = new options();

//             SocketWrapper serverClientSocket = new SocketWrapper("127.0.0.1",2025);
            
//             while (true) {
//                 clientSocketWrapper.write(option.getClientOptionsString());
//                 int choice = (int) clientSocketWrapper.read();
//                 switch (choice) {
//                     case 1:
//                         clientSocketWrapper.write(option.searchRestaurantOptions());
//                         int secondChoice = (int) clientSocketWrapper.read();
//                         //call to the restaurant app for searching restaurants
//                         serverClientSocket.write(choice+" "+secondChoice);
//                         break;
//                     case 2:
//                         clientSocketWrapper.write(option.searchFoodItemOptions());
//                         int secondChoice2 = (int) clientSocketWrapper.read();
//                         //call to the food item app for searching food items
//                         serverClientSocket.write(choice+" "+secondChoice2);
//                         break;
//                     default:
//                         break;
//                 }
//             }
//         } catch (Exception e) {
//             // TODO: handle exception
//             System.out.println(e);
//         }

//     }
// }
public class server {
    public static void main(String[] args) {
        HashMap<String, SocketWrapper> clientList = new HashMap<>();
       try{
        ServerSocket serverSocket = new ServerSocket(2024);
        
        while (true) {
            Socket socket = serverSocket.accept();
            SocketWrapper servSocketWrapper = new SocketWrapper(socket);
            String identifier = (String) servSocketWrapper.read();
            System.out.println(identifier);
            clientList.put(identifier, servSocketWrapper);
            new serveClient(servSocketWrapper);
       }

       }
       catch (Exception e) {
        System.out.println(e);
       }
        
    }
}
