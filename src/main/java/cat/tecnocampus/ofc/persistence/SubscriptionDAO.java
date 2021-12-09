// SPRINT 5

package cat.tecnocampus.ofc.persistence;

import cat.tecnocampus.ofc.application.daos.ISubscriptionDAO;
import cat.tecnocampus.ofc.application.dtos.SubscriptionDTO;
import cat.tecnocampus.ofc.application.exceptions.SubscriptionDoesNotExistException;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionDAO implements ISubscriptionDAO {
    private JdbcTemplate jdbcTemplate;
    public SubscriptionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ResultSetExtractorImpl<SubscriptionDTO> SubscriptionsRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(SubscriptionDTO.class);

    RowMapperImpl<SubscriptionDTO> SubscriptionRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(SubscriptionDTO.class);

    @Override
    public List<SubscriptionDTO> getSubscriptions(String mail) {
        System.out.println("The sent mail is: "+mail);

        final String query="select s.id as id, s.consumerMail as consumerMail, s.amount as amount, s.price as price, " +
                "s.creation_date as creation_date, s.close_date as close_date, " +
                "s.consumerId as consumerId,p.id as product_id, p.name as product_name, p.price as product_price, " +
                "p.measure_unit  as product_measure_unit, p.provider as product_provider, p.vat_type as product_vat_type," +
                " p.category as product_category, p.initial_date as product_initial_date, p.day_of_week as product_day_of_week, " +
                "p.num_of_periods as product_num_of_periods, p.period as product_period, p.image as product_image " +
                "from subscription s " +
                "join product p on s.productId=p.id where s.consumerMail=?";

        System.out.println("The query is: "+query);
        return jdbcTemplate.query(query, SubscriptionsRowMapper, mail);
    }

    @Override
    public SubscriptionDTO getSubscription(String mail) {
        /*
        * "select id, consumerMail, productId, amount, price, creation_date, close_date, consumerId " +
                "from subscription WHERE consumerId=? ";
        * */
        final String query="select s.id as id, s.consumerMail as consumerMail, s.amount as amount, s.price as price, " +
                "s.creation_date as creation_date, s.close_date as close_date, " +
                "s.consumerId as consumerId,p.id as product_id, p.name as product_name, p.price as product_price, " +
                "p.measure_unit  as product_measure_unit, p.provider as product_provider, p.vat_type as product_vat_type," +
                " p.category as product_category, p.initial_date as product_initial_date, p.day_of_week as product_day_of_week, " +
                "p.num_of_periods as product_num_of_periods, p.period as product_period, p.image as product_image from subscription s " +
                "join product p on s.productId=p.id where s.consumerMail=?";

        try {
            return jdbcTemplate.queryForObject(query, SubscriptionRowMapper, mail);
        } catch (EmptyResultDataAccessException e) {
            throw new SubscriptionDoesNotExistException(mail);
        }
    }

    @Override
    public void addSubscription(SubscriptionDTO subscription){
        final var query = "INSERT INTO subscription (id, consumerMail, productId, amount,price,creation_date, close_date, consumerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, subscription.getId(), subscription.getConsumerMail(),
                subscription.getProduct().getId(), subscription.getAmount(), subscription.getPrice(),
                subscription.getCreation_date(), subscription.getClose_date(), subscription.getConsumerId());
    }

    //TODO
    /*
        @Override
    public void addSubscription(SubscriptionDTOAuxiliar subscription){
        final var query = "INSERT INTO subscription (id, consumerMail, productId, amount,price,creation_date, close_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, subscription.getId(), subscription.getConsumerMail(),
                subscription.getProductId(), subscription.getAmount(), subscription.getPrice(),
                subscription.getCreation_date(), subscription.getClose_date());
    }
    * */

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        final var query="select id, consumerMail, productId, amount, price, creation_date, close_date, consumerId from subscription";
        return jdbcTemplate.query(query, SubscriptionsRowMapper);

    }

}
