package com.hnmobile.wechat.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileManager {

	public File getFile( String url ){
		return new File(url);
	}
	
    /** 
     * 读取管道中的流数据 
     * @throws IOException 
     */  
    public byte[] getImgStream( String url ) throws IOException {  
    	File file = new File(url);
        FileInputStream fips = new FileInputStream(file);
        ByteArrayOutputStream bops = new ByteArrayOutputStream();  
        int data = -1;  
        try {  
            while((data = fips.read()) != -1){  
                bops.write(data);  
            }  
            return bops.toByteArray();  
        }catch(Exception e){  
            return null;  
        }finally{
        	fips.close();
        }
    } 
	
}
