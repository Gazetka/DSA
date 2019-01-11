package crypto3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
//import com.sun.javafx.iio.ImageFormatDescription.Signature; /* z ta bibliotek¹ jak wykomentuje siê ostatnia bibliotekê to podkreœnia na czerwono*/
import java.math.BigInteger;
import java.security.Signature;  /* tutaj nie podkresla, ale nie mooge u¿yæ tej wykomentowanej*/ 

/* https://docs.oracle.com/javase/tutorial/security/apisign/versig.html*/

public class DSAVerify {
	
	public void verify () throws  NoSuchAlgorithmException,Exception, InvalidKeySpecException  {
		

		String FILE_NAME = "C:\\TEMP\\1_1.txt";
		Path path;
		path = Paths.get(FILE_NAME);
		byte[] sigBytes;
		sigBytes=Files.readAllBytes(path);
		
		String FILE_NAME1 = "C:\\TEMP\\1.txt";
		Path path1;
		path1 = Paths.get(FILE_NAME1);
		byte[] sigBytes1 = null;
		sigBytes1=Files.readAllBytes(path1);
		
		
		// wziêcie klucza publicznego z pliku
        FileInputStream keyfis = new FileInputStream("C:\\TEMP\\keypub");
        byte[] encKey = new byte[keyfis.available()];  
        keyfis.read(encKey);
        keyfis.close();

        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        KeyFactory kf = KeyFactory.getInstance("DSA", "SUN");
        PublicKey pubKey = kf.generatePublic(pubKeySpec);
        
		
       // KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		
		//PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
		//DSAPublicKeySpec dsaPubKeySpec1 = (DSAPublicKeySpec)kf.getKeySpec(pubKey,DSAPublicKeySpec.class);
		/* Sprawdzenie czy to samo */
		//System.out.println("\n DSA Public Key sprawdzenie");
		//System.out.println("\ng = " + dsaPubKeySpec1.getG());
		//System.out.println("\np = " + dsaPubKeySpec1.getP());
		//System.out.println("\nq = " + dsaPubKeySpec1.getQ());
		//System.out.println("\ny = " + dsaPubKeySpec1.getY());
		
		
		/* Wczytanie podpisanej wiadomosci*/ 
       // FileInputStream sigfile = new FileInputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc_podpisana.pdf");
       // byte[] sigToVerify = new byte[sigfile.available()]; 
       // sigfile.read(sigToVerify);
       // sigfile.close();

	
        Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
        signature.initVerify(pubKey);
        signature.update(sigBytes1);
        
    	System.out.println(signature.verify(sigBytes));
    	
    	Files.write(path, sigBytes);
        
      /* Wetfikacja pliku */

      //  FileInputStream datafiles = new FileInputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc_podpisana.pdf");
      //  BufferedInputStream bufin = new BufferedInputStream(datafiles);

      //  byte[] buffer = new byte[1024];
      //  int len;
     //   while (bufin.available() != 0) {
       //     len = bufin.read(buffer);
       //     signature.update(buffer, 0, len);
        //    };

       // bufin.close();


       // boolean verifies = signature.verify(sigToVerify);

       // System.out.println("\n signature verifies: " + verifies);
		
		
	   
	}
	
	

}