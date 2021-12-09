// SPRINT 5

package cat.tecnocampus.ofc.api;


import cat.tecnocampus.ofc.application.OFCController;
import cat.tecnocampus.ofc.application.ScheduleController;
import cat.tecnocampus.ofc.application.dtos.OrderDTO;
import cat.tecnocampus.ofc.application.dtos.ProductDTO;
import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Validated
@RestController
public class OFCRestController {
    OFCController ofcController;
    ScheduleController scheduleController;

    public OFCRestController(OFCController ofcController){
        this.ofcController=ofcController;
    }

    @PostMapping("/prodSubscription")
    public void addSubscription (@RequestBody @Valid SubscriptionDTO subscription){
        this.ofcController.addSubscription(subscription);
    }

    /*TODO passar nomes product id al addproduct del frontend o deixar com estava?
     @PostMapping("/prodSubscription")
    public void addSubscription (@RequestBody @Valid SubscriptionDTOAuxiliar subscription){ //TODO DTO PRODUCT amb id producte usuari suscripcio i prou
        this.ofcController.addSubscription(subscription);
    }
    * */

    @GetMapping("/consumers/{mailCons}/prodSubscription")
    public List<SubscriptionDTO> getSubscriptions(@PathVariable String mailCons) {
        return ofcController.getSubscriptions(mailCons);
    }

    @GetMapping("consumer/prodSubscription")
    public List<SubscriptionDTO> getSubscriptions(Principal principal) { //TODO treure idCons i fer getName
        return ofcController.getSubscriptions(principal.getName()); //TODO mirar quin name passa

    }

    @PostMapping("/consumers/{mailCons}/prodSubscription")
    public void generateOrderFromSubscription() {
        scheduleController.generateAllOrders();
    }

    @GetMapping("/consumers/{mailCons}/orders")
    public List<OrderDTO> getOrders(@PathVariable String mailCons){
        return ofcController.getOrders(mailCons);
    }

    @PutMapping("/orders/{idOrder}/{amount}")
    public void updateOrder(@PathVariable String idOrder, @PathVariable String amount){
        ofcController.updateOrder(idOrder, amount);
    }
    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        return ofcController.getProducts();
    }

}