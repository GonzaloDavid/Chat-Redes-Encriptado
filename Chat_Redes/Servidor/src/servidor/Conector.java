
package servidor;

import java.io.*;
import java.net.*;
import servidor.Servidor.*;
import static servidor.Servidor.DesCifrarRot13;

public class Conector extends Thread{

    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 4314;
       
    //para conectarme con un cliente
    public Conector(String nombre){
        
        super(nombre);
    }
    
    
    public void run(){
        
        String text="cadena";
        try{
            
        this.ss= new ServerSocket(puerto);
        s=ss.accept(); //genera una conexion
        
                    
        //incializacion de varaibles para la lectura de datos
        entradaSocket = new InputStreamReader(s.getInputStream());
        entrada = new BufferedReader(entradaSocket);
             
            
        //inicializacion de variables de salida de datos para el envio de mensajes
        salida= new DataOutputStream(s.getOutputStream());
        
        while(true)
        {
                text = this.entrada.readLine();
                
                text = DesCifrarRot13(text);                        
                //System.out.println(text);
                VServidor.textServidor.setText(VServidor.textServidor.getText() +"\n" + text);
        }      
        
        
        }catch(Exception e){
            System.out.println("Ha ocurrido un Error");
        };
        
    }    
    public void enviarMSG (String msg){
        
        try{
            salida.writeUTF(msg+"\n");
        }catch(Exception e){};
    }
    
    public String LeerMSG(){
        //regresa el mensaje escrito
        try{
            return entrada.readLine();            
        }catch(IOException e){};
        return null;
    }
    
    public void Deconectar(){
        //solo si hay cliente
        try{
            s.close();
        }catch(Exception e){};
        //si existe servidor
        try{
            ss.close();
        }catch(Exception e){};
        
    }

    void initCliente(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
