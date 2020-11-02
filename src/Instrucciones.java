
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


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

public class Instrucciones {
    
    private static String ruta;
    private static String archivo;

    private static String getRuta() {
        return ruta;
    }

    private static void setRuta(String ruta) {
        Instrucciones.ruta = ruta;
    }

    public static String getArchivo() {
        return archivo;
    }

    public static void setArchivo(String archivo) {
        Instrucciones.archivo = archivo;
    }
    
    
    
    public static void nuevo(Frame f,TextArea ta){
        if(f.getTitle()=="Sin titulo: Bloc de notas" && ta.getText().length() > 0){
            confirmaGuardarNuevo(f, ta);
        }
        else if(compara(ta.getText())){
            confirmaGuardarNuevo(f, ta);
        }
        else {
            ta.setText(" ");
            ta.replaceRange("", 0, 1);
            f.setTitle("Sin titulo: Bloc de notas");
            setRuta(null);
        }
    }
    
    public static void salir(Frame f,TextArea ta){
        int resultado;
        
        if(f.getTitle()!="Sin titulo: Bloc de notas"){
            if(compara(ta.getText())) {
                resultado = JOptionPane.showConfirmDialog(null,
                "Desea guardar los cambios de este documento?",
                "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
                if (resultado == JOptionPane.YES_OPTION) {
                    guardar(f,ta);           
                }   
                if(resultado == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
            else {
                System.exit(0);
            }
        }
        
        else if(f.getTitle().equals("Sin titulo: Bloc de notas") && ta.getText().length() > 0) {
            resultado = JOptionPane.showConfirmDialog(null,
            "Desea guardar los cambios de este documento?",
            "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
            if (resultado == JOptionPane.YES_OPTION) {
                guardarComo(f,ta);
                System.exit(0);
            }
            if(resultado == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
        else {
            System.exit(0);
        }
    }
    
    public static void fechaYHora(TextArea ta){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        ta.insert(dtf.format(now), ta.getCaretPosition());  
    }
    
    public static void seleccionarTodo(TextArea ta) {
        ta.selectAll();
    }
    
    public static void copiar (TextArea ta) {
        String seleccionado = ta.getSelectedText();
        if(seleccionado != null){
            StringSelection stringSelection = new StringSelection(seleccionado);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }
    
    public static void pegar(TextArea ta)  {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if(clipboard != null){
            clipboard.getContents(ta);
            String textopegado;
            try {
                textopegado = (String) clipboard.getData(DataFlavor.stringFlavor);
                ta.insert(textopegado, ta.getCaretPosition());
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(Instrucciones.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } 
    }


    public static void eliminar(TextArea ta) {
       try{
        ta.replaceRange("", ta.getSelectionStart(), ta.getSelectionEnd());
       }
       catch(StringIndexOutOfBoundsException e){}
    }
    
    public static void cortar(TextArea ta) {
        String seleccionado = ta.getSelectedText();
        if(seleccionado != null){
            StringSelection stringSelection = new StringSelection(seleccionado);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            eliminar(ta);
        }
    }
    
    public static void guardar(Frame fr,TextArea ta) {
        System.out.println("Guardar Archivo!!");
        if(getRuta()==null){
            guardarComo(fr,ta);
        }
        else{
            FileWriter escritor = null;
            try {
            escritor = new FileWriter(getRuta());
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
        
    }
    
    public static void guardarComo(Frame fr,TextArea ta) {
        System.out.println("Guardando Archivo!!");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        String rutaAux = getRuta();
        if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            setRuta(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println("Ruta: " + getRuta());
            if (getRuta() == null && rutaAux !=null) {
                setRuta(rutaAux);
            }
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
        try{
            File f = new File(getRuta());
            setArchivo(fileChooser.getName(f));
            fr.setTitle(getArchivo() + " - Bloc de notas");
            System.out.println("La ruta es: "+getRuta());
            System.out.println("El archivo es: "+getArchivo());
        }
        catch(NullPointerException e){}
    }
    
        public static void abrir(Frame fr,TextArea ta) {
        Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        String rutaAux = getRuta();
        try {
            int resultado;
                    if(fr.getTitle()=="Sin titulo: Bloc de notas" && ta.getText().length() > 0){
                        resultado = JOptionPane.showConfirmDialog(null,
                        "Desea guardar los cambios de este documento?",
                        "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (resultado == JOptionPane.YES_OPTION) {
                            guardar(fr,ta);           
                        } 
                    }
                
                    else if(compara(ta.getText())) {
                    resultado = JOptionPane.showConfirmDialog(null,
                    "Desea guardar los cambios de este documento?",
                    "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resultado == JOptionPane.YES_OPTION) {
                        guardar(fr,ta);           
                    }   
                }
            fileChooser.showOpenDialog(fileChooser);
            if(fileChooser.getSelectedFile().getAbsolutePath() != null)
            setRuta(fileChooser.getSelectedFile().getAbsolutePath());
            File f = new File(getRuta());  
            if (!f.exists()){
                JOptionPane.showMessageDialog(null, "No se encontro el archivo");
            }
            else{
                entrada = new Scanner(f);
                ta.setText("");
                while (entrada.hasNext()) {
                    ta.append(entrada.nextLine());
                    ta.append("\n");
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (entrada != null) {
                entrada.close();
            }
        }
        try{
            File f = new File(getRuta());
            if(f.exists()){
                setArchivo(fileChooser.getName(f));
                fr.setTitle(getArchivo() + " - Bloc de notas");
                System.out.println("La ruta es: "+getRuta());
                System.out.println("El archivo es: "+getArchivo());
            }
            else{
                setRuta(rutaAux);
            }
        }
        catch(NullPointerException e){}
    }
        
        
    public static boolean compara(String text) {
        boolean flag=false;
        try {
            if(getRuta() != null){
                List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(getRuta()), StandardCharsets.UTF_8));
                String arc="";
                for (int i = 0; i < fileContent.size(); i++) {
                    arc += fileContent.get(i) + "\n";
               }
                if(text.equals(arc)) {
                    flag = false;
                }
                else{
                    flag = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Instrucciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public static void confirmaGuardarNuevo(Frame f,TextArea ta) {
        int resultado;
        resultado = JOptionPane.showConfirmDialog(null,
            "Desea guardar los cambios de este documento?",
            "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            guardar(f,ta);     
            ta.setText(" ");
            ta.replaceRange("", 0, 1);
            f.setTitle("Sin titulo: Bloc de notas");
            setRuta(null);
        }   
        if(resultado == JOptionPane.NO_OPTION) {
            ta.setText(" ");
            ta.replaceRange("", 0, 1);
            f.setTitle("Sin titulo: Bloc de notas");
            setRuta(null);
        }
    }
    
    public static void acercaDe() {
        JOptionPane.showMessageDialog(null, "Este programa fue hecho para la clase de Programación visual del\n"
                + "grupo ICO G-92 de UAEMex por\n\n"
                + "     - Ríos Díaz de León José Pablo\n"
                + "     - Jimenez Zempoalteca Uriel\n"
                + "     - Sanchez Cortes Aaron Israel\n"
                + "     - Paez Villafuerte Eithel Agustin\n\n");
    }
}
