package crypto3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import crypto3.load_the_file;

public class DSA {
	
	public static void main(String[] args) throws NoSuchAlgorithmException,Exception, InvalidKeySpecException {
	
		//  path = Paths.get(FILE_NAME);
		//	bytes = Files.readAllBytes(path);

		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
	    kpg.initialize(1024);
	    KeyPair keypair = kpg.genKeyPair();
	    PrivateKey priKey = keypair.getPrivate();
	    PublicKey pubKey = keypair.getPublic();
	    KeyFactory kf = KeyFactory.getInstance("DSA");
	    DSAPrivateKeySpec dsaPriKeySpec = (DSAPrivateKeySpec) kf.getKeySpec(priKey,DSAPrivateKeySpec.class);
	    DSAPublicKeySpec dsaPubKeySpec = (DSAPublicKeySpec) kf.getKeySpec(pubKey,DSAPublicKeySpec.class);
	    
	    System.out.println("\n DSA Private Key");
	    System.out.println("\nx = " + dsaPriKeySpec.getX());
	    System.out.println("Format = "+priKey.getFormat());
	    System.out.println("toString = "+priKey.toString());
	    System.out.println("\n DSA Public Key");
	    System.out.println("\ng = " + dsaPubKeySpec.getG());
	    System.out.println("\np = " + dsaPubKeySpec.getP());
	    System.out.println("\nq = " + dsaPubKeySpec.getQ());
	    System.out.println("\ny = " + dsaPubKeySpec.getY());
	    
	    //zapis privkey do pliku
	    byte[] privkey = priKey.getEncoded();
		FileOutputStream keypriv = new FileOutputStream("C:\\Users\\cp24\\Desktop\\keypriv.txt");
		keypriv.write(privkey);
		keypriv.close();
		//zapis pubkey do pliku
		byte[] key = pubKey.getEncoded();
		FileOutputStream keypub = new FileOutputStream("C:\\Users\\cp24\\Desktop\\keypub.txt");
		keypub.write(key);
		keypub.close();		
	    
		Signature signature = Signature.getInstance("SHA1withDSA","SUN");
		signature.initSign(priKey);
		
		//signature.initSign(priKey, new SecureRandom());
		
		//podpisanie pliku
		FileInputStream fis = new FileInputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc.pdf");
		BufferedInputStream bufin = new BufferedInputStream(fis);
		byte[] buffer = new byte[1024];
		int len;
		while ((len = bufin.read(buffer)) >= 0) {
			signature.update(buffer, 0, len);
		};
		bufin.close();
		
		byte[] realSig = signature.sign();
		
		FileOutputStream filesignature = new FileOutputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc.pdf");
		filesignature.write(realSig);
		filesignature.close();
		
		
		/// Scanner in = new Scanner(System.in);
		// System.out.println("\n Podaj tekst do podpisu: ");
		// String tekst = in.nextLine();
		//podpis prywatnym
	//	byte[] message = tekst.getBytes();
	  //  signature.update(message);
	//	byte[] sigBytes = signature.sign();
		

		//System.out.println("------------------\n");


		/*
		 * Pomocny komentarz do tego co trzeba zrobi�, czyli czyta� dokumentacj�.
		 * 
		 * https://docs.oracle.com/javase/tutorial/security/apisign/gensig.html
		 * 
		 * Potrzebne b�d� trzy pliki, jeden wyj�ciowy do podpisu, drugi zapisany po podpisie oraz trzeci zawieraj�cy klucz publiczny.
		 * 
		 * W linijkach 71 - 81 masz opisane jak to zrobi�
		 * 
		 * 1. Zapisa� tablic� bajtow key do pliku.
		 * 
		 * 2. W drugim programie wczytujesz ten plik do tablicy bajt�w i przepisujesz  (79 - 83) te trzy linijki powinien stworzy� klucz publiczny
		 *
		 * 3. Dalej powinno da� si� zweryfikowa� podpis.
		 * 
		 */
		
		
		//Andrzeja 
		

		


		//BigInteger b1= new BigInteger(key);
		
		//byte[] key1 = b1.toByteArray();
		
		//BigInteger b2= new BigInteger(key1);
		
		//System.out.println(b1 + "\n");
		//System.out.println(b2);
		
		
			
		load_the_file rfile = new load_the_file();
		rfile.file();
		

		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
		
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		
		PublicKey pubKey2 = keyFactory.generatePublic(pubKeySpec);
		
		
		//do Sprawdzenia czy to samo
		DSAPublicKeySpec dsaPubKeySpec1 = (DSAPublicKeySpec) kf.getKeySpec(pubKey2,DSAPublicKeySpec.class);
		
	    System.out.println("\n DSA Public Key");
	    System.out.println("\ng = " + dsaPubKeySpec1.getG());
		System.out.println("\np = " + dsaPubKeySpec1.getP());
	    System.out.println("\nq = " + dsaPubKeySpec1.getQ());
	    System.out.println("\ny = " + dsaPubKeySpec1.getY());
	    
	    
	    // weryfikacja na podstawie https://docs.oracle.com/javase/tutorial/security/apisign/versig.html
		
		signature.initVerify(pubKey);
		signature.update(realSig);
		System.out.println("\n"+ signature.verify(realSig));
		
		

}
}
		
