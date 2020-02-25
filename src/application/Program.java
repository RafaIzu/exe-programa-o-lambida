package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import service.EmployeeService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter full file path: ");
		String path=sc.next();
		System.out.print("Enter salary: ");
		double comparaSalary=sc.nextDouble();
		
		try (BufferedReader br=new BufferedReader(new FileReader(path))){
			List<Employee>list=new ArrayList<>();
			String line=br.readLine();
			while(line!=null) {
				String employee[]=line.split(",");
				list.add(new Employee(employee[0], employee[1], Double.parseDouble(employee[2])));
				line=br.readLine();
			}
			System.out.println("Email of people whose salary is more than "+comparaSalary+": ");
			
			Comparator<String>comp1=(s1,s2)->s1.toUpperCase()
					.compareTo(s2.toUpperCase());
			
			List<String>emails=list.stream()
					.filter(p->p.getSalary()>comparaSalary)
					.map(p->p.getEmail())
					.sorted(comp1).collect(Collectors.toList());
			emails.forEach(System.out::println);
			
			EmployeeService es=new EmployeeService();
			
			double sum=es.sumSalary(list, p->p.getName().charAt(0)=='M');
			
			System.out.println("Sum of salary of people whose name starts with 'M': "+String.format("%.2f", sum));
			
			
		}
		catch(IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		sc.close();

	}

}
