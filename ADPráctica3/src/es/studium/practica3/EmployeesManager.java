package es.studium.practica3;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeesManager {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int opcion;
        do {
        	//Creamos las opciones para el usuario
            System.out.println("1. Introduzcir Empleado");
            System.out.println("2. Mostrar datos de un empleado");
            System.out.println("3. Modificar un empleado");
            System.out.println("4. Borrar un empleado");
            System.out.println("5. Salir del programa");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    crearEmpleado();
                    break;
                }
                case 2: {
                	leerEmpleado();
                    break;
                }
                case 3: {
                	actualizarEmpleado();
                    break;
                }
                case 4: {
                	borrarEmpleado();
                    break;
                }
                case 5: {
                    System.out.println("Programa Cerrado");
                    break;
                }
            }
        } while (opcion != 5);
    }
	
	private static void crearEmpleado() {
		//int id, annoN, mesN, diaN, annoC, mesC, diaC;
		//String nombre, apellidos, genero;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca un id");
		int id=sc.nextInt();
		System.out.println("Introduzca el año de nacimiento");
		int annoN=sc.nextInt();
		System.out.println("Introduzca el mes de nacimiento");
		int mesN=sc.nextInt();
		System.out.println("Introduzca el día de nacimiento");
		int diaN=sc.nextInt();
		System.out.println("Introduzca un nombre");
		String nombre=sc.next();
		System.out.println("Introduzca los apellidos");
		String apellidos=sc.next();
		System.out.println("Introduzca el Género(M o F)");
		String genero=sc.next();
		System.out.println("Introduzca el año de contratación");
		int annoC=sc.nextInt();
		System.out.println("Introduzca el mes de contratación");
		int mesC=sc.nextInt();
		System.out.println("Introduzca el día de contratación");
		int diaC=sc.nextInt();
		
		Employees ep = new Employees(id, new GregorianCalendar(annoN,mesN,diaN).getTime(), nombre, apellidos, genero, new GregorianCalendar(annoC,mesC,diaC).getTime()); 
		EmployeesManager.create(ep);

	}
	private static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Employees.class)
				.addAnnotatedClass(Employees.class).configure().buildSessionFactory();
		return sessionFactory;
	}

	public static Integer create(Employees ep) {
		Session sessionObj = getSessionFactory().getCurrentSession();
		Transaction transObj = sessionObj.beginTransaction();
		sessionObj.save(ep);
		transObj.commit();
		sessionObj.close();
		System.out.println("Empleado insertado en la BD");
		return ep.getEmp_no();
	}
	
	
	public static List<Employees> readEmpleado() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca un id de empleado");
		int idEmpleado =sc.nextInt();
		Session sessionObj = getSessionFactory().openSession();
		String query = "FROM Employees WHERE emp_no = " + idEmpleado;
		@SuppressWarnings("unchecked")
		List<Employees> resultado = sessionObj.createQuery(query).list();
		sessionObj.close();
		return resultado;
	}
	private static void leerEmpleado() {
		Employees ep = new Employees();
		List<Employees> resultado = readEmpleado();
		ep.setEmp_no(resultado.get(0).getEmp_no());
		ep.setFirst_name(resultado.get(0).getFirst_name());
		ep.setLast_name(resultado.get(0).getLast_name());
		ep.setBirth_date(resultado.get(0).getBirth_date());
		ep.setHire_date(resultado.get(0).getHire_date());		
    	System.out.println("ID: " + ep.getEmp_no() + " Nombre: " + ep.getFirst_name() + " Apellido: " + ep.getLast_name() + " Fecha de Nacimiento: " + ep.getBirth_date() + " Fecha de Contratación: " + ep.getHire_date());
	}
	
	private static void actualizarEmpleado() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el numero de empleado: ");
		int id = sc.nextInt();
		System.out.println("Introduzca un nuevo apellido: ");
		EmployeesManager.updateEmpleado(id, sc.next());
	}
	 

	public static void updateEmpleado(int numEmpleado, String apellidos) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		Employees EmpleadoBD = sessionObj.load(Employees.class, numEmpleado);
		EmpleadoBD.setLast_name(apellidos);
		transObj.commit();
		sessionObj.close();
		System.out.println("Actualizado correctamente");
	}
	
	private static void borrarEmpleado() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el número de empleado: ");
		EmployeesManager.deleteEmpleado(sc.nextInt());
	}
	public static void deleteEmpleado(int numEmpleado) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		Employees employees = sessionObj.load(Employees.class, numEmpleado);
		sessionObj.delete(employees);
		transObj.commit();
		sessionObj.close();
		System.out.println("Eliminado correctamente");
	}

}
