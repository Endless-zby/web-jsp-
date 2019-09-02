package com.zby.entity;

public class sql {
    private String Version;
    private String Dbname;
    private String Username;

    public sql(String version, String dbname, String username) {
        this.Version = version;
        this.Dbname = dbname;
        this.Username = username;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getDbname() {
        return Dbname;
    }

    public void setDbname(String dbname) {
        Dbname = dbname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

}
