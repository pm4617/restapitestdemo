interface Age
{
    int x = 21;
    void getAge();
}

class Demo {
	
	public  Integer y= 20;
	
	
	
}

class OuterClass {

}

 class EnclosingClass {
    String shared = "hello";
    
    public void someMethod() throws InterruptedException {
        new Thread() {
            public void run() {
                System.out.println(shared); // this is perfectly valid
            }
        }.start();
       Thread.sleep(2000);
        // change the reference 'shared' points to, with a new value
        shared = "other hello"; 
        System.out.println(shared);
    }
}

public class DemoAnonymousInnerClass {



	public static void main(String[] args) throws InterruptedException {
/*		String outerstring = "Test";
		
		Age oj1 = new Age() {
			public void getAge() {
				System.out.println(x);
				System.out.println(outerstring);
				outerstring = "New Test";
				System.out.println(outerstring);
			} 
		};*/
		
		
		EnclosingClass enclosingClass = new EnclosingClass();
		
		enclosingClass.someMethod();
		
		
	}
}
