package CoreMultithreadingConcepts;

// Creating threads by inheriting the thread class is not recommended
// Because java doesn't allow multiple inheritance

public class ThreadDemo {
    public static void main(String[] args) {
        Thread one = new UThreadOne();
        Thread two = new UThreadTwo();

        one.start();
        two.start();
    }
}

class UThreadOne extends Thread{
    public void run(){
        for(int i= 0; i < 10; i++){
            System.out.println("Thread one : " + i);
        }
    }
}

class UThreadTwo extends Thread{
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("Thread two : " + i);
        }
    }
}