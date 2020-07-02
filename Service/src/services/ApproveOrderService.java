package services;

import entities.Order;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IOrderManager;
import interfaces.repository.*;
import managers.AuthenticationValidator;
import managers.OrderManager;
import services.requests.ApproveOrderRequest;
import services.responses.ApproveOrderResponse;

public class ApproveOrderService implements IService<ApproveOrderRequest, ApproveOrderResponse> {
    private IOrderManager orderManager;
    private IAuthenticationValidator authenticationValidator;

    public ApproveOrderService(IOrderRepository orderRepository, IUserRepository userRepository, IBookStockRepository bookStockRepository,
                               IConfigurationRepository configurationRepository, IBooksInOrdersRepository booksInOrdersRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.orderManager = new OrderManager(orderRepository, userRepository, bookStockRepository, configurationRepository, booksInOrdersRepository);
    }

    @Override
    public void validate(ApproveOrderRequest approveOrderRequest) throws BusinessException{
        this.authenticationValidator.ValidateUserId(approveOrderRequest.getUserId());

        if (approveOrderRequest.getOrder() == null)
            throw new InvalidRequestException("ApproveOrderRequest");
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
