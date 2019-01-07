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
		
		// sprawdzenie klucza przywatnego kluczem publicznym z pary
	
		//System.out.println("Podaj klucz publiczny ");
		
		// String y = in.nextLine();
		// BigInteger b1= new BigInteger(y);

		/// if(b1==dsaPubKeySpec.getY())
		// {
		signature.initVerify(keypair.getPublic());  
		signature.update(message);
		System.out.println("\n"+ signature.verify(sigBytes));
		// }
		
		// else {
		//	 System.out.println(" niepoprawny klucz publiczny");
		 //}  
		
	    }

}
