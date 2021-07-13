package CsvFile.model;

import java.util.Objects;

public class ConfigCsvFile {
    private int serverPort;
    private String clientIp;
    private int clientPort;

    public ConfigCsvFile(int serverPort, String clientIp, int clientPort) {
        this.serverPort = serverPort;
        this.clientIp = clientIp;
        this.clientPort = clientPort;
    }

    public ConfigCsvFile() {
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    @Override
    public String toString() {
        return "ConfigCsvFile{" +
                "serverPort=" + serverPort +
                ", clientIp='" + clientIp + '\'' +
                ", clientPort=" + clientPort +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigCsvFile that = (ConfigCsvFile) o;
        return serverPort == that.serverPort && clientPort == that.clientPort && Objects.equals(clientIp, that.clientIp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverPort, clientIp, clientPort);
    }
}
