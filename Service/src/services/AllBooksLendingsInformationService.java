package services;

import exceptions.BusinessException;
import services.requests.AllBooksLendingsInformationRequest;
import services.responses.AllBooksLendingsInformationResponse;

public class AllBooksLendingsInformationService implements IService<AllBooksLendingsInformationRequest, AllBooksLendingsInformationResponse> {
    @Override
    public void validate(AllBooksLendingsInformationRequest allBooksLendingsInformationRequest) throws BusinessException {

    }

    @Override
    public AllBooksLendingsInformationResponse execute(AllBooksLendingsInformationRequest allBooksLendingsInformationRequest) throws BusinessException {
        return null;
    }

    @Override
    public AllBooksLendingsInformationResponse rejectResponseBuilder(BusinessException businessException) {
        return null;
    }
}
