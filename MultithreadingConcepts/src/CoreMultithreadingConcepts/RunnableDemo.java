package CoreMultithreadingConcepts;


// Implementing the thread by runnable interface is recommended because
// it allows you to extend other classes as well as can implement multiple interfaces as well
// to support multiple inheritance

public class RunnableDemo {

    public static void main(String[] args){
        Thread one =  new Thread( new ThreadOne());
        Thread two = new Thread( new ThreadTwo());

        //one more way to implement the thread
        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    System.out.println("Thread three : " + i);
                }
            }
        });

        Thread four = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    System.out.println("Thread four : " + i);
                }
            }
        });

//        one.start();
//        two.start();
        three.start();
        four.start();
    }

}

class ThreadOne implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("thread one : " + i);
        }
    }
}

class ThreadTwo implements Runnable{
    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("thread two : " + i);
        }
    }
}