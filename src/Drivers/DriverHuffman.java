package Drivers;

import CapaDomini.ModelDomini.Huffman;
import CapaDomini.ModelDomini.Pair;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Juanjo.Navarro
 */
public class DriverHuffman {

     private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
       int opcion;
        boolean salir=false;

        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Comprobar funcionamiento Huffman."
                         + "\n\t2. Salir.");

            opcion = Integer.parseInt(intro.readLine());
            int p1, p2;
            Huffman h = new Huffman();
            ArrayList<Pair<Byte,Short>> contingut = new ArrayList<>(8);
            Pair<Byte,Short> p;
            byte[] huff_cod;
            switch(opcion) {
                case 1: {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Introdueix el contingut a codificar (8 pars bytes) {num num}: ");
                    for(int i=0; i<7; i++){
                        p1 = sc.nextInt();
                        p2 = sc.nextInt();
                        p = new Pair(p1, p2);
                        contingut.add(p);
                    }
                    huff_cod = h.huffmanEncode(contingut);

                    System.out.println("S'ha codificat correctament");
                    System.out.println("Ara decodificarem per veure que obtenim el mateix resultat");

                    //contingut = h.huffmanDecode(huff_cod, 8);

                    System.out.println("Resultat obtingut");
                    for(Pair par : contingut){
                        System.out.println(par.getFirst()+", "+par.getSecond());
                    }
                    sc.close();
                    break;
                }
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Introdueix una opció correcta");
                    break;

            }

        }
    }
}
