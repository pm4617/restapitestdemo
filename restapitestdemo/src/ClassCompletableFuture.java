import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Function;
import java.util.function.Supplier;

public class ClassCompletableFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Runatask runatask = new Runatask();
		runatask.runAsync();
	}
}

class Runatask {

	public void runAsync() throws InterruptedException, ExecutionException {

		// Subtask
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
			@Override
			public String get() {
				for (int j = 0; j < 30; j++) {
					 
						System.out.println("Waiting for subtask be finished " + j);
					//	System.out.println("THREAD Subtask " + Thread.currentThread().getName());
					 
				}
				return "Test";
			}
		});
		
		//  call back method called after subtask finished
		CompletableFuture<String> completableFuture2 = completableFuture.thenApplyAsync(new Function<String, String>() {

			@Override
			public String apply(String t) {
 				System.out.println(" after subtask finished - callback received");
			//	System.out.println("THREAD Callback  " + Thread.currentThread().getName());

				return "Call back received after completing subtask ";
			}
		});

		
		
		// main task
		for (int j = 0; j < 10; j++) {
			 
				System.out.println("Waiting for main be finished " + j);
			//	System.out.println("THREAD main  " + Thread.currentThread().getName());

		}

		// System.out.println(completableFuture.get());
		for (int j = 0; j < 10; j++) {
		//	System.out.println("THREAD after " + Thread.currentThread().getName());
			System.out.println("Waiting after get be finished " + j);
		}
		System.out.println(completableFuture2.get());

	}

}

/*
 * class Runatask {
 * 
 * public void runAsync() throws InterruptedException, ExecutionException {
 * 
 * CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new
 * Runnable() {
 * 
 * @Override public void run() {
 * 
 * for (int j = 0; j < 20; j++) {
 * System.out.println("Waiting for sub-task to be finished " + j); try {
 * Thread.sleep(10); } catch (InterruptedException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } }
 * 
 * } });
 * 
 * for (int j = 0; j < 20; j++) {
 * System.out.println("Waiting for main be finished " + j); try {
 * Thread.sleep(10); } catch (InterruptedException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } }
 * 
 * completableFuture.get();
 * 
 * 
 * 
 * for (int j = 0; j < 20; j++) {
 * System.out.println("Waiting after get be finished " + j); try {
 * Thread.sleep(10); } catch (InterruptedException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } } }
 * 
 * }
 */
