package CoreMultithreadingConcepts.JavaMemoryModel;


// #########-----Java Memory model------######

// So the core problem is that when two threads reads the value from the shared variable
// system does not guaranty that they read the latest value because each CPU core has separate
// caching and stack memory

// So the java memory model guaranteed here that whenever the latest write happen in the shared
// memory that is immediately flushed in the main memory so after operation can read the latest value
// from the shared resource/data

// Volatile here only guarantees the latest values will be seen by the thread
// So here Flag changed will immediately stop after three seconds

// What if the operation was increment instead of setting flag
// Does it guarantee that Answer is no

class SharedData {
    volatile boolean flag = false;
}

class MyThread extends Thread {
    SharedData data;

    MyThread(SharedData data) {
        this.data = data;
    }

    public void run() {
        while (!data.flag) {
            // waiting
            System.out.println("print data");
        }
        System.out.println("Flag changed!");
    }
}

public class VolatileDemo{
    public static void main(String[] args) throws Exception {
        SharedData data = new SharedData();
        MyThread t = new MyThread(data);
        t.start();

        t.sleep(3000);
        data.flag = true;
    }
}