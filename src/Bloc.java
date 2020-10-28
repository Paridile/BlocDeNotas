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
  private CheckboxMenuItem mi5;

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
    
    m1.add(mi1);
    m1.add(mi2);
    m1.add(mi3);
    m1.add(mi4);
    m1.addSeparator();
    m1.add(mi6);

    mi5 = new CheckboxMenuItem("Persistent");
    mi5.addItemListener(this);
    m1.add(mi5);

    f.setSize(500,500);
    f.setVisible(true);
  }

  public void actionPerformed( ActionEvent ae) {
    System.out.println("Opcion \"" + 
        ae.getActionCommand() + "\" elegida.");

    if (ae.getActionCommand().equals("Salir")) {
      System.exit(0);
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
