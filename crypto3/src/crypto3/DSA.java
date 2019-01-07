package crypto3;

import java.math.BigInteger;
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
	    
		Signature signature = Signature.getInstance("SHA1withDSA");
		signature.initSign(priKey, new SecureRandom());
		
		 Scanner in = new Scanner(System.in);
		 System.out.println("\n Podaj tekst do podpisu: ");
		 String tekst = in.nextLine();
		//podpis prywatnym
		byte[] message = tekst.getBytes();
	    signature.update(message);
		byte[] sigBytes = signature.sign();
		
		
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
		
		System.out.println("------------------\n");
		
		byte[] key = pubKey.getEncoded();
		
		BigInteger b1= new BigInteger(key);
		
		byte[] key1 = b1.toByteArray();
		
		BigInteger b2= new BigInteger(key1);
		
		System.out.println(b1 + "\n");
		System.out.println(b2);
		
		
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
		
		signature.initVerify(pubKey);
		signature.update(message);
		System.out.println("\n"+ signature.verify(sigBytes));
		
		
		/*
		System.out.println("Podaj klucz publiczny ");
		
		String y = in.nextLine();
		BigInteger b1= new BigInteger(y);
		
		if(b1==dsaPubKeySpec.getY())
		{
			signature.initVerify(keypair.getPublic());  
			signature.update(message);
			System.out.println("\n"+ signature.verify(sigBytes));
		}
		
		else {
			System.out.println(" niepoprawny klucz publiczny");
		 }  
		*/
		
	    }

}
