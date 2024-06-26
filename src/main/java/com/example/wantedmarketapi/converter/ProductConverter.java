package com.example.wantedmarketapi.converter;

import com.example.wantedmarketapi.domain.Product;
import com.example.wantedmarketapi.domain.enums.ProductStatus;
import com.example.wantedmarketapi.domain.member.Member;
import com.example.wantedmarketapi.dto.request.ProductRequestDto.*;
import com.example.wantedmarketapi.dto.response.ProductResponseDto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter {

    public static Product toProduct(Member member, CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .productStatus(ProductStatus.SALE)
                .seller(member)
                .build();
    }

    public static CreateProductResponse toCreateProductResponse(Product product) {
        return CreateProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }

    public static GetProductResponse toGetProductResponse(Product product) {
        return GetProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .reservationStatus(product.getReservationStatus())
                .build();
    }

    public static List<GetProductResponse> toGetProductListResponseList(List<Product> productList) {
        return productList.stream()
                .map(product -> ProductConverter.toGetProductResponse(product))
                .toList();
    }

    public static GetProductDetailsResponse toGetProductDetailsResponse(Product product) {
        return GetProductDetailsResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .reservationStatus(product.getReservationStatus())
                .tradeList(product.getTradeList())
                .build();
    }

    public static SetProductStatusResponse toSetProductStatusResponse(Product product) {
        return SetProductStatusResponse.builder()
                .name(product.getName())
                .productStatus(product.getProductStatus())
                .build();
    }

}
