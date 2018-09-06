import javax.print.DocFlavor.STRING;

public class EqualsInJava {

	public static void main(String[] args) {

		int a = 100;
		int b = 100;
		System.out.println(a == b); //true
		
		

		Integer g = 100;
		Integer h = 100;
		System.out.println(g == h); //true
		
		
		float c = 100.5f;
		float d = 100.5f;
		System.out.println(c == d); //true
		

		Float e = 100.5f;
		Float f = 100.5f;
		System.out.println(e == f); //false
		
		String s1 = new String("HELLO"); // 100
		String s2 = new String("HELLO"); // 200

		String s3 = "HELLO"; // 300
		String s4 = "HELLO"; // 300

		String s5 = "WORLD";
		String s6 = "HELLOWORLD";
		String s7 = "HELLOWORLD";

		System.out.println(s1 == s2); // false
		System.out.println(s1.equals(s2)); // true

		System.out.println(s3 == s4); // true
		System.out.println(s3.equals(s4)); // true

		System.out.println(s1.intern() == s3); // true
		System.out.println(s1.intern() == s2); // false
		System.out.println(s1.intern() == s4); // true
		System.out.println(s2.intern() == s4); // true

		System.out.println("s6==s7" + (s6 == s7));

	}

}
