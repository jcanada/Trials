package com.example.rikacanada.androidtopicreport1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rika Canada on 5/17/2016.
 */
public class Flickr {
    public static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    public static final String API_KEY = "a0a2b0a51e0070e01e2cd92050d973fb";
    
    private byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;
            
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer))>0)
                out.write(buffer, 0, bytesRead);
            
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }
    
    public String getUrl() throws IOException{
        return new String(getUrlBytes(ENDPOINT));
    }
}
