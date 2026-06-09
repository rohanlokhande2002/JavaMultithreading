package CoreMultithreadingConcepts.ExecutorServiceDemo;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Executor service is an interface is used to manage the thread pool using this
// we don't need to start the thrad and manage
// Here we have created the SingleThradedExecutor where only single thread will run
// and complete the task in the sequential order. But here if the thead is dead or stopped then
// it executor will create the new thread automatically

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