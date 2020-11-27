package proceso;

//vamos a crear una clase que se encargue de realizar el calculo de 
//los numeros primos y primos relativos del algoritmo RSA


import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class Rsa{

    //variables
    int tamPrimo;
    BigInteger p, q, n;
    BigInteger phi;
    BigInteger e, d;

    //constructor de la clase
    public Rsa(int tamPrimo){
        this.tamPrimo = tamPrimo;
    }
    
    //tengo permitido pasar los primos y n
    public Rsa(BigInteger p, BigInteger q, BigInteger n){
        this.p = p;
        this.q = q;
        this.e = n;
    }

    //generar los primos
    public void generarPrimos(){
        p = new BigInteger(tamPrimo, 10, new Random());
        do q = new BigInteger(tamPrimo, 10, new Random());
            while(q.compareTo(p)==0);
        //System.out.println("El numero p: "+p);
        //System.out.println("El numero q: "+q);
        //wiii ya tengo mis numerotes 
    }

    //vamos a generar las claves
    public void generarClaves(){
        // n = p*q

        n = p.multiply(q);

        //phi = (p-1)*(q-1)

        phi = p.subtract(BigInteger.valueOf(1)); //p-1

        phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));

        //ahora hay que calcular el coprimo para e a partir del mcd

        do{ 
            e = new BigInteger(2*tamPrimo, new Random());
        } while(((e.compareTo(phi))!= -1) || (e.gcd(phi).compareTo(BigInteger.valueOf(1))!=0));
        //System.out.println(e);
            //calcular a d = e^ 1 mod(phi)
        d = e.modInverse(phi);
        

       // System.out.println("d en la generacion normal es "+d);
    }

    public void generarClavestuneadas(){
        //System.out.println("e es "+e);
        // n = p*q

        n = p.multiply(q);

        //phi = (p-1)*(q-1)

        phi = p.subtract(BigInteger.valueOf(1)); //p-1

        phi = phi.multiply(q.subtract(BigInteger.valueOf(1)));

        //ahora hay que calcular el coprimo para e a partir del mcd

        
        //System.out.println(e);
            //calcular a d = e^ 1 mod(phi)
        d = e.modInverse(phi);
        //System.out.println("d es "+d);


    }
    //cifrar

    public BigInteger[] encriptar(String mensaje){
        //variables
        int i; 
        byte[] temp = new byte[1];
        byte[] digitos = mensaje.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];

        //lo primero es recorrer a bigdigitos para integrarlos al temporal
        for(i = 0; i<bigdigitos.length; i++){
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        BigInteger[] cifrado = new BigInteger[bigdigitos.length];

        //ahora que se el tamaño vamos a cifrarlo
        for(i = 0; i<bigdigitos.length; i++){
            //ciframos
            cifrado[i] = bigdigitos[i].modPow(e, n);
        }

        return (cifrado);
    }

    //descifrar

    public String desencriptar(BigInteger[] cifrado){
        
        BigInteger[] descifrado = new BigInteger[cifrado.length];

        //primero tenemos que recorrerlo

        for(int i = 0; i<descifrado.length; i++){
            //aplicado el descifrado
            descifrado[i] = cifrado[i].modPow(d,n);
           // System.out.println("aqui hay "+descifrado[i]);
        }

        //vamos a necesitar un arreglo de caracteres para toda la informacion
        char[] charArray = new char[descifrado.length];

        for(int i = 0; i<charArray.length; i++){
            charArray[i] = (char)(descifrado[i].intValue()); 
            //System.out.println("vamos en "+charArray[i]);
        }
        return (new String(charArray));
    }

    //los metodos para enviar p, q, n, phi, e, d

    public BigInteger damep(){
        return p;
    }

    public BigInteger dameq(){
        return q;
    }

    public BigInteger damephi(){
        return phi;
    }

    public BigInteger damen(){
        return n;
    }

    public BigInteger damee(){
        return e;
    }

    public BigInteger damed(){
        return d;
    }

}