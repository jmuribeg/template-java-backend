package sps.codeinterview.reto1.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sps.codeinterview.reto1.exception.ResourceNotFoundException;
import sps.codeinterview.reto1.model.Product;
import sps.codeinterview.reto1.repository.ProductRepository;

@RestController
@RequestMapping("/rest-ful")   //127.0.0.1:8080/rest-ful/products
@Validated
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    //traemos todos los productos en una lista
    @GetMapping("/products")
    public List<Product> getProducts() {
           	return productRepository.findAll();
    }

    //traemos un producuto por su id
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    	Product product =  null;
    	product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ids: " + id));
        return ResponseEntity.ok(product);
    }

    
    //Creamos Registro
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        if (product.getName() == null || product.getLabel() == null || product.getPrice() == null) {
            return ResponseEntity.badRequest().build();
        }
        product.setCreatedAt(new Date());
        Product savedProduct = null;
        savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    //Actualizamos un renglon por id 
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
    	Product existingProduct = null; 
    	
    	existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found id:" + id));
        
        existingProduct.setName(product.getName());
        existingProduct.setLabel(product.getLabel());
        existingProduct.setPrice(product.getPrice());
       
        Product updatedProduct =  null;
        
       updatedProduct = productRepository.save(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    
    /*Elimina por ID*/  
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    	  Product product = null;
    	  product = productRepository.findById(id) //busca por id
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
         }
    
    
/*Busca Coincidencias*/   
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
    	List<Product> products = null;
    	 products = productRepository.findByNameContainingOrderByNameDesc(name);
        return ResponseEntity.ok(products);
    }
}

