// SPRINT 5

package cat.tecnocampus.ofc.persistence;

import cat.tecnocampus.ofc.application.daos.IConsumerDAO;
import cat.tecnocampus.ofc.application.dtos.ConsumerDTO;
import cat.tecnocampus.ofc.application.exceptions.ConsumerDoesNotExistException;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerDAO implements IConsumerDAO {
    private  JdbcTemplate jdbcTemplate;
    public ConsumerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    ResultSetExtractorImpl<ConsumerDTO> consumersRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(ConsumerDTO.class);

    RowMapperImpl<ConsumerDTO> consumerRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(ConsumerDTO.class);

    @Override
    public ConsumerDTO getConsumer(String id) {
        final  var selectConsumer = "SELECT * FROM consumer WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(selectConsumer, consumerRowMapper, id);
        }catch (EmptyResultDataAccessException e){
            throw new ConsumerDoesNotExistException(id);
        }
    }

    //TODO
    @Override
    public ConsumerDTO getConsumerByEmail(String email) {
        final  var selectConsumer = "SELECT id,name,secondName,email FROM consumer WHERE email=?";
        try {
            return jdbcTemplate.queryForObject(selectConsumer, consumerRowMapper, email);
        }catch (EmptyResultDataAccessException e){
            throw new ConsumerDoesNotExistException(email);
        }
    }
}
