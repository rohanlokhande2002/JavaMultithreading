package CoreMultithreadingConcepts;

// join method is used to make the main/parent thread wait for the completion of the
// child thread before executing the next flow of the join program.
// here try to understand many threads can run simultaneously but after the join threads completes
// it can execute the next flow of the program
// consider join method as waitForCompletion method or CompletionThenContinue method.


public class JoinMethodUse {
    public static void main(String[] args) {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());

        one.start();
        two.start();

        try{
//            one.join();
            two.join();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("Thread two has completed execution");

    }
}
