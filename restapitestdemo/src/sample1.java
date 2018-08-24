import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class sample1 {

	public static void main(String[] args) {
		StringBuilder s1 = new StringBuilder();
		String s2 = "Test , is , for , the , issue";
		s1.append(s2);
		System.out.println(s1);
		s1.substring(4);
		int foundAt = s1.indexOf(s2);
		System.out.println(s1);
		System.out.println(foundAt);

		// Make a string a , b , c , d , e , f , g . h , i

		StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");

		StringTokenizer s3 = null;
		while (stringTokenizer.hasMoreTokens()) {

			s2 = stringTokenizer.nextToken();
			System.out.println(s2);
		}
		
		Collections.list(new StringTokenizer(s2, ",")).stream()
	      .map(token -> (String) token)
	      .collect(Collectors.toList());
		
		Collections.list(stringTokenizer).stream().map(token -> ((String) token).toUpperCase()).collect(Collectors.toList());
		
		
	}
}
