package kr.co.mashup.moamoa.server;

public class ServerResult {
    private String resultStatus;

    public ServerResult(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }
}
