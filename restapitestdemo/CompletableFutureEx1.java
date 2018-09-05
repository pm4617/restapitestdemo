import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.omg.Messaging.SyncScopeHelper;

public class CompletableFutureEx1 {

	class Example1 {

		ArrayList<Future<Void>> futures;

		void function() throws InterruptedException, ExecutionException {

			futures = new ArrayList<>();

			for (int i = 0; i < 5; i++) {
				int finalN = i;
				CompletableFuture<Void> cf = new CompletableFuture<>();

				// store completable future in array
				futures.add(cf);

				// create 5 threads and start it to execute mytask() function ,
				// passing thread number and completable future
				new Thread(() -> {
					try {
						mytask(finalN, cf);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();

			}

			new Thread(() -> {

				for (Future f : futures) {

					try {
						f.get(2, TimeUnit.SECONDS);
					} catch (InterruptedException | ExecutionException | TimeoutException e) {
 						//e.printStackTrace();
 						System.out.println("Thread timeout");
					}
					System.out.println("."+f.toString());

				}
				System.out.println("End of all futures mytask");

			}).start();

		}

		private void mytask(int i, CompletableFuture<Void> future) throws InterruptedException {

			System.out.println("Starting thread " + i);
			Thread.sleep(5000);
			System.out.println("Completing thread " + i);

			// after task is complete complete future which is passed in
			future.complete(null);

		}

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFutureEx1 cFutureEx1 = new CompletableFutureEx1();

		CompletableFutureEx1.Example1 example1 = cFutureEx1.new Example1();

		example1.function();

		System.out.println("End of main");

	}

}
