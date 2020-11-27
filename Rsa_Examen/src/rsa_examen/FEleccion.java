package rsa_examen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author uzias
 */
public class FEleccion extends JFrame{
    JButton titulo;
    JButton op1, op2;
    
    JLabel instrucciones;
    public FEleccion(){
        proceso();
//        PEleccion obj = new PEleccion(new eventoCerrar());
//        this.add(obj);
//        this.pack();
        this.setPreferredSize(new Dimension(800, 500));
        this.setBackground(Color.decode("#D2D7DF"));
        this.setLayout(null);
        
        Font tit = new Font("Action Man", Font.PLAIN, 40);
        Font txt = new Font("Action Man", Font.PLAIN, 20);
        Font ins = new Font("Action Man", Font.PLAIN, 30);
        
        titulo = new JButton("CIFRADO RSA");
        titulo.setBackground(Color.decode("#30343F"));
        titulo.setBounds(0, 0, 800, 140);
        titulo.setFont(tit);
        titulo.setForeground(Color.white);
        titulo.setBorderPainted(false);
        add(titulo);
        
        instrucciones = new JLabel("Elige una opcion:");
        instrucciones.setFont(ins);
        instrucciones.setBounds(20, 160, 800, 70);
        add(instrucciones);
        
        op1 = new JButton("Cifrar");
        op1.setFont(ins);
        op1.setBackground(Color.decode("#273469"));
        op1.setForeground(Color.white);
        op1.setBounds(66, 280, 300, 140);
        op1.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                FCifrar obj = new FCifrar();
                obj.setVisible(true);
            }
        });
        op1.addActionListener(new eventoCerrar());
        add(op1);
        
        op2 = new JButton("Descifrar");
        op2.setFont(ins);
        op2.setBackground(Color.decode("#273469"));
        op2.setForeground(Color.white);
        op2.setBounds(432, 280, 300, 140);
        op2.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                JDescifrar obj = new JDescifrar();
                obj.setVisible(true);
            }
        });
        op2.addActionListener(new eventoCerrar());
        add(op2);
    }
    
    public void proceso(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cifrado con RSA");
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (pantalla.height)/2;
        int width = (pantalla.width)/2;
        setMinimumSize(new Dimension(800, 500)); //cuanto medirá
        this.setLocation(width-400, height-250);//en donde la pongo
        setResizable(false);
        pack();
    }
    class eventoCerrar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            dispose();
            hide();
        }
    }
}
