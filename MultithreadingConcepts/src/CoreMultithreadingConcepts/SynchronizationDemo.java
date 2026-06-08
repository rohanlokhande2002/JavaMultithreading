package CoreMultithreadingConcepts;

// When multiple threads try to access the same resource at the same time
// So it can happen to have the race condition and create inconsistent results
// to avoid this we use the synchronized method or block to tell compiler
// only one thread can use this critical area at a time


public class SynchronizationDemo {

    private static int counter = 0;

    private static synchronized void Increment(){counter++;}

    public static void main(String[] args) {

        Thread one = new Thread(()-> {
            for(int i = 0; i < 50000; i++){
                Increment();
//                counter++;
            }
        });

        Thread two = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                Increment();
//                counter++;
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("Count value : " + counter);

    }
}
