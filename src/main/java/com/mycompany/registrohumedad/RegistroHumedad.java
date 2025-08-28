package com.mycompany.registrohumedad;

import java.util.*; 

public class RegistroHumedad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] humedad = new double[7];    // Arreglo para guardar 7 valores de humedad

        // Leer 7 valores de humedad se realia un contador
        for (int i = 0; i < 7; i++) {
            while (true) {
                System.out.print("Dia " + (i + 1) + " - Humedad == 0 a 100: ");
                double valor = sc.nextDouble(); // Lee el valor ingresado
                if (valor >= 0 && valor <= 100) { // Verifica el rango permitido
                    humedad[i] = valor; // Guarda el valor en el arreglo
                    break; // Sale del ciclo interno
                } else {
                    System.out.println("Valor invalido. Intenta de nuevo."); 
                }
            }
        }

        // Leer el umbral U
        System.out.print("Ingresar el umbral U: ");
        double umbral = sc.nextDouble();

        // Variables para los calculos
        double max = humedad[0], min = humedad[0], suma = 0;
        int diamax = 0, diamin = 0, sobreUmbral = 0;
        boolean creciente = true, decreciente = true;

        // Recorrer el arreglo para calcular todo
        for (int i = 0; i < 7; i++) {
            double val = humedad[i];
            suma += val; // Suma para el promedio

            // Verifica si es el nuevo maximo
            if (val > max) {
                max = val;
                diamax = i;
            }

            // Verifica si es el nuevo minimo
            if (val < min) {
                min = val;
                diamin = i;
            }

            // Cuenta si esta encima del umbral
            if (val > umbral) {
                sobreUmbral++;
            }

            // Verifica la tendencia
            if (i > 0) {
                if (val < humedad[i - 1]) creciente = false;
                if (val > humedad[i - 1]) decreciente = false;
            }
        }

        //Determina la tendencia
        String tendencia = creciente ? "creciente" : (decreciente ? "decreciente" : "mixta");

        //Calcula el promedio con 2 decimales
        double promedio = Math.round((suma / 7) * 100.0) / 100.0;

        //Ordena una copia del arreglo
        double[] ordenado = Arrays.copyOf(humedad, 7);
        Arrays.sort(ordenado);

        //Muestra el reporte final
        System.out.println("\n--- Reporte ---");
        System.out.println("Maximo: " + max + " (dia " + (diamax + 1) + ")");
        System.out.println("Minimo: " + min + " (dia " + (diamin + 1) + ")");
        System.out.println("Promedio: " + promedio);
        System.out.println("Dias > U: " + sobreUmbral);
        System.out.println("Tendencia: " + tendencia);
        System.out.println("Ordenado: " + Arrays.toString(ordenado));
    }
}
