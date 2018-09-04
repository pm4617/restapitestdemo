
public class Test {

	public static void main(String[] args) {

		A a = new B(){
			public void m3(){
				System.out.println("m3 in annonoyms B");
			}
		};
		
		a.m1();
		((B)a).m2();
	 
	}
}

class A{
	
	public void m1(){
		System.out.println("m1 in A");
	}
 
	
} 
class B extends A{
	public void m2(){
		System.out.println("m2 in B");
	}
}

