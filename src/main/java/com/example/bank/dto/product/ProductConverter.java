package com.example.bank.dto.product;

import com.example.bank.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductResponse convert(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setStock(product.getStock());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
    public Product
    convert2(ProductResponse productResponse){
        Product product = new Product();
        product.setId(productResponse.getId());
        product.setName(productResponse.getName());
        product.setStock(productResponse.getStock());
        product.setPrice(productResponse.getPrice());
        return product;
    }
}
