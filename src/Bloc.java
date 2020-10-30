import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Uriel
 */
public class Bloc implements ActionListener, ItemListener {
  private Frame f;
  private MenuBar mb;
  private Menu m1;
  private Menu m2;
  private Menu m3;
  private MenuItem mi1;
  private MenuItem mi2;
  private MenuItem mi3;
  private MenuItem mi4;
  private MenuItem mi6;
  
  private MenuItem m21;
  private MenuItem m22;
  private MenuItem m23;
  private MenuItem m24;
  private MenuItem m25;
  private MenuItem m26;

  private MenuItem m31;
  
  private CheckboxMenuItem mi5;
  private TextArea ta;

    public Bloc() {
            addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    });
    }

  public void go() {
    f = new Frame("Bloc de Notas z.1");
    mb = new MenuBar();
    m1 = new Menu("Archivo");
    m2 = new Menu("Edicion");
    m3 = new Menu("Ayuda");
    mb.add(m1);
    mb.add(m2);
    mb.setHelpMenu(m3);
    f.setMenuBar(mb);

    mi1 = new MenuItem("Nuevo");
    mi2 = new MenuItem("Abrir");
    mi3 = new MenuItem("Guardar");
    mi4 = new MenuItem("Guardar como...");
    mi6 = new MenuItem("Salir");
    mi1.addActionListener(this);
    mi2.addActionListener(this);
    mi3.addActionListener(this);
    mi4.addActionListener(this);
    mi6.addActionListener(this);
    
    m21 = new MenuItem("Cortar");
    m22 = new MenuItem("Copiar");
    m23 = new MenuItem("Pegar");
    m24 = new MenuItem("Eliminar");
    m25 = new MenuItem("Seleccionar todo");
    m26 = new MenuItem("Fecha y hora");
    m21.addActionListener(this);
    m22.addActionListener(this);
    m23.addActionListener(this);
    m24.addActionListener(this);
    m25.addActionListener(this);
    m26.addActionListener(this);
    
    m31 = new MenuItem("Acerca de");
    m31.addActionListener(this);
    
    m1.add(mi1);
    m1.add(mi2);
    m1.add(mi3);
    m1.add(mi4);
    m1.addSeparator();
    m1.add(mi6);
    
    m2.add(m21);
    m2.add(m22);
    m2.add(m23);
    m2.add(m24);
    m2.addSeparator();
    m2.add(m25);
    m2.add(m26);
    
    m3.add(m31);

    mi5 = new CheckboxMenuItem("Persistent");
    mi5.addItemListener(this);
    m1.add(mi5);
    
    ta = new TextArea();
    f.add(ta, BorderLayout.CENTER);
    f.pack();

    f.setSize(500,500);
    f.setVisible(true);
    f.addWindowListener(new WindowAdapter(){
    public void windowClosing(WindowEvent we){
    System.exit(0);
    }
    });
  }

  public void actionPerformed( ActionEvent ae) {
    System.out.println("Opcion \"" + 
        ae.getActionCommand() + "\" elegida.");
    
    switch(ae.getActionCommand()){
        case "Nuevo":
            Instrucciones.nuevo(ta);
            break;
        case "Abrir":
            Instrucciones.abrir(ta);
            break;
        case "Guardar":
            break;
        case "Guardad como":
            Instrucciones.guardarComo(ta);
            break;
        case "Salir":
            Instrucciones.salir();
            break;
        case "Cortar":
            break;
        case "Copiar":
            break;
        case "Pegar":
            break;
        case "Eliminar":
            break;
        case "Seleccionar todo":
            break;
        case "Fecha y hora":
            Instrucciones.fechaYHora(ta);
            break;
        case "Acerca de":
            break;
    }
  }

  public void itemStateChanged(ItemEvent ie) {
    String state = "deselected";

    if (ie.getStateChange() == ItemEvent.SELECTED) {
      state = "selected";
    }
    System.out.println(ie.getItem() + " " + state);
  }

}