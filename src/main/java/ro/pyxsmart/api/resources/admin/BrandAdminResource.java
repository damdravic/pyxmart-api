package ro.pyxsmart.api.resources.admin;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.models.ApiResponse;
import ro.pyxsmart.api.models.Brand;
import ro.pyxsmart.api.services.BrandService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/brand")
public class BrandAdminResource {


    private final BrandService brandService;


    @PostMapping(value = "/saveLogo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<@NonNull ApiResponse> saveFile (@RequestParam MultipartFile file)
            throws IOException {

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Image saved successfully !!!")
                        .data(Map.of("logoUrl",brandService.saveLogo(file)))
                        .build()
        );
    }

    @PostMapping("/saveNewBrand")
    public ResponseEntity<@NonNull ApiResponse> saveNewBrand(@RequestBody Brand brand){

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("Brand created successfully !!!")
                        .status(HttpStatus.OK.value())
                        .data(Map.of("brand",brandService.createBrand(brand)))
                        .build()
        );
    }


    @GetMapping("/getAllBrands")
    public ResponseEntity<@NonNull ApiResponse> getAllBrands(){

        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("Brands ")
                        .status(HttpStatus.OK.value())
                        .data(Map.of("brands", brandService.getAllBrands()))
                        .build()
        );

    }


    @PostMapping("/editBrand")
    public ResponseEntity<@NonNull ApiResponse> editBrand(@RequestBody Brand brand){
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .message("Brand Edited")
                        .status(HttpStatus.OK.value())
                        .data(Map.of("brand",brandService.editBrand(brand)))
                        .build()
        );
    }




}
