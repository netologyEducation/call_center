import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ats {

    private static final int ANSWER_TIMEOUT = 3000;
    private static Queue<Call> calls = new ConcurrentLinkedQueue<>();

    protected void newCall() {
        System.out.printf("Новый входящий звонок от %s...\n", Thread.currentThread().getName());
        calls.offer(new Call());
    }

    protected void answerCall() {
        try {
            Call call = calls.poll();
            if (call != null) {
                System.out.printf("Звонок обработал %s\n. Звонков в очереди %s\n", Thread.currentThread().getName(),
                        getCountCalls());
            } else {
                System.out.println("Тишина, звонков в ожидании нет...");
            }
            Thread.sleep(ANSWER_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected int getCountCalls() {
        return calls.size();
    }
}
