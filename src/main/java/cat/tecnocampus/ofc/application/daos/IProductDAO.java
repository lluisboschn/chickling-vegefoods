// SPRINT 5

package cat.tecnocampus.ofc.application.daos;

import cat.tecnocampus.ofc.application.dtos.ProductDTO;

import java.util.List;

public interface IProductDAO {
    public void addProduct(ProductDTO product);
    public void updateProduct(ProductDTO product);
    public List<ProductDTO> getAllProducts();
}
