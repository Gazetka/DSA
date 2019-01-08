package crypto3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.math.BigInteger;



public class load_the_file {
	
	public void file() throws IOException {
		
		byte[] pubkey = Files.readAllBytes(new File("C:\\Users\\cp24\\Desktop\\keypub").toPath());
		
	  	BigInteger b1= new BigInteger(pubkey);
		
		byte[] key1 = b1.toByteArray();
		
		BigInteger b2= new BigInteger(key1);
		
		
		
	   
	}

}