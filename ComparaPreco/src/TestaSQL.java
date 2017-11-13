
public class TestaSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Supermarket supermarket = new Supermarket("Angeloni", 88117310, 123456);
		
		int hashcode = supermarket.hashCode();
		
		System.out.println(hashcode);
		
		System.out.println("create"+supermarket.getNameClass());
		
	}

}
