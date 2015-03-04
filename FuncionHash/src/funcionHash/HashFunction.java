package funcionHash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;

//Creo la clase Hash basándome en el ejemplo 2 de la UF5.2
public class HashFunction {
	public static void main(String[] args) {
		try {
			// Recorro el nombre del algoritmo resumen
			if (args.length != 1) {
				System.out.println("missing: <algoritmo de resumen>");
				System.exit(1);
			}
			// Ese algoritmo es String donde está el nombre
			String name = args[0];
			// Soportado por la clase MessageDigest
			MessageDigest md = MessageDigest.getInstance(name);
			// Leo con BufferedReader todo lo que el usuario mete por su entrada
			// de teclado
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String linea = br.readLine();
			// Mientras haya líneas que leer sigo
			while (linea != null) {
				md.update(linea.getBytes());
				linea = br.readLine();
			}
			// Muestro el resumen
			byte[] resumen = md.digest();
			// Muestro el resumen por la salida estándar
			System.out.println("Resumen " + name + ":\n" + new String(resumen));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}