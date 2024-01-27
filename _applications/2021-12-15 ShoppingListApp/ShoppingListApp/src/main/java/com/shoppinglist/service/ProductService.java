package com.shoppinglist.service;

import com.shoppinglist.mapper.MapStructMapper;
import com.shoppinglist.model.dto.ProductAddDto;
import com.shoppinglist.model.entity.ProductEntity;
import com.shoppinglist.model.enumerated.CategoryEnum;
import com.shoppinglist.model.view.ProductView;
import com.shoppinglist.model.view.ProductsByCategoryView;
import com.shoppinglist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final MapStructMapper mapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            MapStructMapper mapper,
            CategoryService categoryService
    ) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }


    public void addProduct(ProductAddDto productAddDto) {
        productAddDto.setNeededBefore(productAddDto.getNeededBefore().plusHours(2));

        ProductEntity productEntity = mapper.toProductEntity(productAddDto);

        productEntity.setCategory(categoryService.getByName(productAddDto.getCategory()));

        productRepository.saveAndFlush(productEntity);
    }


    public ProductsByCategoryView getProductsByCategory() {
        EnumMap<CategoryEnum, List<ProductView>> productsEnumMap = new EnumMap<>(CategoryEnum.class);

        Arrays.stream(CategoryEnum.values()).forEach(
                category -> productsEnumMap.put(category, getProductByCategory(category))
        );

        return new ProductsByCategoryView(productsEnumMap);
    }

    private List<ProductView> getProductByCategory(CategoryEnum category) {
        return productRepository.findByCategoryName(category)
                .stream()
                .map(mapper::toProductView)
                .sorted(Comparator.comparing(ProductView::getName))
                .toList();
    }


    public void buyProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
