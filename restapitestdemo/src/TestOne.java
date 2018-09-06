import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

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

	

	StringTokenizer stringTokenizer;
	// Error
	// myarray2[0] = 1;

	double[] values = { 0.1, 5.2, 5.3, 6.3, 83.3 };

	public void method() throws FileNotFoundException {
		String[] mystring = { "Hello", "World", "There" };
		
		// One way 
		// Hello,World,There
		String joinedStr2 = String.join(",", mystring);
		System.out.println("String.join ==>" + joinedStr2); 

		// second way 
		/*[Hello, World, There]*/
		String joinedStr = Arrays.toString(mystring); 
		System.out.println("Arrays.toString ==>" + joinedStr); 
		
		
		/*[Hello, World, There]*/
		String joinedStr1 = Arrays.asList(mystring).toString(); 
		System.out.println("Arrays.asList.toString ==>" + joinedStr1);
		
		
		stringTokenizer = new StringTokenizer(joinedStr2, ",");

		// Error		
		while (stringTokenizer.hasMoreElements()) {
			System.out.println("Token : " + (stringTokenizer.nextElement()));
			/* Token : Hello
			Token : World
			Token : There */
		}

		/*myarray2[0] = 1;

		for (float f : myarray) {
			System.out.printf("%.2f \n", f);
		}

		System.out.println("\n Hello World !");

		// Scanner aScanner = new Scanner(System.in);
		Scanner aScanner = new Scanner(new File(filePath));
		// String string = aScanner.next();

		while (aScanner.hasNextLine()) {
			System.out.println(aScanner.nextLine());
		}*/

	}

}
