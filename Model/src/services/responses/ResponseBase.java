package services.responses;

import enums.ResponseStatus;
import exceptions.BusinessException;

public abstract class ResponseBase {

    protected String errorMessage;
    protected int status;

    public void rejectResponse(BusinessException exception)
    {
        errorMessage = exception.getMessage();
        status = ResponseStatus.Error.errorCode();
    }

    public void rejectResponse(String errorMessage)
    {
        this.errorMessage = errorMessage;
        status = ResponseStatus.Error.errorCode();
    }

    public void buildResponse()
    {
        errorMessage = "";
        status = ResponseStatus.OK.errorCode();
    }
}