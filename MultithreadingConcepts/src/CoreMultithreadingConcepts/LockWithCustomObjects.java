package CoreMultithreadingConcepts;


// In the synchronizationDemo we have used the synchronized keyword to lock the critical area
// but there we have two threads using same resource, and we have added the synchronization over a method
// but in the current example we have two different threads and two different resources but if we add the
// synchronization over a method we then it will also make wait for other threads as well so to avoid this
// we can add the synchronization over a specific code block if we used it then it demands the object that we
// can pass as given in the below example

public class LockWithCustomObjects {

    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void IncrementOne(){
        synchronized (lock1){
            counter1++;
        }
    }

    public static void IncrementTwo(){
        synchronized(lock2){
            counter2++;
        }
    }

    public static void main(String[] args) {
        Thread one = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                IncrementOne();
            }
        });

        Thread two = new Thread(()->{
            for(int i = 0; i < 50000; i++){
                IncrementTwo();
            }
        });

        one.start();
        two.start();

        try{
            one.join();
            two.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("Counter1 value : "  + counter1);
        System.out.println("Counter2 value : "  + counter2);

    }
}
