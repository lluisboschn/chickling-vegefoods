// SPRINT 5

package cat.tecnocampus.ofc.application.daos;

import cat.tecnocampus.ofc.application.dtos.OrderDTO;
import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;

import java.util.List;

public interface IOrderDAO {
    void generateOrderfromSubscription(List <SubscriptionDTO> subscription);

    void updateOrder(String idOrder, String amount);

    List<OrderDTO> getOrders(String mail);

    public List<OrderDTO> getAllOrders();

    public void UpdateOrderEstate(String id, String status);
}
