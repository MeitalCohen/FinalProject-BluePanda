package services;

import entities.Order;
import exceptions.BusinessException;
import interfaces.business.IOrderManager;
import services.requests.ApproveOrderRequest;
import services.responses.ApproveOrderResponse;

public class ApproveOrderService implements IService<ApproveOrderRequest, ApproveOrderResponse> {
    private IOrderManager orderManager;

    public ApproveOrderService(IOrderManager orderManager)
    {
        this.orderManager = orderManager;
    }

    @Override
    public void validate(ApproveOrderRequest approveOrderRequest) {

    }

    @Override
    public ApproveOrderResponse execute(ApproveOrderRequest approveOrderRequest) throws BusinessException {
        Order order = orderManager.approveOrder(approveOrderRequest.getUserId(), approveOrderRequest.getOrder());
        return new ApproveOrderResponse(order);
    }

    @Override
    public ApproveOrderResponse rejectResponseBuilder(BusinessException businessException) {
        return new ApproveOrderResponse(businessException);
    }

}
