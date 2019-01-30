package crypto3;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

public class DSA {
	
		//String St="crypto3\\";
		String FILE_NAME_DATA;
		String FILE_NAME_SIG;
		String FILE_NAME_KEY;
		Path PATH_DATA;
		Path PATH_SIG;
		Path PATH_KEY;
		byte[] data = null;
		byte[] sigBytes = null;
		byte[] keyp = null;
		PrivateKey priKey;
		PublicKey pubKey;
		DSAPrivateKeySpec dsaPriKeySpec;
		DSAPublicKeySpec dsaPubKeySpec;
		public String podpis, klucz;
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		
		public DSA() {
			
		}
		
		public DSA(String file) {
			this.FILE_NAME_DATA=file;
		
		}
		
		public void ReadData(String file) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
			PATH_DATA=Paths.get(file);
			data=Files.readAllBytes(PATH_DATA);
			
			podpis = file + ".sig";
			SingDataFile();
			SaveSignature(podpis);
			
		
		}
		
		public void readkey () {
			
		}

		//Inicjalizacja kluczy	
		public void InitKey() throws NoSuchAlgorithmException, NoSuchProviderException  {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA", "SUN");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			kpg.initialize(1024,random);
			KeyPair keypair = kpg.genKeyPair();
			priKey = keypair.getPrivate();
			pubKey = keypair.getPublic();
		}
	    
		public void SavePubKey () throws IOException {
			keyp = pubKey.getEncoded();
			FileOutputStream keypub = new FileOutputStream( s+ "keypub");
			keypub.write(keyp);
			keypub.close();	
			
			
		}
		
		public void SingDataFile() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
			Signature signature = Signature.getInstance("SHA1withDSA","SUN");
			signature.initSign(priKey);
			signature.update(data);
			sigBytes = signature.sign();
		}
		
		public void SaveSignature(String file) throws IOException {
			PATH_SIG=Paths.get(file);
			Files.write(PATH_SIG, sigBytes);
		}
		
		public void GetNumberKey () throws NoSuchAlgorithmException, InvalidKeySpecException {
			KeyFactory kf = KeyFactory.getInstance("DSA");
			dsaPriKeySpec = (DSAPrivateKeySpec) kf.getKeySpec(priKey,DSAPrivateKeySpec.class);
			dsaPubKeySpec = (DSAPublicKeySpec) kf.getKeySpec(pubKey,DSAPublicKeySpec.class);
		}
		
		public String getPrivKey () {   				//dsaPriKeySpec.getX().toString();
			return dsaPriKeySpec.getX().toString();
		}
		
		public String getPubKeyP() {
			return dsaPubKeySpec.getP().toString();	
		}
		
		public String getPubKeyQ() {
			return dsaPubKeySpec.getQ().toString();	
		}
		
		public String getPubKeyG() {
			return dsaPubKeySpec.getG().toString();	
		}
		
		public String getPubKeyY() {
			return dsaPubKeySpec.getY().toString();		
		}
		
		
	 
}		