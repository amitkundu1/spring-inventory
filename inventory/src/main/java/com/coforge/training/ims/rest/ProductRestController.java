package com.coforge.training.ims.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.ims.exception.ResourceNotFoundException;
import com.coforge.training.ims.model.Product;
import com.coforge.training.ims.service.ProductRestService;
import com.coforge.training.ims.service.ProductService;

@RestController
@CrossOrigin(origins="http://localhost4200")
@RequestMapping(value = "/api")
public class ProductRestController {

    @Autowired
    private ProductRestService pservice;
    
    //Open PostMan, make a GET Request - http://localhost:8085/ims/api/products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
         return pservice.listAll();   
    }

    /**
     * ResponseEntity represents an HTTP response, including headers, body, and status.
     * 
     * @PathVariable is a Spring annotation which indicates that a method parameter should be
     *  bound to a URI template variable.
       @PathVariable annotation is used to read an URL template variable.
     */

   //Open PostMan, make a GET Request - http://localhost:8085/ims/api/products/4
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long pId)
               throws ResourceNotFoundException {
           Product product = pservice.getSingleProduct(pId) 
                   .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));
           return ResponseEntity.ok().body(product);
    }
    
 // @RequestBody annotation automatically deserializes the JSON into a Java type

    //Open PostMan, make a POST Request - http://localhost:8085/ims/api/products/
    //Select body -> raw -> JSON 
    //Insert JSON product object.
    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product product) {

        Product p=pservice.saveProduct(product);
        return ResponseEntity.ok(p);

    }

    //Open PostMan, make a PUT Request - http://localhost:8085/ims/api/products/3
    //Select body -> raw -> JSON 
    //Update JSON product object with new Values.
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long pId,
            @Validated @RequestBody Product p) throws ResourceNotFoundException {

        Product product = pservice.getSingleProduct(pId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));
        
        
        product.setBrand(p.getBrand());
        product.setMadein(p.getMadein());
        product.setName(p.getName());
        product.setPrice(p.getPrice());

        final Product updatedProduct = pservice.saveProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    //Open PostMan, make a DELETE Request - http://localhost:8085/ims/api/products/4
    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long pId)
            throws ResourceNotFoundException{
        pservice.getSingleProduct(pId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));
        pservice.delete(pId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
   }


}

