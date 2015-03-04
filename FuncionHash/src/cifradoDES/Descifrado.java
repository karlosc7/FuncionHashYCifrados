package cifradoDES;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.*;

public class Descifrado {

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
			aesCipher.init(Cipher.DECRYPT_MODE, key);

			FileInputStream in = new FileInputStream(args[0] + ".cifrado");
			FileOutputStream out = new FileOutputStream(args[0] + ".descifrado");
			byte[] bufferPlano;
			// Creo los arrays que voy a obtener del texto normal
			byte[] buffer = new byte[5000];
			int bytesLeidos = in.read(buffer, 0, 1000);
			while (bytesLeidos != -1) {
				bufferPlano = aesCipher.update(buffer, 0, bytesLeidos);
				out.write(bufferPlano);
				bytesLeidos = in.read(buffer, 0, 1000);
			}

			bufferPlano = aesCipher.doFinal();
			out.write(bufferPlano);

			in.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
