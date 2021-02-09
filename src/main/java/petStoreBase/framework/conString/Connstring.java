package petStoreBase.framework.conString;


import javax.xml.bind.annotation.XmlAttribute;

public class Connstring {
    private String name, server, dbid, username, password, protocol;
    private int port;


    public Connstring(String _name, String _server, String _dbid, String _port, String _username, String _password, String _connectionmethod, String _protocol) {
        setName(_name);
        setServer(_server);
        setDbid(_dbid);
        setUsername(_username);
        setPassword(_password);
        setProtocol(_protocol);
    }

    public Connstring(String _name, String _server, String _hostname, int _port) {
        setName(_name);
        setServer(_server);
        setPort(_port);
    }

    public Connstring() {

    }


    @XmlAttribute
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    @XmlAttribute
    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }


    @XmlAttribute
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    @XmlAttribute
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getDbid() {
        return dbid;
    }

    @XmlAttribute
    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getServer() {
        return server;
    }

    @XmlAttribute
    public void setServer(String server) {
        if (server.toString().contains("${env}")) {
            String url = (System.getProperty("sit-env") != null) ? System.getProperty("sit-env") : "dev1";
            server = server.replace("${env}", url);
            System.out.println("Server url is " + server);
        }
        this.server = server;
    }
}


