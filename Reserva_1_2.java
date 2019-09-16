public class Reserva extends Thread {


  

    private Restaurante res;

    private int limite;
    private int i;
    private String tiene_prioridad;
    public Reserva(String name, int i, Restaurante res, String tiene_prioridad) {
        super(name);
      
        this.res=res;
        this.i=i;
        limite=(int)(Math.random()*9+1);
        this.tiene_prioridad=tiene_prioridad;
    }


    public int getLimite() {
        return limite;
    }
    public void run(){
        try {

            res.ocupar(this,i,tiene_prioridad);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {

            sleep(3000); //La persona permanece 3 segundos en restaurante.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        res.salir(this,i,tiene_prioridad);

    }


}
