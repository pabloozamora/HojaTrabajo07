package dictionary;
/**
 * Programa que, mediante un arbol binario, permite agregar, editar y eliminar palabras de un diccionario ingles-espanol y frances-espanol,
 * asi como traducir un texto en ingles o frances, palabra por palabra.
 * Realizado por Pablo Andres Zamora Vasquez - 21780. el 08/04/2022
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	/**
	 * Metodo getValidInt. Permite verificar si la opcion ingresada por el usuario es valida.
	 * @param sc Scanner utilizado para el texto que ingresa el usuario
	 * @param message Texto ingresado
	 * @param absoluteValues Permite convertir cualquier entero a positivo
	 * @param validValues Valores validos
	 * @return int Opcion ingresada
	 */
	private static int getValidInt(Scanner sc, String message, boolean absoluteValues, Integer... validValues) {

		while (true) {

			try {

				System.out.println(message);
				int value = sc.nextInt();
				sc.nextLine();

				if (Arrays.asList(validValues).contains(value) || validValues.length == 0)
					return absoluteValues ? Math.abs(value) : value;
				else
					System.out.println("Por favor, ingresa un numero valido");

			} catch (Exception ex) {
				System.out.println("Por favor, ingresa un numero valido");
				sc.nextLine();
			}
		}
	}
	
	/**
	 * Metodo main. Busca el archivo diccionario.txt para agregar su contenido a los diccionarios y despliega
	 * las distintas opciones que puede realizar el usuario con el diccionario. En este metodo se instancia
	 * la clase Dictionary.
	 * @param args
	 */
	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		boolean endProgram = false;
		Scanner scan = new Scanner(System.in);
		String path = "";
		String mainMenu = "\nSeleccione una opcion:\n\n"
				+ "1. Agregar una palabra al diccionario.\n2. Editar una palabra.\n3. Eliminar una palabra\n4. Traducir un archivo de texto.\n"
				+ "5. Mostrar las entradas del diccionario (in-order).\n6. Salir del programa";
		boolean fileFound = false;
		String[] fileContent = null;
		System.out.println("\nBienvenido al diccionario ingles-espanol y frances-espanol\nBuscando el archivo diccionario.txt...");
		while(!fileFound) { //Bucle para encontrar el archivo diccionario.txt
			try { //Se encuentra el archivo
				FileController dictionaryFile = new FileController("\\src\\dictionary\\diccionario.txt");
				fileContent = dictionaryFile.readFile();
				fileFound = true;
			} catch (IOException e) { //Si no se encuentra el archivo
				System.out.println("\nArchivo no encontrado.\nPor favor, asegurese de que el archivo diccionario.txt sea valido y se encuentre en la carpeta donde se encuentra el programa.");
				System.out.println("Presione enter para volver a buscar el archivo.");
				scan.nextLine();
			}
		}
		
		System.out.println("\nArchivo encontrado"); //Se encuentra el archivo
		int linecount = 1;
		for (String entry: fileContent) { //Se agregan las palabras dentro de este a los diccionarios
			String [] words = entry.toLowerCase().split(",", 0);
			dictionary.addWord(words[1], words[0], words[2]);
		}
		
		while (!endProgram) { //Bucle principal
			int option = getValidInt(scan, mainMenu, false, 1,2,3,4,5,6);
			if (option == 1) { //Agregar una nueva palabra
				System.out.println("\nIngrese la palabra en espanol:");
				String spanish = scan.nextLine();
				System.out.println("\nIngrese la palabra en ingles:");
				String english = scan.nextLine();
				System.out.println("\nIngrese la palabra en frances:");
				String french = scan.nextLine();
				dictionary.addWord(spanish, english, french);
			}
			else if (option == 2) {//Editar una palabra
				System.out.println("\nPara encontrar la palabra, ingresela en ingles:");
				String english = scan.nextLine();
				System.out.println("\nAhora, ingresela en frances:");
				String french = scan.nextLine();
				String deleteWord = dictionary.deleteWord(english, french); //Se elimina el nodo de la palabra correspondiente de cada diccionario
				if (deleteWord!=null) {
					System.out.println("\nEditando la palabra: " + deleteWord);
					System.out.println("\nIngrese la palabra en espanol:");
					String spanish = scan.nextLine();
					System.out.println("\nIngrese la palabra en ingles:");
					english = scan.nextLine();
					System.out.println("\nIngrese la palabra en frances:");
					french = scan.nextLine();
					dictionary.addWord(spanish, english, french); //Se ingresa la nueva palabra
					System.out.println("\nPalabra editada con exito");
				}
				else {
					System.out.println("\nPalabra no encontrada");
				}
			}
			
			else if (option == 3) { //Eliminar una palabra
				System.out.println("\nPara encontrar la palabra, ingresela en ingles:");
				String english = scan.nextLine();
				System.out.println("\nAhora, ingresela en frances:");
				String french = scan.nextLine();
				String deleteWord = dictionary.deleteWord(english, french);
				if (deleteWord!=null) {
					System.out.println("\nPalabra eliminada: " + deleteWord);
				}
				else {
					System.out.println("\nPalabra no encontrada");
				}
			}
			
			else if (option == 4) {//Traducir el contenido de un archivo de texto
				try { //Se encuentra el archivo
					FileController textFile = new FileController("\\src\\dictionary\\texto.txt");
					String[] textfileContent = textFile.readFile();
					linecount = 1;
					String result = "";
					for (String entry: textfileContent) {
						String [] words = entry.toLowerCase().split(" ", 0);
						for (String word: words) {
							result += dictionary.translate(word);
							result += " ";
						}
						result += "\n";
					}
					System.out.println("\nTraduccion:\n" + result);
					
				} catch (IOException e) { //Si no se encuentra el archivo
					System.out.println("\nArchivo no encontrado.\nPor favor, asegurese de que el archivo texto.txt sea valido y se encuentre en la carpeta donde se encuentra el programa.");
				}
			}
			
			else if (option == 5) {//Desplegar las entradas de uno de los diccionarios in-order
				int optEntries = getValidInt(scan, "\nIngrese el diccionario cuyas entradas desea ver:\n1. Ingles-espanol\n2. Frances-espanol", false, 1,2);
				if (optEntries == 1) {
					System.out.println("\nEntradas del diccionario ingles-espanol:");
					System.out.println(dictionary.getEntries(optEntries));
				}
				else if (optEntries == 2) {
					System.out.println("\nEntradas del diccionario frances-espanol:");
					System.out.println(dictionary.getEntries(optEntries));
				}
				else {
					System.out.println("Opcion no valida\n");
				}
			}
			else if (option == 6) { //Finalizar el programa
				System.out.println("\nGracias por utilizar el diccionario ingles-espanol y frances-espanol. Feliz dia.");
				endProgram = true;
			}
			
			else { //Opcion no valida
				System.out.println("Opcion no valida");
			}
		}
	}
}
