import java.net.*;
import java.io.*;
class Server{
    ServerSocket server;
    Socket socket;
    BufferedReader br;                        //reader
    PrintWriter out;                            //writer
    //constructor
    public Server(){
        try{
        server = new ServerSocket(7777);
        System.out.println("server is ready to accept connection");
         System.out.println("waiting...");
         socket=server.accept();

         br =new BufferedReader(new InputStreamReader(socket.getInputStream()));

         out =new PrintWriter(socket.getOutputStream());
         startReading();
         startWriting();

        } catch (Exception e){
            e.printStackTrace();

        }

    }
 
  public void startReading()
  {
//thrred krke deta rhega
Runnable r1 = ()-> {
     System.out.println( "reader started..");
     while(true){
        try {

        
        String msg = br.readLine();
        if(msg.equals("exit"))
        {
           System.out.println("client terminated the chat");
           break; 
        }
        System.out.println("Client :" + msg);
        } catch (Exception e){
        System.out.println("Connection closed");
         }

       } 
       System.out.println("Connection closed"); 
  };
  
  new Thread(r1).start();
  }
  public void startWriting()
  {
// thread - data user lega and the send karga client tak
Runnable r2 =()->{
  System.out.println("Write the started .....");
  while(true){
    
    try{
      BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
      String content = br1.readLine();
      out.println(content);
      out.flush();
     }catch (Exception e){
      //e.printStackTrace();
      System.out.println("Connection closed");
    }

    }

  };
  new Thread(r2).start();
  }
 
 
    public static void main(String[] args){
        System.out.println("This is server..going to start server");
        new Server();
    } 
}
