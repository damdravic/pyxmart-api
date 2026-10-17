package ro.pyxsmart.api.resources.admin;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.pyxsmart.api.models.ApiResponse;
import ro.pyxsmart.api.models.Category;
import ro.pyxsmart.api.services.CategoryService;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoryResources {

    private final CategoryService categoryService;

    @PostMapping("/addRepository")
    public ResponseEntity<@NonNull ApiResponse> addRepository(@RequestBody Category category){
        return ResponseEntity.ok().body(
                 ApiResponse.builder()
                         .status(HttpStatus.OK.value())
                         .message("Category created")
                         .data(Map.of("category",categoryService.createCategory(category)))
                         .build()
        );
    }




}
