package services;

import entities.Order;
import exceptions.BusinessException;
import interfaces.business.IOrderManager;
import services.requests.CancelOrderRequest;
import services.responses.CancelOrderResponse;

public class CancelOrderService implements IService<CancelOrderRequest, CancelOrderResponse>{

    private IOrderManager orderManager;

    public CancelOrderService(IOrderManager orderManager)
    {
        this.orderManager = orderManager;
    }

    @Override
    public void validate(CancelOrderRequest cancelOrderRequest) {

    }

    @Override
    public CancelOrderResponse execute(CancelOrderRequest cancelOrderRequest) throws BusinessException {
        Order canceledOrder = orderManager.cancelOrder(cancelOrderRequest.getOrderId(), cancelOrderRequest.getUserId());
        return new CancelOrderResponse(canceledOrder);
    }

    @Override
    public CancelOrderResponse rejectResponseBuilder(BusinessException businessException) {
        return new CancelOrderResponse(businessException);
    }
}
