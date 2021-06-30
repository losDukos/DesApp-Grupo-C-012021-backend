package ar.edu.unq.desapp.grupoC.backenddesappapi.dto;

public class ListenForReviewsRequestBody {
    private String platform;
    private String title;
    private String callback;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
