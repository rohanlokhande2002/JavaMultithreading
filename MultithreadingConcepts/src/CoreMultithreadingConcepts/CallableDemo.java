package CoreMultithreadingConcepts;

import java.util.concurrent.*;

// In callable is used it is similar to runnable use to create thread
// but unlike runnable interface callable can return the value
// In the below example crated threads using the executor service
// and create the task which can return the value by implementing the callable interface

// here Future object is used to store the data that maybe underprocessing
// and we have to provide the same type of object/data which callable is going to return


///////////////#################################///////////////////////////////////
// here important catch is that get() method is used that can block main thread to process
// other task, so we don't prefer to use it anywhere
// somewhere in the future maybe thread returns the value and then main thread can start working again
///////////////#################################///////////////////////////////////

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        try(ExecutorService service = Executors.newFixedThreadPool(3)){
            Future<Integer> rs = service.submit(new ReturnTask());


            System.out.println(rs.get());
//            To verify the whether it blocks the main thread or not
            System.out.println("Main thread started working completed");


//            rs.cancel(true);
//            boolean cancelled =  rs.isCancelled();
//            boolean done = rs.isDone();

        }
    }
}

class ReturnTask implements Callable<Integer> {

    private final Integer data = 15;
    @Override
    public Integer call() throws Exception{
        Thread.sleep(3000);
        return data;
    }
}
