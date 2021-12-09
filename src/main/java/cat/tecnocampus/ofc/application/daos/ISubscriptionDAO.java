// SPRINT 5

package cat.tecnocampus.ofc.application.daos;

import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;
import cat.tecnocampus.ofc.persistence.SubscriptionDAO;

import java.util.List;

public interface ISubscriptionDAO {
    public List<SubscriptionDTO> getSubscriptions(String mail);
    public SubscriptionDTO getSubscription(String id);
    public void addSubscription(SubscriptionDTO subscription);
    List<SubscriptionDTO> getAllSubscriptions();
}
