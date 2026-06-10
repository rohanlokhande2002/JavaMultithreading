package CoreMultithreadingConcepts.ExecutorServiceDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newFixedThreadPool(3)){
            for(int i = 0; i < 5; i++){
                service.submit(new tasks(i));
            }
        }
    }
}

class tasks implements Runnable{

    private final int taskId;

    public tasks(int taskId){
        this.taskId = taskId;
    }

    @Override
    public void run(){
        System.out.println("Task Id : - " + taskId + " Thread Name" + Thread.currentThread().getName());
    }
}