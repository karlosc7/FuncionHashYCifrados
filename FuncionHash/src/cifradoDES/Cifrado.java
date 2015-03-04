package cifradoDES;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.*;

public class Cifrado {

	public static void main(String[] args) {
		try {

			// Primero obtenemos una clave de cifrado DES
			System.out
					.println("Obteniendo generador de claves con cifrado DES");
			// Creo la clave usando el objeto generador
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
			// Comunico al usuario que estoy generando la clave
			System.out.println("Generando la clave");
			// Creo la key
			SecretKey key = keygen.generateKey();
			Cipher aesCipher = Cipher.getInstance("DES");
			System.out.println("Configurando Cipher para cifrar...");
			aesCipher.init(Cipher.ENCRYPT_MODE, key);

			// Creo los arrays que voy a obtener del texto normal y cifrado
			byte[] buffer = new byte[5000];
			byte[] bufferCifrado;

			// Leo y cierro el txt
			FileInputStream in = new FileInputStream(args[0]);
			FileOutputStream out = new FileOutputStream(args[0] + ".cifrado");

			// Esta parte de código la he conseguido de un blog
			// http://gl-epn-programacion-ii.blogspot.com.es/2012/12/cifrado-de-des-en-java.html
			// Mientras lea el fichero voy guardando y cifrando
			int bytesLeidos = in.read(buffer, 0, 5000);
			while (bytesLeidos != -1) {
				bufferCifrado = aesCipher.update(buffer, 0, bytesLeidos);
				out.write(bufferCifrado);
				bytesLeidos = in.read(buffer, 0, 5000);
			}
			bufferCifrado = aesCipher.doFinal();
			out.write(bufferCifrado);

			// Cierro con close
			in.close();
			out.close();

			// Obtengo el objeto cifrado
			System.out.println("Obteniendo objeto Cipher con cifrado DES");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}