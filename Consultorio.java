package evidenciaFinal;

import java.io.*;
import java.util.*;
//prueba brach crear_cita
public class Consultorio {
	// Scanner para la entrada de datos del usuario.
    private static final Scanner scanner = new Scanner(System.in);
 // Rutas a los archivos CSV que almacenarán los datos.
    private static final String DOCTORES_ARCHIVO = "C:\\Users\\jesus\\eclipse-workspace\\evidenciaFinal\\src\\evidenciaFinal\\db\\doctores.csv";
    private static final String PACIENTES_ARCHIVO = "C:\\Users\\jesus\\eclipse-workspace\\evidenciaFinal\\src\\evidenciaFinal\\db\\pacientes.csv";
    private static final String CITAS_ARCHIVO = "C:\\Users\\jesus\\eclipse-workspace\\evidenciaFinal\\src\\evidenciaFinal\\db\\citas.csv";
    private static final String ADMIN_ARCHIVO = "C:\\Users\\jesus\\eclipse-workspace\\evidenciaFinal\\src\\evidenciaFinal\\db\\admins.csv";

    public static void main(String[] args) {
    	// Intenta iniciar sesión y termina el programa si falla.
        if (!login()) {
            System.out.println("Acceso denegado.");
            return;
        }
     // Bucle principal para manejar el menú del programa.
        boolean salir = false;
        while (!salir) {
        	// Imprime las opciones del menú.
            System.out.println("1. Dar de alta doctores");
            System.out.println("2. Dar de alta pacientes");
            System.out.println("3. Crear una cita");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
         // Lee la opción del usuario.
            int opcion = Integer.parseInt(scanner.nextLine());
            
         // Ejecuta la acción basada en la opción seleccionada.
            switch (opcion) {
                case 1 -> registrarDoctor();
                case 2 -> registrarPaciente();
                case 3 -> crearCita();
                case 4 -> {
                	// Imprime mensaje de salida y cambia el flag para terminar el bucle.
                    System.out.println("Saliendo del programa...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
    
 // Método para iniciar sesión verificando las credenciales contra el archivo admins.csv.
    private static boolean login() {
        System.out.print("Ingrese su usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        
     // Intenta leer el archivo de administradores y verifica las credenciales.
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_ARCHIVO))) {
            return reader.lines()
                    .anyMatch(linea -> linea.equals(usuario + "," + contrasena));
        } catch (IOException e) {
        	// Imprime error si no puede acceder al archivo.
            System.out.println("Error al acceder al sistema: " + e.getMessage());
            return false;
        }
    }
    
 // Método para registrar un nuevo doctor en el sistema.
    private static void registrarDoctor() {
    	// Solicita datos del doctor al usuario.
        System.out.print("Ingrese identificador único del doctor: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre completo del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese especialidad del doctor: ");
        String especialidad = scanner.nextLine();

     // Intenta escribir los datos del doctor en el archivo CSV.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTORES_ARCHIVO, true))) {
            writer.write(String.join(",", id, nombre, especialidad));
            writer.newLine();
         // Confirma que el doctor ha sido registrado
            System.out.println("Doctor registrado con éxito.");
        } catch (IOException e) {
        	// Imprime error si no puede escribir en el archivo
            System.out.println("Error al registrar doctor: " + e.getMessage());
        }
    }

 // Método para registrar un nuevo paciente en el sistema.
    private static void registrarPaciente() {
    	// Solicita datos del paciente al usuario.
        System.out.print("Ingrese identificador único del paciente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre completo del paciente: ");
        String nombre = scanner.nextLine();

     // Intenta escribir los datos del paciente en el archivo CSV.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PACIENTES_ARCHIVO, true))) {
            writer.write(String.join(",", id, nombre));
            writer.newLine();
         // Confirma que el paciente ha sido registrado.
            System.out.println("Paciente registrado con éxito.");
        } catch (IOException e) {
        	// Imprime error si no puede escribir en el archivo.
            System.out.println("Error al registrar paciente: " + e.getMessage());
        }
    }

 // Método para crear una nueva cita en el sistema.
    private static void crearCita() {
        System.out.print("Ingrese identificador único de la cita: ");
        String idCita = scanner.nextLine();
        System.out.print("Ingrese fecha y hora de la cita (formato YYYY-MM-DD HH:MM): ");
        String fechaHora = scanner.nextLine();
        System.out.print("Ingrese motivo de la cita: ");
        String motivo = scanner.nextLine();
        System.out.print("Ingrese identificador del doctor: ");
        String idDoctor = scanner.nextLine();
        System.out.print("Ingrese identificador del paciente: ");
        String idPaciente = scanner.nextLine();

     // Intenta escribir los datos de la cita en el archivo CSV.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CITAS_ARCHIVO, true))) {
            writer.write(String.join(",", idCita, fechaHora, motivo, idDoctor, idPaciente));
            writer.newLine();
         // Confirma que la cita ha sido registrado.
            System.out.println("Cita creada con éxito.");
        } catch (IOException e) {
        	// Imprime error si no puede escribir en el archivo.
            System.out.println("Error al crear cita: " + e.getMessage());
        }
    }
}
