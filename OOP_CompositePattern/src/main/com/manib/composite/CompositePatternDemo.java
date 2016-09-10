package main.com.manib.composite;

public class CompositePatternDemo {

	public static void main(String[] args) {
		Employee CEO = new Employee("Mani", "CEO", 30000);

		Employee headSales = new Employee("Shreya", "Head Sales", 20000);

		Employee headMarketing = new Employee("Arch", "Head Marketing", 20000);

		Employee clerk1 = new Employee("Lily", "Marketing", 10000);
		Employee clerk2 = new Employee("Rose", "Marketing", 10000);

		Employee salesExecutive1 = new Employee("Rish", "Sales", 10000);
		Employee salesExecutive2 = new Employee("Soma", "Sales", 10000);

		CEO.addEmployee(headSales);
		CEO.addEmployee(headMarketing);

		headSales.addEmployee(salesExecutive1);
		headSales.addEmployee(salesExecutive2);

		headMarketing.addEmployee(clerk1);
		headMarketing.addEmployee(clerk2);

		CEO.printEmployeeHeirarchy();
		System.out.println("=======================================================================================");
		headMarketing.printEmployeeHeirarchy();
		System.out.println("=======================================================================================");
		headSales.showEmployees();
	}
}
