public class MissingParameterException extends MaritesException {
    private final String missingParameterTag;

    public MissingParameterException(String missingParameterTag) {
        this.missingParameterTag = missingParameterTag;
    }

    public String getMissingParameterTag() {
        return missingParameterTag;
    }
}
