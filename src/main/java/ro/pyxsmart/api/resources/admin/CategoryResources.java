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
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryResources {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<@NonNull ApiResponse> addCategory(@RequestBody Category category){



        return ResponseEntity.ok().body(
                 ApiResponse.builder()
                         .status(HttpStatus.OK.value())
                         .message("Category created")
                         .data(Map.of("category",categoryService.createCategory(category)))
                         .build()
        );
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<@NonNull ApiResponse> getAllCategories (){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("All categories")
                        .status(HttpStatus.OK.value())
                        .data(Map.of("categories", categoryService.getAllCategories()))
                        .build()
        );
    }


    @GetMapping("/getActiveCategories")
    public ResponseEntity<@NonNull ApiResponse> getActiveCategories (){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("All categories")
                        .status(HttpStatus.OK.value())
                        .data(Map.of("categories", categoryService.getActiveCategories()))
                        .build()
        );
    }




    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<@NonNull ApiResponse> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("All categories")
                        .status(HttpStatus.OK.value())
                        .data(Map.of("category",categoryService.getCategoryById(id)))
                        .build()
        );
    }

    @PostMapping("/editCategory")
    public ResponseEntity<@NonNull ApiResponse> editCategory(@RequestBody Category category){



        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Category edited successfully")
                        .data(Map.of("category",categoryService.editCategory(category)))
                        .build()
        );
    }


}
