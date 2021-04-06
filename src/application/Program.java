package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		List<Employee> list = new ArrayList<>();
		
		System.out.println("Enter full file path: ");
		String path = teclado.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while(line != null) {
				
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);
				
				Employee employee = new Employee(name, email, salary);
				list.add(employee);
				
				line = br.readLine();
			}
			
			System.out.println();
			System.out.print("Which salary range do you want to filter? ");
			double salaryFilter = teclado.nextDouble();
			System.out.println();
			
			//PIPELINE P/FILTRAR EMAILS CUJO SALARIO SEJA MAIOR QUE O INFORMADO PELO USUARIO
			List<String> emails = list.stream().filter(e -> e.getSalary() > salaryFilter)
					.map(e -> e.getEmail()).sorted().collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + salaryFilter);
			emails.forEach(System.out::println);
			
			//PIPELINE P/SOMATORIO DOS SALARIOS CUJO NOME DO FUNCIONARIO COMEÇE COM A LETRA M
			double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M').map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x+y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
			
			
		} catch (IOException e){
			System.out.println("Error: " + e.getMessage());
		}

		teclado.close();
	}

}
