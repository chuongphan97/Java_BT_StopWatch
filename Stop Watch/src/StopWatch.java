import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class StopWatch {
    Instant startTime, endTime;
    Duration duration;
    boolean isRunning = false;

    public void start(){
        if (isRunning) {
            throw new RuntimeException("Stopwatch is already running.");
        }
        this.isRunning = true;
        startTime = Instant.now();
    }
    public Duration stop(){
        this.endTime = Instant.now();
        if (!isRunning) {
            throw new RuntimeException("Stopwatch has not been started yet");
        }
        isRunning = false;

        Duration result = Duration.between(startTime,endTime);

        if (this.duration == null){
            this.duration = result;
        } else {
            this.duration = duration.plus(result);
        }
        return this.getElapsedTime();
    }

    public Duration getElapsedTime(){
        return this.duration;
    }
    public void reset(){
        if (this.isRunning) {
            this.stop();
        }
        this.isRunning = false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)Math.round(Math.random()*99);
        }
        StopWatch sw = new StopWatch();
        System.out.println("Start sort!");
        sw.start();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1;  j < arr.length ; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }


        sw.stop();
        System.out.println(sw.getElapsedTime().toMillis() + " ms");
    }
}
