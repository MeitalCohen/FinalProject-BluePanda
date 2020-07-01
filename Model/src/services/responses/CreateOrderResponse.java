package services.responses;

import entities.Order;
import exceptions.BusinessException;

public class CreateOrderResponse extends ResponseBase{

    private Order order;

    public CreateOrderResponse(Order order)
    {
        this.buildResponse();
        this.order = order;
    }

    public CreateOrderResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.order = null;
    }

    public Order getOrder(){return order;}
}
