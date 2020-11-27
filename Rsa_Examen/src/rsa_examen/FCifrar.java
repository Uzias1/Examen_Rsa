package rsa_examen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.regex.Pattern;
import javax.swing.*;
import proceso.Rsa;
/**
 *
 * @author uzias
 */
public class FCifrar extends JFrame{
    JButton titulo;
    JButton cifrar;
    
    JLabel mensaje, instrucciones, tamanio;
    
    JTextArea m;
    
    JTextField t;
    
    Font tit = new Font("Action Man", Font.PLAIN, 40);
    Font txt = new Font("Action Man", Font.PLAIN, 20);
    Font ins = new Font("Action Man", Font.PLAIN, 30);
    public FCifrar(){
        proceso();
        this.setPreferredSize(new Dimension(1100, 700));
        this.setBackground(Color.decode("#D2D7DF"));
        this.setLayout(null);
        
        titulo = new JButton("CIFRADO");
        titulo.setBackground(Color.decode("#30343F"));
        titulo.setBounds(0, 0, 1100, 140);
        titulo.setFont(tit);
        titulo.setForeground(Color.white);
        titulo.setBorderPainted(false);
        add(titulo);
        
        instrucciones = new JLabel("Escriba su mensaje y el tamaño de los primos, posteriormente de click en cifrar");
        instrucciones.setFont(ins);
        instrucciones.setBounds(20, 160, 1100, 40);
        add(instrucciones);
        
        mensaje = new JLabel("Mensaje: ");
        mensaje.setFont(txt);
        mensaje.setBounds(20, 220, 200, 40);
        add(mensaje);
        
        m = new JTextArea();
        m.setLineWrap(true);
        m.setFont(txt);
        m.setBounds(240, 220, 600, 340);
        add(m);
        
        tamanio = new JLabel("Tamaño del primo:");
        tamanio.setFont(txt);
        tamanio.setBounds(20, 580, 200, 40);
        add(tamanio);
        
        t = new JTextField();
        t.setFont(txt);
        t.setBounds(240, 580, 600, 40);
        add(t);
        
        cifrar = new JButton("Cifrar");
        cifrar.setFont(ins);
        cifrar.setBackground(Color.decode("#273469"));
        cifrar.setForeground(Color.white);
        cifrar.setBounds(860, 360, 220, 140);
        cifrar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                //primero hay que validar la cadena y el tamaño
                String siu = m.getText();
                String cl = t.getText();
                
                boolean c1 = validaMensaje(siu);
                boolean c2 = validaTama(cl);
                
                if (c1 && c2){
                    //entonces se hacen los archivos siuuuuuu
                    int l = Integer.parseInt(cl);
                    cifra(siu, l);
                    JLabel mensajexd = new JLabel("Archivos generados con exito");
                    mensajexd.setFont(ins);
                    JOptionPane.showMessageDialog(null, mensajexd, "Correcto", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JLabel mensajexd = new JLabel("Ingresa un mensaje valido, sin simbolos o números y un numero posible");
                    mensajexd.setFont(ins);
                    JOptionPane.showMessageDialog(null, mensajexd, "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(cifrar);
    }
    public void proceso(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cifrado");
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (pantalla.height)/2;
        int width = (pantalla.width)/2;
        setMinimumSize(new Dimension(1100, 700)); //cuanto medirá
        this.setLocation(width-550, height-350);//en donde la pongo
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
    
    public boolean validaMensaje(String mensaje){
        boolean valido = false;
        String exp = "^[a-zA-Z\\s\\u00f1\\u00d1]{1,888}";//solo las letras y solamente hasta 231 caracteres xd
        Pattern p = Pattern.compile(exp);
//            if (p.matcher(msj).matches() && p.matcher(clave).matches() ){
        if (p.matcher(mensaje).matches()){
            //aqui tenemos un mensaje valido
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }
    
    public boolean validaTama(String tam){
        boolean pasa = true;
        try {
            int num = Integer.parseInt(tam);
            //con eso sabemos que es un numero, ahora a ver si no es negativo o decimal
            //no es negativo
            if (num < 0){
                pasa = false;
            }
            //no es decimal
            if (num%1 != 0){
                pasa = false;
            }
            //para que no se pasen xd
            if (num > 1000000){
                pasa = false;
            }
        } catch(Exception e){
            pasa = false;
        }
        return pasa;
    }
    
    public void cifra(String mensaje, int longitud){
        //primero se generan los primos
        Rsa rsaobj = new Rsa(longitud);
        rsaobj.generarPrimos();
        
        //genero las claves
        rsaobj.generarClaves();
        
        //cifrar
        BigInteger[] cifradou = rsaobj.encriptar(mensaje);
        String[] aux = new String[cifradou.length];
        for (int i = 0; i < cifradou.length; i++) {
            aux[i] = cifradou[i].toString();//lo paso primero a string B)
        }
        
        BigInteger bip = rsaobj.damep();
        BigInteger biq = rsaobj.dameq();
        BigInteger bie = rsaobj.damee();
        //en teoria ya esta bastante cifrado eso SIUUU
        try {
            
            //BigInteger big = new BigInteger("515377520732011331036461129765621272702107522001");
            OutputStream fos = new FileOutputStream("cifrado.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(cifradou);
            fos.close();

            
            
            FileOutputStream out = new FileOutputStream("cifrado.txt");
            byte[] ar;
            
            for (int i = 0; i < cifradou.length; i++) {
                ar = aux[i].getBytes();
                out.write(ar);
            }
            //peeeeero tambien tengo que guardar los numerotes 
            FileOutputStream outp = new FileOutputStream("numeros.txt");
            FileOutputStream outq = new FileOutputStream("numeros2.txt");
            FileOutputStream oute = new FileOutputStream("numeros3.txt");
            
            String aux3 = bie.toString();
            byte[] charArray3;
            
            charArray3 = aux3.getBytes();
            oute.write(charArray3);
            
            String aux1 = bip.toString();
            byte[] charArray;
            
            charArray = aux1.getBytes();
            outp.write(charArray);
            
            
            String aux2 = biq.toString();
            byte[] charArray2;
            
            charArray2 = aux2.getBytes();
            outq.write(charArray2);
            
//            String xd = rsaobj.desencriptar(cifradou);
//            System.out.println("Es "+xd);
//            
            outp.close();
            outq.close();
            oute.close(); 
            out.close();
        } catch(Exception e){
            System.out.println("Error master");
        }
    }
}
