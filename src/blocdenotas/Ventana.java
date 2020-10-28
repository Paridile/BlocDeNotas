package ceis.cs.umss.edu.bo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Ventana extends JFrame{

    private JMenuBar menuBar;
    private JMenu archivo, ayuda;
    private JMenuItem nuevo, abrir, guardar, salir, acercaDe;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public Ventana() {
        super("Mi Bloc de Notas");

        menuBar = new JMenuBar();
        archivo = new JMenu("Archivo");
        ayuda = new JMenu("Ayuda");

        nuevo = new JMenuItem("Nuevo");
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        abrir = new JMenuItem("Abrir");

        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo();
            }
        });
        guardar = new JMenuItem("Guardar");
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
        });


        salir = new JMenuItem("Salir");

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        acercaDe = new JMenuItem("Acerca de...");

        acercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bloc de Notas hecho de Java Swing!!!");
            }
        });

        //Tocar armar el menu

        menuBar.add(archivo);
        menuBar.add(ayuda);

        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.addSeparator();
        archivo.add(salir);

        ayuda.add(acercaDe);

        textArea = new JTextArea();
        scrollPane =  new JScrollPane(textArea);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);

        setJMenuBar(menuBar);
    }

    private void guardarArchivo() {
        System.out.println("Guardar Archivo!!");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            FileWriter escritor = null;

            try {
                escritor = new FileWriter(archivo);
                escritor.write(textArea.getText());
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
    }

    private void abrirArchivo() {
        System.out.println("Abrir archivo!!!");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();

            try {
                List<String> lineas = Files.readAllLines(Paths.get(path));

                for (String linea: lineas) {
                    textArea.append(linea+"\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
