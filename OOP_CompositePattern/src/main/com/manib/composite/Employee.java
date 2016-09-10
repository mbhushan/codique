package main.com.manib.composite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Employee {

	private String name;
	private String department;
	private double salary;
	private List<Employee> subordinates;
	
	public Employee(String name, String department, double salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.subordinates = new ArrayList<Employee>();
	}
	
	public Employee() {}
	
	public void addEmployee(Employee employee) {
		this.subordinates.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		this.subordinates.remove(employee);
	}
	
	public List<Employee> getSubordinates() {
		return this.subordinates;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("[");
		sb.append(this.name + ", ");
		sb.append(this.department + ", ");
		sb.append(this.salary + ", ");
		sb.append("]");
		
		return sb.toString();
	}
	
	public void printEmployeeHeirarchy(Employee employee) {
		if (employee == null) {
			return ;
		}
		
		
		Queue<Employee> queue = new LinkedList<Employee>();
		queue.add(employee);
		
		Employee marker = new Employee();
		
		while (!queue.isEmpty()) {
			
		}
	}
	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if(!(o instanceof Employee)) {
			return false;
		}
		
		Employee e = (Employee) o;
		
		boolean flag = (this.name.equals(e.name) 
				&& this.department.equals(e.department)
				&& (Double.compare(this.salary, e.salary) == 0)
				);
		return flag;
	}
}
