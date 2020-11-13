import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Phaser;
public class CyclistPhaser extends Phaser {
    public static final int ARRIVE_TO_GASSTATION_PHASE = 0;
    public static final int ARRIVE_TO_SALE_PHASE = 1;
    public static final int BACK_TO_GASSTATION_PHASE = 2;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case ARRIVE_TO_GASSTATION_PHASE:
                System.out.printf("%s - All %d friends arrived to the gas station (executed in %s)\n",
                        LocalTime.now().format(dateTimeFormatter), registeredParties,
                        Thread.currentThread().getName());
                break;
            case ARRIVE_TO_SALE_PHASE:
                System.out.printf("%s - All %d friends arrived to the sale (executed in %s)\n",
                        LocalTime.now().format(dateTimeFormatter), registeredParties,
                        Thread.currentThread().getName());
                break;
            case BACK_TO_GASSTATION_PHASE:
                System.out.printf("%s - All %d friends went back to the gas station (executed in %s)\n",
                        LocalTime.now().format(dateTimeFormatter), registeredParties,
                        Thread.currentThread().getName());
                return true;
        }
        return super.onAdvance(phase, registeredParties);
    }
}
