
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.*;

/*
 * BLOC DE NOTAS
 * Crear un un bloc de notas tomando en cuenta los archivo de menu archivo, menu editar
 * y menu ayuda que dio; el menu archivo debe terner funcionando las opciones nuevo, abrir, guardar
 * guardar como y salir; El menu Editar debe tener funcionando las opciones Deshacer, 
 * cortar, copiar, pegar, eliminar, seleccionar todo y hora y fecha; El menu ayuda solo 
 * debe mostrar información de los participantes del equipo e información extra acerca
 * del proyecto.
 * El bloc de notas debe ser creado en swing o awt.
 */

/**
 *
 * @author Uriel
 */

public class Instrucciones extends JFrame{
    public static void nuevo(TextArea ta){
        ta.setText(" ");
    }
    
    public static String abrir(TextArea ta){
        String archivo="";
        Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        String ruta = fileChooser.getSelectedFile().getAbsolutePath();
        try {
            //String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            File f = new File(ruta);
            entrada = new Scanner(f);
            ta.setText("");
            while (entrada.hasNext()) {
                ta.append(entrada.nextLine());
                ta.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
        System.out.println("La ruta es: "+ruta);
        System.out.println("El archivo es: "+fileChooser);
        return ruta;
    }
    
    public static String guardar(TextArea ta, String ruta) {
        System.out.println("Guardar Archivo!!");
        String rut=null;
        if(ruta==null){
            rut = guardarComo(ta);
        }
        else{
            FileWriter escritor = null;
            try {
            escritor = new FileWriter(ruta);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }      
        BufferedWriter buff = new BufferedWriter(escritor);
        try {
            buff.write(ta.getText());//RECOJE LO QUE HAY EN EL AREA DE TEXTO
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            buff.close();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return rut;
    }
    
    public static String guardarComo(TextArea ta) {
        System.out.println("Guardando Archivo!!");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        String ruta = null;
        
        if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            ruta = fileChooser.getSelectedFile().getAbsolutePath();
            FileWriter escritor = null;

            try {
                escritor = new FileWriter(archivo);
                escritor.write(ta.getText());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    escritor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("La ruta es:"+ruta);
        return ruta;
    }
    
    public static void salir(){
        System.exit(0);
    }
    
    public static void fechaYHora(TextArea ta){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        ta.append(dtf.format(now) + " ");
    }
}
