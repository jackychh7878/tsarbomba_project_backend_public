package com.fsse2305.fsse2305_project_backend.api;

import com.fsse2305.fsse2305_project_backend.config.EnvConfig;
import com.fsse2305.fsse2305_project_backend.data.product.domainObject.CreateProductData;
import com.fsse2305.fsse2305_project_backend.data.product.domainObject.ProductDetailData;
import com.fsse2305.fsse2305_project_backend.data.product.dto.request.CreateProductRequestDto;
import com.fsse2305.fsse2305_project_backend.data.product.dto.response.FullProductListDetailResponseDto;
import com.fsse2305.fsse2305_project_backend.data.product.dto.response.ProductDetailResponseDto;
import com.fsse2305.fsse2305_project_backend.service.ProductService;
import com.fsse2305.fsse2305_project_backend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RequestMapping("/public/product")
@RestController
public class ProductApi {
    private final ProductService productService;

    @Autowired
    public ProductApi(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDetailResponseDto createProduct (@RequestBody CreateProductRequestDto createProductRequestDto){
        return new ProductDetailResponseDto(productService.createProduct(new CreateProductData(createProductRequestDto)));
    }

    @GetMapping
    public List<FullProductListDetailResponseDto> getAllProduct(){
        List<FullProductListDetailResponseDto> productDetailResponseDtoList = new ArrayList<>();
        for (ProductDetailData data: productService.getAllProduct()){
            productDetailResponseDtoList.add(new FullProductListDetailResponseDto(data));
        }
        return productDetailResponseDtoList;
    }

    @GetMapping("/{id}")
    public ProductDetailResponseDto getProductById(@PathVariable Integer id){
        return new ProductDetailResponseDto(productService.findProductById(id));
    }

    //Search bar function
    @GetMapping("/search")
    public List<FullProductListDetailResponseDto> searchProduct(@RequestParam String searchTerm){
        List<FullProductListDetailResponseDto> productListDetailResponseDtoList = new ArrayList<>();
        for (ProductDetailData data: productService.searchProductByKeywords(searchTerm)){
            productListDetailResponseDtoList.add(new FullProductListDetailResponseDto(data));
        }
        return productListDetailResponseDtoList;
    }
}
