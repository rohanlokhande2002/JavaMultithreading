package CoreMultithreadingConcepts.ExecutorServiceDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// cached thread pool has synchronous queue
// whenever the task is created it is hold inside the queue
// cached Thread pool searches for the already created thread which are ideal
// and assigned those threads the task present inside the queue
// in case if no idle threads are there it creates the new threads and assign them a task

// and once task is finishes it terminates the threads which are idle
// so basically it has a autoscaling property

// as the thread creation comes with a cost, so it has some upper limit as well
// which we don't need to care because executor service handles it


public class CachedThreadPoolDemo {

    public static void main (String[] args){
        try (ExecutorService service = Executors.newCachedThreadPool()){
            for (int i = 0; i < 1000; i++){
                service.submit(new Tasks(i));
            }
        }
    }

}

class Tasks implements Runnable{

    private final int taskId;

    public Tasks(int taskId){
        this.taskId = taskId;
    }

    @Override
    public void run(){
        System.out.println("Task Id " + taskId + " " + Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
