package blocdenotas;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ventana = new Ventana();
                ventana.setSize(500, 500);
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.setVisible(true);
            }
        });
    }
}
