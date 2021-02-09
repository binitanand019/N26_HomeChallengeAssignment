package petStoreBase.framework.handler;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpDeleteWithBody() {
        super();
    }




    public CloseableHttpResponse sendDelete(String URL, String PARAMS, HashMap<String, String> header) throws IOException
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(URL);
        StringEntity input = new StringEntity(PARAMS, ContentType.APPLICATION_JSON);
        //Adding Headers
        for (String key : header.keySet())
        {

            httpDelete.addHeader(key,header.get(key));

        }

        httpDelete.setEntity(input);
        Header requestHeaders[] = httpDelete.getAllHeaders();
        System.out.println(requestHeaders.length);
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        return response;

    }
}

