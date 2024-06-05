package result;

public class Result {

    int code = 200;
    private boolean isError = false;
    private String message = null;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setError(String errorMessage) {
        isError = true;
        this.message = errorMessage;
    }

    public boolean isError() {
        return isError;
    }

    public String getErrorMessage() {
        return message;
    }
}
