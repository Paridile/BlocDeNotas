
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
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

public class Instrucciones {
    public static void nuevo(TextArea ta){
        ta.setText(" ");
    }
    
    public static void abrir(TextArea ta){
        String archivo="";
        Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            File f = new File(ruta);
            entrada = new Scanner(f);
            ta.setText(" ");
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
    }
    
    //fileChooser.showSaveDialog(fileChooser);
    
    public static void guardarComo(TextArea ta) {
       String nombre="";
       JFileChooser fileChooser = new JFileChooser();
       fileChooser.showSaveDialog(fileChooser);
       nombre = fileChooser.getSelectedFile().getAbsolutePath();
       File guardado = new File(nombre);
       FileWriter escritor = null;
       try{
           escritor = new FileWriter(guardado);
           escritor.write(ta.getText());
       }
       catch(FileNotFoundException e){
           System.out.println(e.getMessage());
       }
       catch(NullPointerException e){
            System.out.println("No se ha seleccionado ningún fichero");
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
       finally{
           
       }
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
