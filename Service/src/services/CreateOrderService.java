package services;

import entities.Order;
import exceptions.BusinessException;
import interfaces.business.IOrderManager;
import services.requests.CreateOrderRequest;
import services.responses.CreateOrderResponse;

public class CreateOrderService implements IService<CreateOrderRequest, CreateOrderResponse> {

    private IOrderManager orderManager;

    public CreateOrderService(IOrderManager orderManager)
    {
        this.orderManager = orderManager;
    }


    @Override
    public void validate(CreateOrderRequest createOrderRequest) {

    }

    @Override
    public CreateOrderResponse execute(CreateOrderRequest request) throws BusinessException {
        Order order = orderManager.insertOrder(request.getUserId(), request.getNewOrder(), request.getBooksInOrder());
        return new CreateOrderResponse(order);
    }

    @Override
    public CreateOrderResponse rejectResponseBuilder(BusinessException businessException) {
        return new CreateOrderResponse(businessException);
    }
}
