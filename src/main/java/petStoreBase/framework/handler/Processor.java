package petStoreBase.framework.handler;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;
import io.qameta.allure.Attachment;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.xml.sax.InputSource;
import petStoreBase.framework.enums.AcceptType;
import petStoreBase.framework.enums.PayloadType;
import petStoreBase.framework.enums.RequestType;
import petStoreBase.framework.interfaces.Validator;
import petStoreBase.framework.launch.LaunchService;
import petStoreBase.staticData.StaticData;

import javax.net.ssl.*;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;


public class Processor {

    private StaticData staticdata = new StaticData();
    public Validator RequestValidator = null;
    public Validator ResponseValidator = null;
    public static final String PRINT_FORMAT = "%1$-30s%2$-50s\n";

    public Processor(LaunchService service, HashMap<String, String> headers, String[] payloadparams) {
        WebServiceDetails webservicedetails = service.APIDetails.webservicedata;
        APIDetails apidetails = service.APIDetails.apidetails;
        execute(webservicedetails, apidetails, headers, null, payloadparams, null, null, null);
    }


    public Processor(LaunchService service, HashMap<String, String> headers, String[] payloadparams, String[] urlparams) {
        WebServiceDetails webservicedetails = service.APIDetails.webservicedata;
        APIDetails apidetails = service.APIDetails.apidetails;
        execute(webservicedetails, apidetails, headers, null, payloadparams, urlparams, null, null);
    }


    private void execute(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams, HashMap<String, String> formdata, List<Cookie> cookies) {

        switch (getAPISendMethod(apidetails)) {

            case POST:
                executepost(wsdetails, apidetails, headers, Payload, payloadparams, urlparams, formdata, cookies);
                break;
            case PUT:
                executeput(wsdetails, apidetails, headers, Payload, payloadparams, urlparams, formdata);
                break;
            case GET:
                executeget(wsdetails, headers, apidetails, urlparams, cookies);
                break;
            case DELETE:
                executeDelete(wsdetails, apidetails, headers, cookies, urlparams);
                break;
            case DELETEWITHBODY:
                executeDeleteWithBody(wsdetails,apidetails, headers, Payload, payloadparams,urlparams,0);
                break;
            case PATCH:
                executepatch(wsdetails, apidetails, headers, Payload, payloadparams, urlparams);
                break;
        }
    }

    public void AssignResponseValidator(Response response) {


        ResponseValidator = new JsonValidator(response);

    }

