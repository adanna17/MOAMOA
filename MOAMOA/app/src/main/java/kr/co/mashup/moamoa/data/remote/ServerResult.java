package kr.co.mashup.moamoa.data.remote;

public class ServerResult {
    private String resultStatus;

    public ServerResult(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }
}
