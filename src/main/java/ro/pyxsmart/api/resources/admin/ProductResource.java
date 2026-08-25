package ro.pyxsmart.api.resources.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ProductResource {

    @GetMapping("/test")
    public String getTest(){
        return "It is working";
    }


}
