import javax.print.DocFlavor.STRING;

public class EqualsInJava {

	public static void main(String[] args) {
		
		
		String s1 = new String("HELLO"); //100
        String s2 = new String("HELLO"); //200
        
        String s3 = "HELLO"; // 300
        String s4 = "HELLO"; // 300
        
        String s5 = "WORLD";
        String s6 = "HELLOWORLD";
        String s7 = "HELLOWORLD";
        
        
                
//        System.out.println(s1 == s2); // false
//        System.out.println(s1.equals(s2)); // true
//        
//        
//        System.out.println(s3 == s4); // true
//        System.out.println(s3.equals(s4)); // true
        
/*        System.out.println(s1.intern() == s3); //false
        System.out.println(s1.intern() == s2);
        System.out.println(s1.intern() == s4);
        System.out.println(s2.intern() == s4);*/
        
        System.out.println("s6==s7"+(s6==s7));
        
        
        
	
	}

}
