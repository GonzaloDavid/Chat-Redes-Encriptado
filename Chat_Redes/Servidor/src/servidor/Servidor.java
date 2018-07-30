
package servidor;

public class Servidor {

    public static Conector cliente, servidor;     
    public static void main(String[] args) {
        
        VServidor servidor = new VServidor(); //Es una ventana servidor
        servidor.show();
        
               
    }
    public static void initServer(){
        servidor = new Conector("hilos"); // es un servidor pues no tiene parametros
        servidor.start();
    }        
    
    public static String cifrarRot13(String cadena){
        
        String Minu = "abcdefghijklmnñopqrstuvwxyz";
        String Mayu = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String texto;
        texto = cadena;
        String Cifrado = "";
        
        for(int n = 0;n<texto.length();n++){
        if((Minu.indexOf(texto.charAt(n))!=-1)||(Mayu.indexOf(texto.charAt(n))!=-1))
            if(Minu.indexOf(texto.charAt(n))!=-1){
                Cifrado+=Minu.charAt(((Minu.indexOf(texto.charAt(n)))+13)%Minu.length());
            }else{
               
                Cifrado+=Mayu.charAt((Mayu.indexOf(texto.charAt(n))+13)%Mayu.length());    
            }
            
        else
            Cifrado+=texto.charAt(n);
        }
        return Cifrado;
    }  
    
    public static String DesCifrarRot13(String cadena){
        String Minu = "abcdefghijklmnñopqrstuvwxyz";
        String Mayu = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String texto;
        texto = cadena;
        String DesEncrip="";
        
        for(int n = 0;n<texto.length();n++){
            
        if((Minu.indexOf(texto.charAt(n))!=-1)){
            
            if((Minu.indexOf(texto.charAt(n))-13)<0){
                DesEncrip+=Minu.charAt( Minu.length()+((Minu.indexOf(texto.charAt(n)))-13));
            }
            else{
                DesEncrip+=Minu.charAt((Minu.indexOf(texto.charAt(n)))-13);
            }
        }
        else{
            
            if((Mayu.indexOf(texto.charAt(n))!=-1)){
                
                if((Mayu.indexOf(texto.charAt(n))-13)<0){
                DesEncrip+=Mayu.charAt(Mayu.length()+(Mayu.indexOf(texto.charAt(n))-13));
                
                }
                else
                {
                     DesEncrip+=Mayu.charAt((Mayu.indexOf(texto.charAt(n))-13));    
                }
            }else{      
                
            DesEncrip+=texto.charAt(n);
            
            }        
        }
        }
        return DesEncrip;    
        
    }
    
}
