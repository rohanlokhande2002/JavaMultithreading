package CoreMultithreadingConcepts.JavaMemoryModel;

// ###############--------ATOMICITY-----################

// Here we are going to see how we can maintain the atomicity
// remember volatile only guarantees the latest value will be flushed into main memory
// but let's consider the scenario where two or more threads perform the operation
// on the shared data how we can make sure atomicity

// There are two ways like keep the operation block synchronized
// means only one thread can perform operation
// use the atomicInteger which use the CAS(Compare and swap) CPU instruction

// Internally AtomicInteger let's compare the original value with expected values
// after operation and if the original does not match then it reiterates the loop
// Unlike the synchronized it does not make the other threads block to perform
//
//    [ Thread A Reads Value: 10 ]
//            │
//            ▼
//            [ Computes New Value: 11 ]
//            │
//            ▼
//            Does CAS(Expected=10, New=11)
//            │
//            ┌────────┴────────┐
//            SUCCESS            FAIL (Another thread changed it first!)
//            │                 │
//            ▼                 ▼
//            [ Value is now 11 ]   [ Loop back: Read newest value and try again ]

// But definitely if we want to perform complex operation Synchronized is more preferable

//Here if run comment the count.incrementAndGet() method and uncomment the code count.Set(count.get() + 1)
// and run the code you will each time different output now why do we get different data
// that is because of the AtomicInteger guarantees the atomicity at each operation but it
// does not guarantee the atomicity at combined operation like set is made of two operation
// that get the value increment by 1 and set always make sure to use individual operation on the
// AtomicInteger



import java.util.concurrent.atomic.AtomicInteger;

public class AtomicityDemo {


    public static void main(String[] args){

//        public static int count = 0;
//        public synchronized static void increment(){
//            count++;
//        }

        AtomicInteger count = new AtomicInteger(0);

        Thread t1 = new Thread(()-> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
//                count.set(count.get() + 1);
//                increment();
            }
        });
        Thread t2 = new Thread(()-> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
//                count.set(count.get() + 1);
//                increment();
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println("Count value - " + count);

    }

}
