public class Main {
    public static void main(String[] args) {
        MyOperations myOperations = new MyOperations();

        myOperations.mySelect();
	System.out.println();

        myOperations.myInsertEmployee("1221090576183", "\'Pop\'", "\'Silviu\'", "\'male\'", "\'Iasi - street Atanasie Panu\'", 6, 3800, 14, "\'tester\'");
        myOperations.mySelect();
	System.out.println();

        myOperations.myUpdate(6, 5500);
        myOperations.mySelect();
	System.out.println();

        myOperations.myDelete(6);
        myOperations.mySelect();
	System.out.println();
    }
}
