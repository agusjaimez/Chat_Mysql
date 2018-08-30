/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swi.ng2;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 *
 * @author agustin
 */
public class Chat_interf {

    private JFrame ventana;
    private JButton btnEnviar;
    private JButton btnBorrar;
    private JTextArea txtarea;
    private JTextField txtfield;
    private ActionListener action;
    private KeyListener keypressed;
    private JCheckBox principio;
    private Conexion conexion;
    private String usuario;
    private JButton btn_salir;
    private Login log;
    private Timer timer;
    private JScrollPane scroll;

    public Chat_interf(String texto, String usuario) {
        this.usuario = usuario;
        conexion = new Conexion();
        initcomponentes();
        this.txtarea.setText(texto);
        ventana.setVisible(true);
    }

    public void initcomponentes() {
        this.ventana = new JFrame();
        this.btnBorrar = new JButton();
        this.btnEnviar = new JButton();
        this.txtarea = new JTextArea();
        this.txtfield = new JTextField();
        this.principio = new JCheckBox();
        this.btn_salir = new JButton();

        this.ventana.setSize(800, 600);
        this.ventana.setResizable(false);
        this.ventana.setLayout(null);
        this.ventana.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.ventana.setTitle("Chat" + " - Logged as: " + usuario);
        this.ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.btnEnviar.setText("Enviar");
        this.btnEnviar.setBounds(560, 550, 100, 30);
        this.ventana.add(this.btnEnviar);

        this.btnBorrar.setText("Borrar");
        this.btnBorrar.setBounds(680, 550, 100, 30);
        this.ventana.add(this.btnBorrar);

        this.txtarea.setBounds(15, 10, 770, 440);
        this.txtarea.setEditable(false);
        this.scroll=new JScrollPane(txtarea);
        this.scroll.isWheelScrollingEnabled();
        this.scroll.setBounds(15,10,770,440);
        this.ventana.add(this.scroll);
        
        this.txtfield.setBounds(15, 500, 770, 30);
        this.ventana.add(txtfield);

        this.principio.setBounds(15, 460, 130, 30);
        this.principio.setText("Copiar al inicio");
        this.principio.setBackground(Color.LIGHT_GRAY);
        this.ventana.add(principio);

        this.btn_salir.setBounds(15, 550, 100, 30);
        this.btn_salir.setText("Salir");
        this.ventana.add(this.btn_salir);

        this.action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if (principio.isSelected() == false) {
                    if (txtfield.getText().equals("")) {
                    } else {
                        if (txtarea.getText().equals("")) {
                            try {
                                conexion.setMensajes(conexion.obtener(), usuario, txtfield.getText());
                            } catch (SQLException | ClassNotFoundException ex) {
                                Logger.getLogger(Chat_interf.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            txtarea.setText(usuario+": "+txtfield.getText());
                            txtfield.setText("");
                        } else {
                            try {
                                conexion.setMensajes(Conexion.obtener(), usuario, txtfield.getText());
                            } catch (SQLException | ClassNotFoundException ex) {
                                Logger.getLogger(Chat_interf.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            txtarea.setText(txtarea.getText() + "\n" + usuario +": "+txtfield.getText());
                            txtfield.setText("");
                        }
                    }
                } else {
                    if (txtarea.getText().equals("")) {
                        txtarea.setText(txtfield.getText());
                        txtfield.setText("");
                    } else {
                        txtarea.setText(usuario+": "+txtfield.getText() + "\n" + txtarea.getText());
                    }
                    {
                    }
                }
            }
        };
        this.btnEnviar.addActionListener(action);

        this.action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                txtfield.setText("");
            }
        };
        this.btnBorrar.addActionListener(action);

        this.keypressed = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent k) {
                if (k.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtfield.getText().equals("")) {
                    } else {
                        if (txtarea.getText().equals("")) {
                            try {
                                conexion.setMensajes(conexion.obtener(), usuario, txtfield.getText());
                            } catch (SQLException | ClassNotFoundException ex) {
                                Logger.getLogger(Chat_interf.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            txtarea.setText(usuario+": "+txtfield.getText());
                            txtfield.setText("");
                        } else {
                            try {
                                conexion.setMensajes(conexion.obtener(), usuario, txtfield.getText());
                            } catch (SQLException | ClassNotFoundException ex) {
                                Logger.getLogger(Chat_interf.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            txtarea.setText(txtarea.getText() + "\n" + usuario +": "+txtfield.getText());
                            txtfield.setText("");
                        }
                    }
                }
            }
        };

        this.txtfield.addKeyListener(keypressed);

        this.action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                log = new Login();
                ventana.dispose();
            }
        };
        this.btn_salir.addActionListener(action);
        
        int interval = 1000;
        
        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtarea.setText(conexion.getMensajes(Conexion.obtener()));
                } catch (SQLException ex) {
                    Logger.getLogger(Chat_interf.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Chat_interf.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        timer.start();

    }
}
