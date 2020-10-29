import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Principal extends WindowAdapter implements ActionListener{
    private Frame f;//---------------------------------------------------VENTANA
    private MenuBar mb;//------------------------------------------BARRA DE MENU
    private TextArea ta;//-----------------------------------------AREA DE TEXTO
    private FileDialog fd;//-------------------------------VENTANA ABRIR ARCHIVO
    
    private Menu m1;//-------------------------------------------PESTAÑA 1:ARCHIVO
    private Menu m2;//-------------------------------------------PESTAÑA 2:EDICION
    private Menu m3;//-------------------------------------------PESTAÑA 3:FORMATO
    private Menu m4;//-------------------------------------------PESTAÑA 4:VER
    private Menu m5;//-------------------------------------------PESTAÑA 5:AYUDA
    private Menu m6;//--------------------------------------PESTAÑA 6:DEVELOPERS
//------------------------------------------------------------------------------BOTONES DE LA PESTAÑA ARCHIVO
    private MenuItem nuevo;
    private MenuItem nuevaVentana;
    private MenuItem abrir;
    private MenuItem guardar;
    private MenuItem guardarComo;
    private MenuItem configurarPagina;
    private MenuItem imprimir;
    private MenuItem salir;

//------------------------------------------------------------------------------MAIN DE EJECUCIÓN
  public static void main (String args[]) {
    Principal Traer1 = new Principal();
    Traer1.go();
  }
//------------------------------------------------------------------------------VOID: PARAMETROS DE ACCIÓN
  public void go() {
//------------------------------------------------------------------------------ACCIONES DE VENTANA
    f = new Frame("BLOC DE NOTAS");//-----------------------TITULO DE LA VENTANA
    f.setSize(400,400);//-------------TAMAÑO DE LA VENTA (HORIZONTAL * VERTICAL)
    f.setVisible(true);//--------------------------------------------VER VENTANA
    f.addWindowListener((WindowListener) this);//--------------CERRAR LA VENTANA
    ta = new TextArea("", 4, 30);//PARAMETROS DESCONOCIDOS
    f.add(ta, BorderLayout.CENTER);//--POSICIONAR EL AREA DE TEXTO EN LA VENTANA
    fd = new FileDialog(f, "FileDialog");//---INGRESA EL FILEDIALOG A LA VENTANA

    
//------------------------------------------------------------------------------ACCIONES DE LA BARRA DE MENU
    mb = new MenuBar();//------------------------------------------BARRA DE MENU
    f.setMenuBar(mb);//----------------------COLOCAR BARRA DE MENU EN LA VENTANA

    
    
//------------------------------------------------------------------------------PESTAÑAS
    m1 = new Menu("Archivo");//--------------------------------NOMBRAR PESTAÑA
    m2 = new Menu("Edicion");
    m3 = new Menu("Formato");
    m4 = new Menu("Ver");
    m5 = new Menu("Ayuda");
    m6 = new Menu("Developers");
    
//------------------------------------------------------------------------------COLOCAR PESTAÑAS EN LA BARRA DE MENU
    mb.add(m1);
    mb.add(m2);
    mb.add(m3);
    mb.add(m4);
    mb.add(m5);
    mb.add(m6);
    
//------------------------------------------------------------------------------BOTONES DE PESTAÑA "ARCHIVO"    
    nuevo = new MenuItem("Nuevo");//---------NOMBRAR BOTONES DE LA PESTAÑA"
    nuevaVentana = new MenuItem("Nueva Ventana");
    abrir = new MenuItem("Abrir");
    guardar = new MenuItem("Guardar");
    guardarComo = new MenuItem("Guardar Como");
    configurarPagina = new MenuItem("Configurar Pagina");
    imprimir = new MenuItem("Imprimir");
    salir = new MenuItem("Salir");
        
    m1.add(nuevo);//------------------------METER BOTONES A LA PESTAÑA "ARCHIVO"
    m1.add(nuevaVentana);
    m1.add(abrir);
    m1.add(guardar);
    m1.add(guardarComo);
    m1.add(configurarPagina);
    m1.add(imprimir);
    m1.add(salir);
    nuevo.addActionListener(this);//-------------------------IMPLEMENTAR BOTONES
    nuevaVentana.addActionListener(this);
    abrir.addActionListener(this);
    guardar.addActionListener(this);
    guardarComo.addActionListener(this);
    configurarPagina.addActionListener(this);
    imprimir.addActionListener(this);
    salir.addActionListener(this);
    
  }
//------------------------------------------------------------------------------CIERRE GO

//------------------------------------------------------------------------------VOID: BOTON CERRAR VENTANA
  public void windowClosing(WindowEvent ev)
  {
    System.out.println("BLOC DE NOTAS CERRADO.");
    System.exit(0);
  }
//------------------------------------------------------------------------------FIN VOID: BOTON CERRAR VENTANA
  public void itemStateChanged(ItemEvent ie) {
    String state = "deselected";

    if (ie.getStateChange() == ItemEvent.SELECTED) {
      state = "selected";
    }
    System.out.println(ie.getItem() + " " + state);
  }
  
//------------------------------------------------------------------------------VOID: EVENTOS
  public void actionPerformed(ActionEvent ev)
  {
      String buttonPressed = ev.getActionCommand();
      String cadena;
//------------------------------------------------------------------------------SENTENCIA GUARDAR      
        if (buttonPressed.equals("Guardar")){
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
//------------------------------------------------------------------------------SENTENCIA ABRIR
        else if(buttonPressed.equals("Abrir")){
		fd.setVisible(true);
                cadena=fd.getFile();
                String archivo = fd.getFile();
                    if (cadena!=null){
			System.out.println("Dirección: "+fd.getDirectory()+cadena);
                        FileReader fr = null;
                    try {
                        fr = new FileReader (archivo);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                BufferedReader br = new BufferedReader(fr);
                String texto = "";
                String linea = "";
                    try {
                        while(((linea = br.readLine()) != null)){                            
                            texto+=linea+" \n";                                
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                ta.setText(texto);
                JOptionPane.showMessageDialog(null,"Archivo Leido");  
                    }
        }
//------------------------------------------------------------------------------SENTENCIA NUEVA VENTANA      
        else if(buttonPressed.equals("Nueva Ventana")){
            Principal Traer1 = new Principal();
            Traer1.go();
        }
//------------------------------------------------------------------------------SENTENCIA NUEVA AREA DE TRABAJO
        else if(buttonPressed.equals("Nuevo")){
            ta.setText(null);            
        }
//------------------------------------------------------------------------------SENTENCIA IMPRIMIR
        else if(buttonPressed.equals("Imprimir")){
            
        }
//------------------------------------------------------------------------------  
        
  }
//------------------------------------------------------------------------------FIN VOID: EVENTOS
  
  
}