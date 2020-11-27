package rsa_examen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.util.StringTokenizer;
import javax.swing.*;
import proceso.Rsa;
/**
 *
 * @author uzias
 */
public class JDescifrar extends JFrame{
    Font tit = new Font("Action Man", Font.PLAIN, 40);
    Font txt = new Font("Action Man", Font.PLAIN, 20);
    Font ins = new Font("Action Man", Font.PLAIN, 30);
    
    JButton titulo;
    JLabel instrucciones;
    
    JLabel p, q, m;
    JLabel p2, q2, m2, cadena, cadenaf;
    JButton obtener;
    
    public JDescifrar(){
        proceso();
        this.setPreferredSize(new Dimension(1100, 600));
        this.setBackground(Color.decode("#D2D7DF"));
        this.setLayout(null);
        
        titulo = new JButton("DESCIFRADO");
        titulo.setBackground(Color.decode("#30343F"));
        titulo.setBounds(0, 0, 1100, 140);
        titulo.setFont(tit);
        titulo.setForeground(Color.white);
        titulo.setBorderPainted(false);
        add(titulo);
        
        instrucciones = new JLabel("Este procceso es automatico, asegurate de tener los archivos primero");
        instrucciones.setFont(ins);
        instrucciones.setBounds(20, 160, 1100, 40);
        add(instrucciones);
        
        p = new JLabel("P detectado: ");
        p.setFont(txt);
        p.setBounds(20, 220, 200, 40);
        add(p);
        
        p2 = new JLabel();
        p2.setFont(txt);
        p2.setBounds(240, 220, 840, 40);
        add(p2);
        
        q = new JLabel("Q detectado: ");
        q.setFont(txt);
        q.setBounds(20, 280, 200, 40);
        add(q);
        
        q2 = new JLabel();
        q2.setFont(txt);
        q2.setBounds(240, 280, 840, 40);
        add(q2);
        
        obtener = new JButton("Descifrar");
        obtener.setFont(ins);
        obtener.setBackground(Color.decode("#273469"));
        obtener.setForeground(Color.white);
        obtener.setBounds(400, 400, 300, 150);
        obtener.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                descifra();
            }
        });
        add(obtener);
        
        cadena = new JLabel("Mensaje: ");
        cadena.setFont(txt);
        cadena.setBounds(20, 340, 200, 40);
        add(cadena);
        
        cadenaf = new JLabel();
        cadenaf.setFont(txt);
        cadenaf.setBounds(240, 340, 840, 40);
        add(cadenaf);
    }
    
    public void proceso(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Descifrado");
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (pantalla.height)/2;
        int width = (pantalla.width)/2;
        setMinimumSize(new Dimension(1100, 600)); //cuanto medirá
        this.setLocation(width-550, height-265);//en donde la pongo
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
    
    public void descifra(){
        try {
            FileInputStream in1 = new FileInputStream("cifrado.txt");
            String m = convertStreamToString(in1);
            //System.out.println(m);
            
            FileInputStream in2 = new FileInputStream("numeros.txt");
            String qarchivo = convertStreamToString(in2);
            //System.out.println(qarchivo);
            
            FileInputStream in3 = new FileInputStream("numeros2.txt");
            String parchivo = convertStreamToString(in3);
            //System.out.println(parchivo);
            
            FileInputStream in4 = new FileInputStream("numeros3.txt");
            String earchivo = convertStreamToString(in4);
            //System.out.println("estas ennn "+earchivo);
            //ya tengo los archivos leidos
            //los imprimo en la esa cosa
            p2.setText(qarchivo);
            q2.setText(parchivo);
            
            //ahora voy a usar el metodo metodoso
            //necesito los bigs de estos strings
            BigInteger n1 = new BigInteger(qarchivo);
            BigInteger n2 = new BigInteger(parchivo);
            //el big e
            BigInteger e = new BigInteger(earchivo);
            
            Rsa objrsa = new Rsa(n1, n2, e);
            objrsa.generarClavestuneadas();
            
            
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("cifrado.ser"));
            BigInteger[] bigReadBack = (BigInteger[]) inputStream.readObject();

            //assertThat(big).isEqualTo(bigReadBack);
            
            String letra = "";
            //System.out.println(m);
            StringTokenizer st=new StringTokenizer(m);
            BigInteger[] textoCifrado = new BigInteger[st.countTokens()];

            for(int i=0;i<textoCifrado.length;i++){
                letra = st.nextToken();
                textoCifrado[i]=new BigInteger(letra);
                //System.out.println("vamos en "+textoCifrado[i]);
            }
//            BigInteger[] big = new BigInteger[m.length()];
//            for (int i = 0; i < m.length(); i++) {
//                big[i] = new BigInteger(String.valueOf(m.charAt(i)));
//                System.out.println("Vamos en: "+big[i]);
//            }
            
            in1.close();
            in2.close();
            in3.close();
            in4.close();
            //ahora si viene lo chido
            
            String finalisimo = objrsa.desencriptar(bigReadBack);
            cadenaf.setText(finalisimo);
            
            FileOutputStream sal = new FileOutputStream("descifrado.txt");
            
            byte[] charArray2;
            
            charArray2 = finalisimo.getBytes();
            sal.write(charArray2);
            sal.close();
            //System.out.println("El final es "+finalisimo);
            
        } catch(Exception e){
            JLabel mensajexd = new JLabel("Asegurate de tener los archivos en la ubicación correcta");
            mensajexd.setFont(ins);
            JOptionPane.showMessageDialog(null, mensajexd, "Error", JOptionPane.WARNING_MESSAGE);
        }
        //no se como hacer esto xd
        //Rsa obj = new Rsa();
        /*
        BigInteger n2 = new BigInteger(n); donde n es un string
        */
    }
    
    public static String convertStreamToString(java.io.InputStream is) {
        if (is == null) {
            return "";
        }

        java.util.Scanner s = new java.util.Scanner(is);
        s.useDelimiter("\\A");

        String streamString = s.hasNext() ? s.next() : "";

        s.close();

        return streamString;
    }
}
