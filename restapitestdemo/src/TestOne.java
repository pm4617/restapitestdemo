import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Scanner;

public class TestOne {

	public static void main(String[] args) throws FileNotFoundException {

		Application application = new Application();

		application.method();

	}

}

class Application {

	String filePath = "test.txt";

	float[] myarray = new float[] { 1.2545f, 2.35454f, 5.78454f, 3.45454f };
	double[] myarray1 = new double[] { 1.9, 2.9 };

	double[] myarray2 = new double[1];

	// Error
	// myarray2[0] = 1;

	double[] values = { 0.1, 5.2, 5.3, 6.3, 83.3 };

	public void method() throws FileNotFoundException {
		myarray2[0] = 1;

		for (float f : myarray) {
			System.out.printf("%.2f \n", f);
		}

		System.out.println("\n Hello World !");

		// Scanner aScanner = new Scanner(System.in);
		Scanner aScanner = new Scanner(new File(filePath));
		// String string = aScanner.next();

		while (aScanner.hasNextLine()) {
			System.out.println(aScanner.nextLine());
		}

	}

}
