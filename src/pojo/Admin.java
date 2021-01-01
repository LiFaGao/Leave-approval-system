package pojo;

public class Admin {
    private String adUsername;
    private String adPassword;

    public Admin() {
    }

    public Admin(String adUsername, String adPassword) {
        this.adUsername = adUsername;
        this.adPassword = adPassword;
    }

    public String getAdUsername() {
        return adUsername;
    }

    public void setAdUsername(String adUsername) {
        this.adUsername = adUsername;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adUsername='" + adUsername + '\'' +
                ", adPassword='" + adPassword + '\'' +
                '}';
    }
}
