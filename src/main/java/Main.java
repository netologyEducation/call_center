import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int TIMEOUT_CALL = 1000;

    private static final int NUMBER_INCOMING_CALLS = 60;

    private static final int WORKING_ITERATIONS = 10;

    private static final int MANAGERS_COUNT = 100;

    public static void main(String[] args) {

        Ats ats = new Ats();
        List<Thread> managers = new ArrayList<>();

        Thread calling = new Thread(() -> {
            for (int i = 1; i <= WORKING_ITERATIONS; i++) {
                for (int j = 1; j <= NUMBER_INCOMING_CALLS; j++) {
                    ats.newCall();
                }
                try {
                    Thread.sleep(TIMEOUT_CALL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        calling.start();

        for (int i = 0; i <= MANAGERS_COUNT; i++) {
            managers.add(new Thread(() -> {
                while (calling.isAlive() || ats.getCountCalls() > 0) {
                    ats.answerCall();
                }
            }));
            managers.get(i).start();
        }
    }
}
