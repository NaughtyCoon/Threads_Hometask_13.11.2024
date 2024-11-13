import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException{

        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable incTask = ()->{
            for (int i = 1; i < 1000; i++){
                counter.increment();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Runnable decTask = ()->{
            for (int i = 1; i < 1000; i++){
                counter.decrement();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        executor.submit(incTask);
        executor.submit(decTask);

        executor.shutdown();

        while (!executor.isTerminated()) { }   // Ожидаем завершения всех задач}

        System.out.println("Итоговое значение счётчика: " + counter.getCount());
    }
}