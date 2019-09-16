import java.util.Scanner;
public class Simulacion {

    public static void main(String Args[]) {

        Scanner teclado=new Scanner(System.in);
        System.out.print("Número de reservas en esta simulación: ");
        int numReservas=teclado.nextInt();

        System.out.println("Inicio simulacion restaurante");
        int i=0;

        Restaurante res = new Restaurante(10,"Reserva",i);
        //Restaurante res = new Restaurante(10,"Reserva");
        Reserva[] r=new Reserva[numReservas];

        for( i=0; i<numReservas;i++){ //Creo las reservas

            int num = (int) (Math.random() * 10 + 1);
            if (num > 5) {
                r[i]=new Reserva("Reserva",i,res,"Tiene prioridad");
                r[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                r[i]=new Reserva("Reserva",i,res,"No tiene prioridad");
                r[i].setPriority(Thread.MIN_PRIORITY);
            }

            r[i].start();

        }
        for( i=0; i<numReservas;i++){ //Inicializo los procesos
            try {
                r[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fin de la simulación.");




    }
}
