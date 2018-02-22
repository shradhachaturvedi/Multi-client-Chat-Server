import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Client {
    public static void main(String args[]) throws IOException{
        Socket s=new Socket("127.0.0.1",8092);
        System.out.println("Client connection is established");
        String msg;
        DataOutputStream dc=new DataOutputStream(s.getOutputStream());
        DataInputStream di=new DataInputStream(s.getInputStream());
        Scanner sc=new Scanner(System.in);
        do{
             msg=sc.next();
             dc.writeUTF(msg);
             System.out.println("Client sent: "+msg);
             if(!msg.equals("bye"))
                System.out.println("Server replied: "+di.readUTF());
        }while(!msg.equals("bye"));
        s.close();
        
    }
    
}
