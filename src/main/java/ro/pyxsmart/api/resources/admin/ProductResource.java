package ro.pyxsmart.api.resources.admin;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.services.ProductService;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
@Slf4j
public class ProductResource {

    private final ProductService productService;

    @GetMapping("/test")
    public String getTest(){
        return "It is working";
    }

    @PostMapping("/new")
    public ResponseEntity<@NonNull HttpResponse<ProductAdminDTO>>  createNewProduct(@RequestBody ProductRequest productRequest){

        log.info("hit");
        log.info("{}",productRequest.getCode());


        productService.createNewProduct(productRequest);




        return null;

    }

}
