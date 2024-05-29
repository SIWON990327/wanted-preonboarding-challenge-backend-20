package com.example.wantedmarketapi.controller;

import com.example.wantedmarketapi.annotation.auth.AuthMember;
import com.example.wantedmarketapi.common.BaseResponse;
import com.example.wantedmarketapi.converter.ProductConverter;
import com.example.wantedmarketapi.domain.member.Member;
import com.example.wantedmarketapi.dto.request.ProductRequestDto.*;
import com.example.wantedmarketapi.dto.response.ProductResponseDto.*;
import com.example.wantedmarketapi.exception.GlobalErrorCode;
import com.example.wantedmarketapi.service.ProductCommandService;
import com.example.wantedmarketapi.service.ProductQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "📋 Product", description = "제품 관련 API")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    @Operation(summary = "제품 등록 API", description = "제품을 등록합니다")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "성공")})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CreateProductResponse> createProduct(@Parameter(hidden = true) @AuthMember Member member, @RequestBody CreateProductRequest request){
        return BaseResponse.onSuccess(GlobalErrorCode.CREATED, ProductConverter.toCreateProductResponse(productCommandService.createProduct(member, request)));
    }

    @Operation(summary = "제품 목록 조회 API", description = "제품 목록을 조회합니다")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "성공")})
    @GetMapping("/")
    public BaseResponse<List<GetProductResponse>> getProductList(){
        return BaseResponse.onSuccess(productQueryService.getProductList());
    }

    @Operation(summary = "제품 상세 조회 API", description = "제품의 상세정보를 조회합니다")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "성공")})
    @GetMapping("/{productId}")
    public BaseResponse<GetProductDetailsResponse> getProductDetails(@PathVariable(name = "productId") Long productId){
        return BaseResponse.onSuccess(productQueryService.getProductDetails(productId));
    }

}
