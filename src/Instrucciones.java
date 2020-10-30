
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
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

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
    public static void nuevo(TextArea ta){
        ta.setText(" ");
    }

    public static void abrir(Frame fr,TextArea ta){
        Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            File f = new File(ruta);
            String nombre = fileChooser.getName(f);
            entrada = new Scanner(f);
            fr.setTitle(nombre + " - Bloc de notas");
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


      public static void salir(){
    int resultado;
    resultado = JOptionPane.showConfirmDialog(null,
            "Estas seguro que deseas salir sin guardar?",
            "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
    if (resultado == JOptionPane.YES_OPTION) System.exit(0);
}

    

    public static void fechaYHora(TextArea ta){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ta.append(dtf.format(now) + " ");
    }

    public static void guardar(TextArea ta) {
        System.out.println("Estas Guardando");
        String nombreArchivo = "Prueba";/*ta.getText();*///Cambiarlo por el nombre del archivo
        String carpeta = System.getProperty("user.dir");
        String rutaCompleta = carpeta + "/" + nombreArchivo + ".txt";
        FileWriter ubicacion = null;
        try {
            ubicacion = new FileWriter(rutaCompleta);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter escritor = new BufferedWriter(ubicacion);
        try {
            escritor.write(ta.getText());//RECOJE LO QUE HAY EN EL AREA DE TEXTO
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                ta.append(textopegado);
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(Instrucciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
