import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int NUMBER_OF_FRIENDS = 10;

    public static void main(String[] args) throws InterruptedException {

        int i;
        CyclistPhaser phaser = new CyclistPhaser();

        for (i = 0; i < NUMBER_OF_FRIENDS; i++) {
            new Thread(new Cyclist("Cyclist #" + (i+1), phaser), "Cyclist #" + (i+1)).start();
        }

        //Un ciclista que una vez que se salga de la gasolinera ya no va a esperar a nadie.
        i++;
        new Thread(new ImpacientCyclist("Cyclist #" + i, phaser)).start();


        //Un ciclista que se levanta tarde y para cuando sale de casa el resto de amigos, que ni lo esperaban,
        // ya han salido de la gasolinera. Los otros ciclistas lo esperarán en la venta.
        TimeUnit.SECONDS.sleep(18);
        i++;
        new Thread(new TardyCyclist("Cyclist #" + i, phaser)).start();


        //Un ciclista que para cuando trata de unirse al resto es demasiado tarde porque los otros ya han
        // llegado todos a la gasolinera y se han ido para casa.
        TimeUnit.SECONDS.sleep(30);
        i++;
        new Thread(new TardyCyclist("Cyclist #" + i, phaser)).start();

    }
}
