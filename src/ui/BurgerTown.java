package ui;

import java.util.Scanner;

public class BurgerTown {
    public static Scanner reader;
    public static double[] prices;
    public static int[] units;
    public static void main(String[] args) {
        initializeGlobals();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void initializeGlobals() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Welcome to BurgerTown!");

        stablishQuantitySold();

        boolean exit = false;

        do {

            System.out.println("\nMain Menu:");
            System.out.println("1. Ask prices ($) and quantities of each dish throughout the day");
            System.out.println("2. Calculate the total quantity of dishes sold throughout the day");
            System.out.println("3. Calculate the average price of the dishes sold throughout the day");
            System.out.println("4. Calculate the total sales (money recieved) throughout the day");
            System.out.println("5. Ask the number of dishes throughout the day that surpassed a minimum threshold of sales");
            System.out.println("6. Exit");
            System.out.println("\nEnter the action to execute");
            int option = reader.nextInt();

            switch (option) {
                case 1:
                    solicitData();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de platos vendidos en el dia fue de: "+calculateTotalDishesSold());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de los platos vendidos en el dia fue de: "+calculateAveragePrice(calculateTotalDishesSold()));
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calculateTotalsales(calculateAveragePrice(calculateTotalDishesSold()),calculateTotalDishesSold()));
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limit = reader.nextDouble();
                    System.out.println("\nDe las "+prices.length+" referencias vendidas en el dia, "+consultDishesAboveLimit(limit)+" superaron el limite minimo de ventas de "+limit);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    exit = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!exit);

    }

    /**
     * Description: This method asks the user for the ammount of different dishes served throughout the day 
     * pre: scanner must be declared
     * pre: the price and units arrays must be declared
     * pos: the price and units arrays will be initialized for further processes
     */
    public static void stablishQuantitySold() {

        System.out.println("\nInput the number of different dishes sold throughout the day ");
        int dishes = reader.nextInt();

        prices = new double[dishes];
        units = new int[dishes];
        
    }
    /**
     * Descrition: this method asks the user for the price and units of a dish sold throughout the day 
     * pre: scanner must be declared
     * pre: the price and units arrays must be initialized
     */
    public static void solicitData(){
        int i = 0;
        while (prices.length > i){
            System.out.println("\nPlease input the price of the dish: " + (i+1));
            prices[i] = reader.nextDouble();

            System.out.println("\nPlease input the units of the dish: " + (i+1));
            units[i] = reader.nextInt();
            i = i+1;

        }
        
    }
    /**
     * Descrition: this method calculates the total of dishes sold with the units array
     * pre: the price and units arrays must be initialized
     */

    public static int calculateTotalDishesSold(){
        int i = 0;
        int sumUnits = 0;
        for(i=0; i< prices.length; i++)
        {
            sumUnits = sumUnits + units[i];
        }
        return sumUnits;
    }

    /**
     * Descrition: this method calculates the average price of each dish sold
     * pre: the price and units arrays must be initialized
     */
    public static double calculateAveragePrice(int u){
        int i = 0;
        Double sumUnits_D = Double.valueOf(u);
        Double sumPrices = 0.0;
        Double avgPrice = 0.0;
        for(i=0; i< prices.length; i++)
        {
            sumPrices = sumPrices + prices[i]*units[i];
        }
        avgPrice = sumPrices/sumUnits_D;

        return sumPrices;

    }
    /**
     * Descrition: this method calculates the total money earned
     * pre: the price and units arrays must be initialized
     */
    public static double calculateTotalsales(double avrgprice, int u){
        int i = 0;
        Double sumPrices = 0.0;
        for(i=0; i< prices.length; i++)
        {
            sumPrices = sumPrices + prices[i]*units[i];
        }
        return sumPrices;
    }
    /**
     * Descrition: this method tells the user how many kinds of dishes surpassed the minimum threshhold the user input i got lazy
     * pre: the price and units arrays must be initialized
     */
    public static int consultDishesAboveLimit(double limit){
        int i = 0;
        int d = 0;
        for(i=0; i< prices.length; i++)
        {
            if (Double.valueOf(prices[i]*units[i]) > limit){

                d = d+1;
            }
            
        }
        return d;
    }

}