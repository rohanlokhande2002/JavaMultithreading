package CoreMultithreadingConcepts.ExecutorServiceDemo;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadedExecutorDemo {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newSingleThreadExecutor()){
            for(int i = 0; i < 5; i++){
                service.submit(new task(i));
            }
        }
    }
}

class task implements Runnable{

    private final int taskId;

    public task(int taskId){
        this.taskId = taskId;
    }

    @Override
    public void run(){
        System.out.println("Task Id : - " + taskId + " Thread Name" + Thread.currentThread().getName());
    }
}