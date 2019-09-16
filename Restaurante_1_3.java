public class Restaurante {



    private int limiteComensales=0;
    private int comensalesActuales=0;


	public Restaurante(int limiteComensales, String name, int i) {
		this. limiteComensales =  limiteComensales;
		this.comensalesActuales = 0;


	}

    public synchronized  void ocupar(Reserva reserva, int i, String tiene_prioridad)throws InterruptedException {
        while (comensalesActuales+reserva.getLimite() > limiteComensales){
            System.out.println("    "+reserva.getName()+i+" ESPERANDO COMENSALES");
            wait();
        }
        comensalesActuales += reserva.getLimite();
        System.out.println("LIMITE TOTAL COMENSALES "+limiteComensales+" NOMBRE RESERVA: "+reserva.getName()+i+"[comensales actuales "+comensalesActuales+"] ENTRAN A RESTAURANTE "+reserva.getLimite()+" CLIENTE/S"+tiene_prioridad);
    }



    public synchronized void salir(Reserva reserva, int i, String tiene_prioridad){
        comensalesActuales -= reserva.getLimite();
        System.out.println("LIMITE TOTAL COMENSALES"+limiteComensales+" NOMBRE RESERVA: "+reserva.getName()+i+"[comensales actuales "+comensalesActuales+"] SALEN DE RESTAURANTE "+reserva.getLimite()+"CLIENTE/S "+tiene_prioridad );
        notifyAll();
    }

}
