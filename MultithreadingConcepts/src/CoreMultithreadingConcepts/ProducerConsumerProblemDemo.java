package CoreMultithreadingConcepts;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


// here is the producer consumer problem
// where the producer produce the data and consumer consumes the data
// from the same buffer that is container in the below given example
// here producer consumer problem show the use case of the lock and notify

// Below problem shows when the producer produces it notify the consumer
// when the container is full the consumer starts consuming data from container
// similarly when the container is empty the consumer wait till the producer produces the data
// and the process goes on

// below example is running in infinite time which is not the actual case
// but this example keeps it for demo purpose



public class ProducerConsumerProblemDemo {

    public static void main(String args[]){
        Worker worker = new Worker(5,0);

        Thread producer = new Thread(() -> {
            try{
                worker.producer();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(()->{
            try{
                worker.consumer();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        producer.start();;
        consumer.start();
    }
}

    class Worker{
        private int sequence = 0;
        private final Integer top;
        private final Integer bottom;
        private final List<Integer> container;
        private final Object lock = new Object();

        public Worker(Integer top, Integer bottom){
            this.top = top;
            this.bottom = bottom;
            this.container = new ArrayList<>();
        }

        public void producer() throws InterruptedException {

            synchronized (lock) {
                while(true){
                    if(container.size() == top){
                        System.out.println("Container full, waiting for items to be removed");
                        lock.wait();
                    }else{
                        System.out.println(sequence + " added to the container");
                        container.add(sequence++);
                        lock.notify();
                    }
                    Thread.sleep(500);
                }
            }

        }

        public void consumer() throws InterruptedException{
            synchronized (lock) {
                while(true){
                    if(container.size() == bottom){
                        System.out.println("Container is empty, waiting for items to be added");
                        lock.wait();
                    }else {
                        System.out.println(container.removeFirst() + " removed from the container");
                        lock.notify();
                    }

                    Thread.sleep(500);
                }
            }
        }

    }



