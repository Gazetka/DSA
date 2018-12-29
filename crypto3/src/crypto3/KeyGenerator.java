package crypto3;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;


public class KeyGenerator {
public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
	    kpg.initialize(512);
	    KeyPair keys = kpg.genKeyPair();
	    PrivateKey priKey = keys.getPrivate();
	    PublicKey pubKey = keys.getPublic();
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
		
	    }

}
