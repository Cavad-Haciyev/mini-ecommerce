package com.example.bank.service.product;

import com.example.bank.dto.product.ProductConverter;
import com.example.bank.dto.product.ProductResponse;
import com.example.bank.exception.ProductNotFoundException;
import com.example.bank.model.Product;
import com.example.bank.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public ProductResponse getOneProduct(Long id){
      Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException());
      return productConverter.convert(product);
    }
    public Product getOneProducts(Long id){

return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException());

    }
}
