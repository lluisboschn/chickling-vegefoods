// SPRINT 5

package cat.tecnocampus.ofc.application.daos;

import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;

import java.util.List;

public interface IOFCdao {
    public void addSubscription (SubscriptionDTO subscription);
    public List<SubscriptionDTO> getSubscriptions ();
}
