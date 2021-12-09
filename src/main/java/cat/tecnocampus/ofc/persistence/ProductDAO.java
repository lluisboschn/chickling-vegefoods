// SPRINT 5

package cat.tecnocampus.ofc.persistence;

import cat.tecnocampus.ofc.application.daos.IProductDAO;
import cat.tecnocampus.ofc.application.dtos.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;

import java.util.List;

@Repository
public class ProductDAO implements IProductDAO {
    List<SubscriptionDTO> subscriptions;
    ConsumerDTO consumer;

    private JdbcTemplate jdbcTemplate;

    ResultSetExtractorImpl<ProductDTO> productsRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newResultSetExtractor(ProductDTO.class);

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void addProduct(ProductDTO product) {
        final String insertProduct = "INSERT INTO product (id, name, period,price) VALUES (?, ?, ?,?)";
        jdbcTemplate.update(insertProduct, product.getId(), product.getName(), product.getPeriod(),product.getPrice());
    }
    @Override
    public void updateProduct(ProductDTO product){
        final String updateProduct = "UPDATE product set  name=?, period=?, price=? WHERE id=?";
        jdbcTemplate.update(updateProduct,  product.getName(), product.getPeriod(),product.getPrice(),product.getId());
    }

    @Override
    public List<ProductDTO> getAllProducts(){
        final var result="select * from product";
        return jdbcTemplate.query(result, productsRowMapper);
    }

    /*
    //update preu producte
    @Override
    public void updateProductPrice(ProductDTO product){
        //agafar preus del repositori i posar-los aqui
        //batch update (exemple tinder!!)
        final String updateProduct = "UPDATE product set  price=? WHERE id=?";
        jdbcTemplate.update(updateProduct,  product.getPrice(),product.getId());
    }
    */
}
