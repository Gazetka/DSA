package crypto3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
//import com.sun.javafx.iio.ImageFormatDescription.Signature;
import java.math.BigInteger;
import java.security.Signature;

public class DSAVerify {
	
	public void verify () throws  NoSuchAlgorithmException,Exception, InvalidKeySpecException  {
		

		// wziêcie klucza publicznego z pliku

        FileInputStream keyfis = new FileInputStream("C:\\Users\\cp24\\Desktop\\keypub.txt");
        byte[] encKey = new byte[keyfis.available()];  
        keyfis.read(encKey);
        keyfis.close();

        KeyFactory kf = KeyFactory.getInstance("DSA");
       
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
		
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		
		PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
	
		DSAPublicKeySpec dsaPubKeySpec1 = (DSAPublicKeySpec)kf.getKeySpec(pubKey,DSAPublicKeySpec.class);
		
		System.out.println("\n DSA Public Key sprawdzenie");
		System.out.println("\ng = " + dsaPubKeySpec1.getG());
		System.out.println("\np = " + dsaPubKeySpec1.getP());
		System.out.println("\nq = " + dsaPubKeySpec1.getQ());
		System.out.println("\ny = " + dsaPubKeySpec1.getY());
		 
        FileInputStream sigfile = new FileInputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc_podpisana.pdf");
        byte[] sigToVerify = new byte[sigfile.available()]; 
        sigfile.read(sigToVerify);
        sigfile.close();

       
        Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
        signature.initVerify(pubKey);

      

        FileInputStream datafiles = new FileInputStream("C:\\Users\\cp24\\Desktop\\Wiadomosc_podpisana.pdf");
        BufferedInputStream bufin = new BufferedInputStream(datafiles);

        byte[] buffer = new byte[1024];
        int len;
        while (bufin.available() != 0) {
            len = bufin.read(buffer);
            signature.update(buffer, 0, len);
            };

        bufin.close();


        boolean verifies = signature.verify(sigToVerify);

        System.out.println("\n signature verifies: " + verifies);
		
		
	   
	}
	
	

}