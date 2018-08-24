import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

public class FutureClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exeservice exeservice = new Exeservice();
		try {
			exeservice.runtask();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class Exeservice {

	public ExecutorService executorService = Executors.newSingleThreadExecutor();

	public void runtask() throws InterruptedException {

		Callable<String> task = () -> {
			for (int j = 0; j < 20; j++) {
				System.out.println("Waiting for sub-task to be finished " + j);
				Thread.sleep(1000);
			}
			return "Test";
		};

		FutureTask<String> fTask = new FutureTask<String>(task);

		executorService.execute(fTask);

		for (int i = 0; i < 10; i++) {
			System.out.println("Executing main task" + i);
			Thread.sleep(1000);
		}

		try {
			System.out.println("======== waiting for result ========");
			System.out.println(fTask.get());

			for (int i = 0; i < 5; i++) {
				System.out.println("After Get" + i);
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
