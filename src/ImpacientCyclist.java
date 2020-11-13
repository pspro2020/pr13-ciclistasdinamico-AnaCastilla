import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ImpacientCyclist implements Runnable {
    private final String name;
    private final Phaser phaser;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ImpacientCyclist(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        phaser.register();
        try {
            leavingHome();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while he was leaving his home and going to the gas station\n", name);
            return;
        }
        phaser.arriveAndDeregister();
        try {
            meetingSale();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going to the sale\n", name);
            return;
        }
        try {
            backToGasStation();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going back to the gas station\n", name);
            return;
        }
        try {
            backHome();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going back to his home from the gas station\n", name);
            return;
        }

    }

    private void leavingHome() throws InterruptedException {
        System.out.printf("%s - %s is leaving home\n",
                LocalTime.now().format(dateTimeFormatter), name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3) + 1);
        System.out.printf("%s - %s arrived to the gas station\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void meetingSale() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10) + 5);
        System.out.printf("%s - %s arrived to the sale\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void backToGasStation() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10) + 5);
        System.out.printf("%s - %s arrived back to the gas station\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void backHome() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3) + 1);
        System.out.printf("%s - %s is already in home\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }
}
