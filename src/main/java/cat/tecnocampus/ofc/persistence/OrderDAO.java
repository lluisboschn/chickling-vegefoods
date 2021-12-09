// SPRINT 5

package cat.tecnocampus.ofc.persistence;

import cat.tecnocampus.ofc.application.daos.IOrderDAO;
import cat.tecnocampus.ofc.application.dtos.OrderDTO;
import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderDAO implements IOrderDAO {
    private JdbcTemplate jdbcTemplate;

    ResultSetExtractorImpl<OrderDTO> ordersRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(OrderDTO.class);

    RowMapperImpl<OrderDTO> orderRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(OrderDTO.class);

    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Async
    @Override
    public void generateOrderfromSubscription(List<SubscriptionDTO> subscriptions) {
        for(SubscriptionDTO sub:subscriptions){
            OrderDTO order= new OrderDTO(UUID.randomUUID().toString(),sub.getConsumerMail(),
                    sub.getClose_date(),
                    "OPEN",
                    sub.getId(),
                    sub.getProduct(),
                    sub.getAmount(),
                    sub.getPrice());

            final String insertOrder = "INSERT INTO productOrder (id, email, dat_deliver, close_date, status,idSubs, productId, amount,price, date) VALUES (?,?,?,?,?,?,?,?,?,?);";

            jdbcTemplate.update(insertOrder, order.getId(), order.getEmail(),order.getDat_deliver(),order.getClose_date(),
                    order.getStatus(), order.getIdSubs(), order.getProductId().getId(), order.getAmount(),
                    order.getPrice(), order.getDate());
        }
    }

    @Override
    public void updateOrder(String idOrder, String amount) {
        String updateQuery="update productOrder SET amount=? where id=?;";
        jdbcTemplate.update(updateQuery, amount,idOrder);
    }

    @Override
    public List<OrderDTO> getOrders(String mail) {
        final String query="select o.id as id, o.email as email, o.dat_deliver as dat_deliver, o.close_date as close_date, o.status as status, " +
                "o.idSubs as idSubs, o.amount as amount,p.id as productId_id, p.name as productId_name, p.price as productId_price, " +
                "p.measure_unit  as productId_measure_unit, p.provider as productId_provider, p.vat_type as productId_vat_type," +
                " p.category as productId_category, p.initial_date as productId_initial_date, p.day_of_week as productId_day_of_week, " +
                "p.num_of_periods as productId_num_of_periods, p.period as productId_period, p.image as productId_image from productOrder o " +
                "join product p on o.productId=p.id where email=?";

        List<OrderDTO> result=new ArrayList<OrderDTO>();
        result=jdbcTemplate.query(query, ordersRowMapper,mail);
        return result;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        final String query="select o.id as id, o.email as email, o.dat_deliver as dat_deliver, o.close_date as close_date, o.status as status, " +
                "o.idSubs as idSubs, o.amount as amount,p.id as productId_id, p.name as productId_name, p.price as productId_price, " +
                "p.measure_unit  as productId_measure_unit, p.provider as productId_provider, p.vat_type as productId_vat_type," +
                " p.category as productId_category, p.initial_date as productId_initial_date, p.day_of_week as productId_day_of_week, " +
                "p.num_of_periods as productId_num_of_periods, p.period as productId_period, p.image as productId_image from productOrder o " +
                "join product p on o.productId=p.id ";

        List<OrderDTO> result=new ArrayList<OrderDTO>();
        result=jdbcTemplate.query(query, ordersRowMapper);
        return result;
    }

    @Override
    public void UpdateOrderEstate(String id, String status) {
        //Buscar les OPEN i si els hi toca TANCAR
        //Buscar les CLOSED i si els hi topca DELIVERED
        final String query="UPDATE status=? FROM productOrder WHERE id=?";
        jdbcTemplate.update(query,status, id);
    }


}