    private void executeget(WebServiceDetails wsdetails, HashMap<String, String> header, APIDetails apidetails, String[] urlparams, List<Cookie> cookies) {


        WebTarget webTarget = getWebTarget(wsdetails, apidetails, urlparams);
        Invocation.Builder invocationBuilder;
        if (header != null && (header.containsKey("Content-Type") || header.containsKey("content-type"))) {
            invocationBuilder = webTarget.request();
        } else {
            invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        }
        if (header != null) {
            Iterator it = header.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry map = (Map.Entry) it.next();
                invocationBuilder.header(map.getKey().toString(), map.getValue());
            }
        }
        if (cookies != null) {
            invocationBuilder = appendCookie(invocationBuilder, cookies);
        }
        logRequest(HttpMethod.GET, header, wsdetails, apidetails, urlparams, null, null);
        Response response = invocationBuilder.get();
        logResponse(response);
        AssignResponseValidator(response);
    }

    private void executepost(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams, HashMap<String, String> formdata, List<Cookie> cookies) {
        invokePostRequest(wsdetails, apidetails, headers, Payload, payloadparams, urlparams, formdata, cookies);
    }


    private void executeput(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams, HashMap<String, String> formdata) {
        invokePutRequest(wsdetails, apidetails, headers, Payload, payloadparams, urlparams, formdata);
    }

    private void executepatch(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams) {
        invokePatchRequest(wsdetails, apidetails, headers, Payload, payloadparams, urlparams);
    }

    private void executeDelete(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, List<Cookie> cookie, String[] urlparams) {
        invokeDeleteRequest(wsdetails, apidetails, headers, cookie, urlparams);
    }

    private void executeDeleteWithBody(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams ,int dummy) {
        invokeDeleteRequestWithBody(wsdetails, apidetails, headers, Payload, payloadparams, urlparams);
    }

    private Invocation.Builder GetInvocationBuilder(APIDetails apidetails, WebServiceDetails wsdetails, String[] urlparams) {
        WebTarget target = getWebTarget(wsdetails, apidetails, urlparams);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);

        switch (getAccept(apidetails)) {

            case XML:
                builder = target.request(MediaType.APPLICATION_XML);
                break;
            case JSON:
                builder = target.request(MediaType.APPLICATION_JSON);
                break;
            case FORMDATA:
                builder = target.request(MediaType.MULTIPART_FORM_DATA);
                break;
            case URLENCODED:
                builder = target.request(MediaType.APPLICATION_FORM_URLENCODED);
                break;
            case TEXT_PLAIN:
                builder = target.request(MediaType.TEXT_PLAIN);
                break;
        }
        return builder;
    }


    private void invokePostRequest(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams, HashMap<String, String> formparams, List<Cookie> cookies) {
        Invocation.Builder invocationBuilder = GetInvocationBuilder(apidetails, wsdetails, urlparams);

        boolean payloadParameterizationRequired = false;
        boolean urlParameterizationRequired = false;
        if (headers != null && (headers.containsKey("Accept") || headers.containsKey("accept")))
            invocationBuilder.accept(headers.get("Accept"));
        System.out.println("Base URL ############ " + wsdetails.Baseurl + "/" + apidetails.APIPath);
        if (headers != null) {
            invocationBuilder = appendHeaders(invocationBuilder, headers);
        }

        if (cookies != null) {
            invocationBuilder = appendCookie(invocationBuilder, cookies);
        }

        if (payloadparams != null) {
            payloadParameterizationRequired = true;
        }

        System.out.println("Payloadparameterizationrequired = " + payloadParameterizationRequired);
        if (Payload != null) {

        } else {
            if (payloadParameterizationRequired) {
                Payload = appendParameterizedPayload(apidetails, payloadparams);
            } else {
                System.out.println("No Parameterization required");
                Payload = appendParameterizedPayload(apidetails, null);
            }
        }


        System.out.println("Payload = " + Payload);
        if (urlparams != null) {
            urlParameterizationRequired = true;
        }

        if (apidetails.APIPath.contains("${") && urlparams != null) {

            if (urlParameterizationRequired) {
                appendParameterizedURL(apidetails, urlparams);
            } else {
                appendParameterizedURL(apidetails, null);
            }
        }
        if (formparams != null) {
            FormDataMultiPart multiPart = new FormDataMultiPart();

            if (formparams.containsKey("file")) {
                File fileToUpload = new File(formparams.get("file").toString());
                FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                        new File(formparams.get("file").toString()), MediaType.APPLICATION_OCTET_STREAM_TYPE);
                fileDataBodyPart.setContentDisposition(
                        FormDataContentDisposition.name("file")
                                .fileName(fileToUpload.getName()).build());
                multiPart.bodyPart(fileDataBodyPart);
            }

            if (formparams.containsKey("document")) {
                File fileToUpload = new File(formparams.get("document").toString());
                FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("document",
                        new File(formparams.get("document").toString()), MediaType.APPLICATION_OCTET_STREAM_TYPE);
                fileDataBodyPart.setContentDisposition(
                        FormDataContentDisposition.name("document")
                                .fileName(fileToUpload.getName()).build());
                multiPart.bodyPart(fileDataBodyPart);
            }

            for (Map.Entry<String, String> entry : formparams.entrySet()) {
                multiPart.field(entry.getKey(), entry.getValue());
            }
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
            logRequest(HttpMethod.POST, headers, wsdetails, apidetails, urlparams, Payload, payloadparams);
            Response response = invocationBuilder.post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA_TYPE), Response.class);
            logResponse(response);
            AssignResponseValidator(response);
        } else {
            apidetails.PayloadBody = Payload;
            RequestValidator = new JsonValidator(Payload);
            logRequest(HttpMethod.POST, headers, wsdetails, apidetails, urlparams, Payload, payloadparams);
            Response response = invocationBuilder.post(Entity.json(Payload));
            logResponse(response);
            AssignResponseValidator(response);
        }
    }

    private void invokePutRequest(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams, HashMap<String, String> formparams) {
        Invocation.Builder invocationBuilder = GetInvocationBuilder(apidetails, wsdetails, urlparams);
        boolean Payloadparameterizationrequired = false;
        boolean urlparameterizationrequired = false;

        if (headers != null) {
            invocationBuilder = appendHeaders(invocationBuilder, headers);
        }

        if (payloadparams != null) {
            Payloadparameterizationrequired = true;
        }
        System.out.println("Payloadparameterizationrequired = " + Payloadparameterizationrequired);
        if (Payload != null) {

        } else {
            if (Payloadparameterizationrequired) {
                Payload = appendParameterizedPayload(apidetails, payloadparams);
            } else {
                System.out.println("No Parameterization required");
                Payload = appendParameterizedPayload(apidetails, null);
            }
        }

        System.out.println(Payload);
        //System.out.println("Payload = " + Payload);
        if (urlparams != null) {
            urlparameterizationrequired = true;
        }

        if (apidetails.APIPath.contains("${") && urlparams != null) {

            if (urlparameterizationrequired) {
                appendParameterizedURL(apidetails, urlparams);
            } else {
                appendParameterizedURL(apidetails, null);
            }
        }

        if (formparams != null) {
            FormDataMultiPart multiPart = new FormDataMultiPart();

            if (formparams.containsKey("file")) {
                File fileToUpload = new File(formparams.get("file").toString());
                FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                        new File(formparams.get("file").toString()), MediaType.APPLICATION_OCTET_STREAM_TYPE);
                fileDataBodyPart.setContentDisposition(
                        FormDataContentDisposition.name("file")
                                .fileName(fileToUpload.getName()).build());
                multiPart.bodyPart(fileDataBodyPart);
            }

            if (formparams.containsKey("document")) {
                File fileToUpload = new File(formparams.get("document").toString());
                FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("document",
                        new File(formparams.get("document").toString()), MediaType.APPLICATION_OCTET_STREAM_TYPE);
                fileDataBodyPart.setContentDisposition(
                        FormDataContentDisposition.name("document")
                                .fileName(fileToUpload.getName()).build());
                multiPart.bodyPart(fileDataBodyPart);
            }

            for (Map.Entry<String, String> entry : formparams.entrySet()) {
                multiPart.field(entry.getKey(), entry.getValue());
            }
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
            logRequest(HttpMethod.PUT, headers, wsdetails, apidetails, urlparams, Payload, payloadparams);
            Response response = invocationBuilder.put(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA_TYPE), Response.class);
            logResponse(response);
            AssignResponseValidator(response);
        } else {
            apidetails.PayloadBody = Payload;
            RequestValidator = new JsonValidator(Payload);
            logRequest(HttpMethod.PUT, headers, wsdetails, apidetails, urlparams, Payload, payloadparams);
            Response response = invocationBuilder.put(Entity.json(Payload));
            logResponse(response);
            AssignResponseValidator(response);
        }
    }

    private void invokePatchRequest(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams, String[] urlparams) {
        WebTarget webTarget = getWebTarget(wsdetails, apidetails, urlparams);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        //Invocation.Builder invocationBuilder =  GetInvocationBuilder(apidetails, wsdetails, urlparams);
        boolean Payloadparameterizationrequired = false;
        boolean urlparameterizationrequired = false;

        if (headers != null) {
            invocationBuilder = appendHeaders(invocationBuilder, headers);
        }

        if (payloadparams != null) {
            Payloadparameterizationrequired = true;
        }
        System.out.println("Payloadparameterizationrequired = " + Payloadparameterizationrequired);
        if (Payload != null) {

        } else if (Payloadparameterizationrequired) {
            Payload = appendParameterizedPayload(apidetails, payloadparams);
        } else {
            System.out.println("No Parameterization required");
            Payload = appendParameterizedPayload(apidetails, null);
        }

        System.out.println(Payload);
        //System.out.println("Payload = " + Payload);
        if (urlparams != null) {
            urlparameterizationrequired = true;
        }

        if (apidetails.APIPath.contains("${") && urlparams != null) {

            if (urlparameterizationrequired) {
                appendParameterizedURL(apidetails, urlparams);
            } else {
                appendParameterizedURL(apidetails, null);
            }
        }

        apidetails.PayloadBody = Payload;
        RequestValidator = new JsonValidator(Payload);
        Response response = invocationBuilder.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).method("PATCH", Entity.json(Payload));
        AssignResponseValidator(response);
        //System.out.println(response.readEntity(String.class));
    }

    private void invokeDeleteRequest(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, List<Cookie> cookies,
                                     String[] urlparams) {
        Invocation.Builder invocationBuilder = GetInvocationBuilder(apidetails, wsdetails, urlparams);
        boolean urlparameterizationrequired = false;

        if (headers != null) {
            invocationBuilder = appendHeaders(invocationBuilder, headers);
        }

        if (cookies != null) {
            invocationBuilder = appendCookie(invocationBuilder, cookies);
        }

        if (urlparams != null) {
            urlparameterizationrequired = true;
        }

        if (apidetails.APIPath.contains("${") && urlparams != null) {
            if (urlparameterizationrequired) {
                appendParameterizedURL(apidetails, urlparams);
            } else {
                appendParameterizedURL(apidetails, null);
            }
        }

        Response response = invocationBuilder.delete();
        AssignResponseValidator(response);
    }

    private void invokeDeleteRequestWithBody(WebServiceDetails wsdetails, APIDetails apidetails, HashMap<String, String> headers, String Payload, String[] payloadparams,
                                             String[] urlparams){
        Invocation.Builder invocationBuilder = GetInvocationBuilder(apidetails, wsdetails, urlparams);
        boolean urlparameterizationrequired = false;
        boolean Payloadparameterizationrequired = false;

        if (headers != null) {
            invocationBuilder = appendHeaders(invocationBuilder, headers);
        }

        if (payloadparams != null) {
            Payloadparameterizationrequired = true;
        }
        System.out.println("Payloadparameterizationrequired = " + Payloadparameterizationrequired);
        if (Payload != null) {

        } else if (Payloadparameterizationrequired) {
            Payload = appendParameterizedPayload(apidetails, payloadparams);
        } else {
            System.out.println("No Parameterization required");
            Payload = appendParameterizedPayload(apidetails, null);
        }
        System.out.println(Payload);

        if (urlparams != null) {
            urlparameterizationrequired = true;
        }

        if (apidetails.APIPath.contains("${") && urlparams != null) {

            if (urlparameterizationrequired) {
                appendParameterizedURL(apidetails, urlparams);
            } else {
                appendParameterizedURL(apidetails, null);
            }
        }

        apidetails.PayloadBody = Payload;
        RequestValidator = new JsonValidator(Payload);
        Response response = invocationBuilder.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION,true).method("DELETE", Entity.json(Payload));
        AssignResponseValidator(response);
    }

    private Invocation.Builder appendHeaders(Invocation.Builder invocationbuilder, HashMap<String, String> headers) {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                Object value = entry.getValue();
                invocationbuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return invocationbuilder;
    }

    private Invocation.Builder appendCookie(Invocation.Builder invocationbuilder, List<Cookie> cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                invocationbuilder.cookie(cookie);
            }
        }
        return invocationbuilder;
    }


    private WebTarget getWebTarget(WebServiceDetails wsdetails, APIDetails apidetails, String[] urlparams) {
        Client client = getClient();
        String url = ToolBox.decode(wsdetails.Baseurl + "/" + apidetails.APIPath);
        if (urlparams != null) {
            url = prepareurl(apidetails, wsdetails, urlparams);
            System.out.println("parameterized url = " + url);
        }

        WebTarget webTarget = client.target(url);
        webTarget.register(HandleRedirects.class);
        webTarget.register(MultiPartWriter.class);
        webTarget.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.TRUE);
        return webTarget;
    }

    private String preparePayload(APIDetails apidetails) {
        String payloadfolderpath = GetPayloadPath(apidetails);
        String payloadfilename = apidetails.ServiceName + "_" + apidetails.APIName;
        String payloadbody = "";
        ToolBox toolbox = new ToolBox();
        try {
            if (apidetails.PayloadRequired.equalsIgnoreCase("true"))
                payloadbody = toolbox.readFileAsString(payloadfolderpath + "/" + payloadfilename);
            else
                payloadbody = "${0}";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payloadbody;
    }

    private String prepareurl(APIDetails apidetails, WebServiceDetails wsdetails, String[] urlparams) {
        String url = wsdetails.Baseurl + "/" + apidetails.APIPath;
        ToolBox toolbox = new ToolBox();
        url = toolbox.Parameterize(url, urlparams);
        //return toolbox.decode(url);
        return url;
    }


    private String appendParameterizedPayload(APIDetails apidetails, String[] params) {
        if (params != null) {
            return parameterizePayload(preparePayload(apidetails), params);
        } else {
            return preparePayload(apidetails);
        }

    }


    private String appendParameterizedURL(APIDetails apidetails, String[] params) {
        if (params != null) {
            return parameterizeURL(apidetails.APIPath, params);
        } else {
            return apidetails.APIPath;
        }

    }

    private String parameterizePayload(String textdata, String[] parameters) {
        ToolBox utils = new ToolBox();
        String returnval = utils.Parameterize(textdata, parameters);
        return returnval;
    }

    private String parameterizeURL(String textdata, String[] parameters) {
        ToolBox utils = new ToolBox();
        String returnval = utils.Parameterize(textdata, parameters);
        return returnval;
    }

    private Invocation.Builder addCookies(Invocation.Builder invocationbuilder, HashMap<String, String> cookies) {

        return invocationbuilder;
    }

    private String GetPayloadPath(APIDetails apidata) {
        String basepayloadfolderpath = staticdata.getPayloadfolder();
        String payloadpath = basepayloadfolderpath + "/XML";
        switch (getPayloadtype(apidata)) {

            case XML:
                payloadpath = basepayloadfolderpath + "/" + "XML";
                break;
            case JSON:
                payloadpath = basepayloadfolderpath + "/" + "JSON";
                break;
            case FORMDATA:
                payloadpath = basepayloadfolderpath + "/" + "FORMDATA";
                break;
            case URLENCODED:
                payloadpath = basepayloadfolderpath + "/" + "URLENCODED";
                break;
        }
        return payloadpath;
    }

    private RequestType getAPISendMethod(APIDetails apidata) {
        return RequestType.valueOf(apidata.RequestMethod);
    }

    private PayloadType getPayloadtype(APIDetails apidata) {
        return PayloadType.valueOf(apidata.PayloadType.trim().toUpperCase());
    }

    private AcceptType getAccept(APIDetails apidata) {
        if(apidata.Accept == null){
            return AcceptType.valueOf(apidata.PayloadType.trim().toUpperCase());
        }
        return AcceptType.valueOf(apidata.Accept.trim().toUpperCase());
    }

    public SSLContext getSSLContext() {
        TrustManager[] trustManager = new X509TrustManager[]{new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {

            }
        }};

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManager, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    public Client getClient() {
        return ClientBuilder.newBuilder().sslContext(getSSLContext()).hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }).build();
    }

    public CloseableHttpResponse invokeDeleteWithPayload(LaunchService service, HashMap<String, String> headers, String payload, int dummy) throws IOException {

        WebServiceDetails webservicedetails = service.APIDetails.webservicedata;
        APIDetails apidetails = service.APIDetails.apidetails;
        String url = ToolBox.decode(webservicedetails.Baseurl + "/" + apidetails.APIPath);
        System.out.println("Invoking Delete with Payload");
        HttpDeleteWithBody http = new HttpDeleteWithBody();
        System.out.println("Headers are" + headers.toString());
        CloseableHttpResponse response = http.sendDelete(url, payload, headers);
        return response;
    }


    @Attachment("response")
    private String logResponse(Response response) {
        return logResponse(response, true);
    }

    private String logResponse(Response response, boolean printResponse) {

        if (response == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        String prefix = "\n******************************************** response *********************************************"
                + System.lineSeparator();
        builder.append(
                String.format(PRINT_FORMAT, "Status Line: ", response.getStatus() + " " + response.getStatusInfo() ));

        appendHeaders(builder, response.getHeaders());
        if (printResponse) {

            String contentType = "application/json";
            response.bufferEntity();
            Object body = response.readEntity(String.class);
            if (body != null) {
                if (body instanceof String ) {
                    if (contentType != null && (contentType.toString().contains("xml") || contentType.toString().contains("html"))) {
                        body = StringEscapeUtils.unescapeHtml((String) response.getEntity());
                    }
                }
                builder.append(String.format(PRINT_FORMAT, "Body: ", "\n" + prettyPrint(contentType.toString(), body)));
            }
        }
        String suffix = "***************************************************************************************************";
        String msg = builder.toString();

        return msg;
    }




    private void appendHeaders(StringBuilder builder, Map<String, ?> headers) {
        String name = "Headers: ";
        appendHeaders(builder, name, headers);
    }


    private void appendParams(StringBuilder builder, String[] payloadParams) {
        String name = "Payload Params: ";
        appendParams(builder, name, payloadParams);
    }


    private void appendParams(StringBuilder builder, String name, String[] payloadParams) {
        boolean isPayloadParams = name.toLowerCase().contains("Payload");

        if (payloadParams == null ) {
            builder.append(String.format(PRINT_FORMAT,"Payload Params: ", "NONE"));
        } else {
            for (String param: payloadParams) {
                builder.append(String.format(PRINT_FORMAT,"" +
                        "\n Payload Params: ", param));
            }
        }
    }



    @Attachment("request")
    private String logRequest(String methodType, HashMap<String, String> headers, WebServiceDetails wsdetails, APIDetails apidetails, String[] urlparams, String payload, String[] payloadparams) {

        String prefix = "\n********************************************* request *********************************************"
                + System.lineSeparator();
        String suffix = "***************************************************************************************************";



        StringBuilder builder = new StringBuilder();

        builder.append(String.format(PRINT_FORMAT, "Request HTTPMethod:", methodType));
        if (urlparams != null){
            builder.append(
                    String.format(PRINT_FORMAT, "Request URI: ", prepareurl(apidetails, wsdetails, urlparams)));
        }
        else {
            String url = ToolBox.decode(wsdetails.Baseurl + "/" + apidetails.APIPath);
            builder.append(
                    String.format(PRINT_FORMAT, "Request URI: ", url));
        }
        if(payloadparams != null)
            appendParams(builder, payloadparams);
        appendHeaders(builder,headers);
        if(payload != null)
            builder.append(String.format(PRINT_FORMAT, "Body: ", payload == null ? "none" : "\n" + payload));

        return builder.toString();

    }



    private void appendHeaders(StringBuilder builder, String name, Map<String, ?> map) {
        boolean isHeader = name.toLowerCase().contains("header");

        if (map == null || map.isEmpty()) {
            builder.append(String.format(PRINT_FORMAT, isHeader ? "Headers: " : name + " Params: ", "none"));
        } else {
            int i = 0;
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (i++ != 0) {
                    builder.append(String.format(PRINT_FORMAT, "", entry.getKey() + " = " + entry.getValue()));
                } else {
                    builder.append(String.format(PRINT_FORMAT, isHeader ? "Headers: " : name + " Params: ",
                            entry.getKey() + " = " + entry.getValue()));
                }
            }
        }
    }


    private Object prettyPrint(String contentType, Object body) {
        if (contentType == null) {
            return body;
        }
        if (body instanceof String) {
            try {
                return contentType.contains("xml") ? prettyPrintXml((String) body)
                        : new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                        .create().toJson(new JsonParser().parse((String) body));
            } catch (Exception e) {
                return body;
            }
        }
        return body;
    }


    private static String prettyPrintXml(String xmlString) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StreamResult result = new StreamResult(new StringWriter());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlString));
            DOMSource source = new DOMSource(db.parse(is));
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception ex) {
            return xmlString;
        }
    }
}








