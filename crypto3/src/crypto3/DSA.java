package crypto3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import crypto3.DSAVerify;


public class DSA {
	
	public static void main(String[] args) throws NoSuchAlgorithmException,Exception, InvalidKeySpecException,IOException {
	
		//  path = Paths.get(FILE_NAME);
		//	bytes = Files.readAllBytes(path);
		String FILE_NAME = "C:\\Users\\cp24\\Desktop\\Wiadomosc.pdf";
		Path path;
		path = Paths.get(FILE_NAME);
		byte[] bytes=Files.readAllBytes(path);
		//bytes = Files.readAllBytes(path);
		
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
		
		
		/* Wczytanie klucza prywatnego z pliku*/
		File filePrivateKey = new File("C:\\Users\\cp24\\Desktop\\keypriv.txt");
		FileInputStream fileprivkey = new FileInputStream("C:\\Users\\cp24\\Desktop\\keypriv.txt");
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fileprivkey.read(encodedPrivateKey);
		fileprivkey.close();
		
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		DSAPrivateKeySpec dsaPriKeySpec1 = (DSAPrivateKeySpec) kf.getKeySpec(privateKey,DSAPrivateKeySpec.class);
		
		/* Sprawdzenie klucza prywatnego z pliku - czy to samo*/
		System.out.println("\n DSA Private Key sprawdzenie");
		System.out.println("\nx = " + dsaPriKeySpec1.getX());
	    
		
		
		Signature signature = Signature.getInstance("SHA1withDSA","SUN");
		signature.initSign(privateKey);
		
		
		signature.update(bytes);
		byte[] sigBytes = signature.sign();
		Files.write(path, sigBytes);
		
		//podpisanie pliku
		//FileInputStream fis = new FileInputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc.pdf");
	//	BufferedInputStream bufin = new BufferedInputStream(fis);
	//	byte[] buffer = bytes;
	//	int len;
	//	while ((len = bufin.read(buffer)) >= 0) {
	//		signature.update(buffer, 0, len);
	//	};
	//	bufin.close();
	//	byte[] realSig = signature.sign();
		
		//zapisanie pliku z podpisem
		//FileOutputStream Files = new FileOutputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc_podpisana.pdf");
		//Files.write(path, bytes);
		//Files.clone();
		
		DSAVerify dsaV = new DSAVerify();
		dsaV.verify();
		
		/// Scanner in = new Scanner(System.in);
		// System.out.println("\n Podaj tekst do podpisu: ");
		// String tekst = in.nextLine();
		//podpis prywatnym
	//	byte[] message = tekst.getBytes();
	  //  signature.update(message);
	//	byte[] sigBytes = signature.sign();
		

		//System.out.println("------------------\n");


		/*
		 * Pomocny komentarz do tego co trzeba zrobiæ, czyli czytaæ dokumentacjê.
		 * 
		 * https://docs.oracle.com/javase/tutorial/security/apisign/gensig.html
		 * 
		 * Potrzebne bêd¹ trzy pliki, jeden wyjœciowy do podpisu, drugi zapisany po podpisie oraz trzeci zawieraj¹cy klucz publiczny.
		 * 
		 * W linijkach 71 - 81 masz opisane jak to zrobiæ
		 * 
		 * 1. Zapisaæ tablicê bajtow key do pliku.
		 * 
		 * 2. W drugim programie wczytujesz ten plik do tablicy bajtów i przepisujesz  (79 - 83) te trzy linijki powinien stworzyæ klucz publiczny
		 *
		 * 3. Dalej powinno daæ siê zweryfikowaæ podpis.
		 * 
		 */
		
		
		//Andrzeja 
		

		


		//BigInteger b1= new BigInteger(key);
		
		//byte[] key1 = b1.toByteArray();
		
		//BigInteger b2= new BigInteger(key1);
		
		//System.out.println(b1 + "\n");
		//System.out.println(b2);
		
		
			
	//	DSAVerify rfile = new DSAVerify();
	//	rfile.file();
		
 /*
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
	    
	*/    
	    // weryfikacja na podstawie https://docs.oracle.com/javase/tutorial/security/apisign/versig.html
		
	//	signature.initVerify(pubKey);
	//	signature.update(realSig);
	//	System.out.println("\n"+ signature.verify(realSig));
		
		

	}
}
		
