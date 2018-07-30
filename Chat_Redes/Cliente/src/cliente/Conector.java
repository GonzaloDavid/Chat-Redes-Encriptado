/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import static cliente.Cliente.*;
import java.io.*;
import java.net.*;

public class Conector extends Thread{

    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 4314;
       
    
    public Conector(String ip){
        
        try{
            
            s= new Socket(ip, puerto);            
            
            //incializacion de varaibles para la lectura de datos
            entradaSocket = new InputStreamReader(s.getInputStream());
            entrada = new BufferedReader(entradaSocket);            
           
             //inicializacion de variables de salida de datos para el envio de mensajes
            salida= new DataOutputStream(s.getOutputStream());
            salida.writeUTF(cifrarRot13("Conectado exitosamente \n\n"));
                     
            
        }catch(Exception e){};
                
    }
    
    
    
    public void run(){
       
       String texto;
              
       while(true){
           try{
            texto = entrada.readLine();
            texto=DesCifrarRot13(texto);
            
            VCliente.textoCliente.setText(VCliente.textoCliente.getText()+ "\n"+texto);
           }catch(IOException e){};           
        }
        
    }    
    public void enviarMSG (String msg){
        
        //System.out.println("Enviado");
        try{
            this.salida = new DataOutputStream(s.getOutputStream());
            this.salida.writeUTF(msg+"\n");
        }catch (IOException e){
            System.out.println("Error al enviar");
        };
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
