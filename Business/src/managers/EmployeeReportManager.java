package managers;

import entities.User;
import interfaces.repository.IEventRepository;
import interfaces.repository.IOrderRepository;
import interfaces.repository.IUserRepository;

import java.util.HashSet;
import java.util.Hashtable;

public class EmployeeReportManager {
    private IUserRepository userRepository;
    private IEventRepository eventRepository;
    private IOrderRepository orderRepository;

    public EmployeeReportManager(IUserRepository userRepository, IEventRepository eventRepository,
                                 IOrderRepository orderRepository)
    {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.orderRepository = orderRepository;
    }


}
