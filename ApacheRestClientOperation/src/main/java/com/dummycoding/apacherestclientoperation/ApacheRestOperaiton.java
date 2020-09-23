/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.apacherestclientoperation;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class ApacheRestOperaiton {

    public static void main(String[] args) throws Exception {

        //get Operations
        Header header = new BasicHeader("Content-Type", "application/json");
        get("http://dummy.restapiexample.com/api/v1/employees", header);
        
        Header[] headers = new Header[2];
        headers[0] = new BasicHeader("Content-Type", "application/json");
        headers[1] = new BasicHeader("Accept", "application/json");
        //post operation
        String postResponse = doPost("http://dummy.restapiexample.com/api/v1/create", "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}	", headers);
        System.out.println("postResponse = " + postResponse);
    }

    public static void get(String Url, Header header) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(Url);
            request.addHeader(header);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                System.out.println(response.getProtocolVersion());
                System.out.println(response.getStatusLine().getStatusCode());
                System.out.println(response.getStatusLine().getReasonPhrase());
                System.out.println(response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

    public static String doPost(String url, String body, Header... header) throws Exception {
        String result = "";
        HttpPost post = new HttpPost(url);
        post.setHeaders(header);
        post.setEntity(new StringEntity(body));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(post)) {
            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }

}
