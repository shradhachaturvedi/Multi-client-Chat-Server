import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static void main(String args[]) throws IOException, InterruptedException{
        ServerSocket server_socket=new ServerSocket(8092);
        Scanner sc=new Scanner(System.in);
        int counter=1;
        System.out.println("Server is running..");
        while(true){
            Socket s=server_socket.accept();
            System.out.println("Client "+counter+++" connected...");
            ServerThread st=new ServerThread(s);
            st.start();
        }
    }
    static class ServerThread extends Thread{
        static int counter=0;
        DataInputStream i;
        DataOutputStream o;
        String msg="";
        Socket s;
        ServerThread(Socket s) throws IOException{
            i=new DataInputStream(s.getInputStream());
            o=new DataOutputStream(s.getOutputStream());
            this.s=s;
            counter++;
        }
        @Override
        public void run() {
           
            try {
                do{
                msg=i.readUTF();
                System.out.println("Client message: "+msg);
                if(!msg.equals("bye"))
                    o.writeUTF("Received : "+msg);
                }while(!msg.equals("bye"));
                counter--;
                s.close();
                if(counter == 0)
                    System.exit(0);
                
                
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Closing server");
            
        }
   
        
    }
    
}
