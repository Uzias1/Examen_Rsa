package rsa_examen;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import proceso.Rsa;
/**
 *
 * @author uzias
 */
public class Rsa_Examen {

    /**
     * Deberán de crear una aplicación ya sea con sockets o websokets o serializable que se encargue de:


        Calcular los numero p, q, n, phi(n), e y d para que el usuario 1 pueda cifrar un mensaje numerico 
        * con e y este sea enviado al usuario 2, el cual debe de obtener en tiempo polinómico p, q, n, 
        * para calcular d, y con d poder decifrar el mensaje numerico que ha sido enviado.

        Recuerden que es una simulacion del algoritmo rsa para lo cual deben de entregar lo siguiente:

    1.- Repositorio de github
    2.- Minivideo explicando el procedimiento y algoritmo
    3.- Demostrar el funcionamiento
     */
    public static void main(String[] args) {
        // Aqui se genera un objeto para ka primera ventana
        FEleccion ele = new FEleccion();
        ele.setVisible(true);
        //String descifrado = rsa.desencriptar(cifrado);
        //ojo a esto XD
        //String des = rsaobj.desencriptar(cifradou);
        //System.out.println(des);
//        try {
//            FileInputStream inm = new FileInputStream("cifrado.txt");
//            FileInputStream inn = new FileInputStream("numeros.txt");
//            
//            uno = inn.read();
//        } catch(Exception e){
//            System.out.println("Muy buenas tardes, hay un error");
//        }
//        
        //Rsa rsaobj2 = new Rsa(uno, dos);
//        Scanner s = new Scanner(System.in);
//        System.out.println("Ingresa texto");
//        String mensajee = s.nextLine();
//        Rsa rsa = new Rsa(50);
//        rsa.generarPrimos();
//        rsa.generarClaves();
//        BigInteger[] cifrado = rsa.encriptar(mensajee);
//        //System.out.println(Arrays.toString(cifrado));
//        String descifrado = rsa.desencriptar(cifrado);
//        System.out.println("El descifrado: "+descifrado);
//        System.out.println("p: " + rsa.damep() + "\nq: " + rsa.dameq() + "\nn: " + rsa.damen() + "\nphi: " + rsa.damephi() + "\ne: " + rsa.damee() + "\nd: " + rsa.damed());
    } 
}
