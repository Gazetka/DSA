package crypto3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.math.BigInteger;
import java.security.Signature;
import java.security.SignatureException;



public class DSAVerify {
	
		
		String FILE_NAME_DATA;
		public String FILE_NAME_SIG;
		String FILE_NAME_PUBKEY;
		Path PATH_DATA;
		Path PATH_SIG;
		public byte[] data = null;
		public byte[] sigBytes = null;
		PublicKey pubKey;
		DSA jeden;
		
		
	/*	public DSAVerify() {
			
		}
		
	*/	
		public void setTest(String test) {
			this.FILE_NAME_SIG=test;
		}
		
		public DSAVerify(String file, String pubfile) {
			this.FILE_NAME_DATA=file;
			this.FILE_NAME_PUBKEY=pubfile;
		}
		
		public void ReadDATA () throws IOException {
			PATH_DATA=Paths.get(FILE_NAME_DATA);
			data=Files.readAllBytes(PATH_DATA);
			PATH_SIG=Paths.get(FILE_NAME_SIG);
			sigBytes=Files.readAllBytes(PATH_SIG);
		}
		
		
	//	String FILE_NAME = "C:\\TEMP\\1_1.txt";
	//	Path path;
	//	path = Paths.get(FILE_NAME);
	//	byte[] sigBytes;
	//	sigBytes=Files.readAllBytes(path);
		
	//	String FILE_NAME1 = "C:\\TEMP\\1.txt";
	//	Path path1;
	//	path1 = Paths.get(FILE_NAME1);
	//	byte[] sigBytes1 = null;
	//	sigBytes1=Files.readAllBytes(path1);
		
		
		// wziêcie klucza publicznego z pliku
      public void TakeKey () throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
    	  
      		FileInputStream keyfis = new FileInputStream(FILE_NAME_PUBKEY);
      		byte[] encKey = new byte[keyfis.available()];  
      		keyfis.read(encKey);
      		keyfis.close();

      		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
      		KeyFactory kf = KeyFactory.getInstance("DSA", "SUN");
      		pubKey = kf.generatePublic(pubKeySpec);
        
      }
      

      	public boolean CheckSig () throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, IOException{
      		
      		Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
      		signature.initVerify(pubKey);
      		signature.update(data);
        
      		boolean verifies = signature.verify(sigBytes);
      		
         	return verifies;
      	}
   
		
		


}