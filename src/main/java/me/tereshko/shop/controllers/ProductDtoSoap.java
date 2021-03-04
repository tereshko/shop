package me.tereshko.shop.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.tereshko.shop.services.ProductDtoSoapService;
import me.tereshko.shop.soap.GetAllProductsDtoRequest;
import me.tereshko.shop.soap.GetAllProductsResponse;
import me.tereshko.shop.soap.GetProductDtoByIdRequest;
import me.tereshko.shop.soap.GetProductDtoByIdResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class ProductDtoSoap {
    private static final String NAMESPACE_URI = "http://www.tereshko.me/spring/ws/product";
    private final ProductDtoSoapService productDtoSoapService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductDtoByIdRequest")
    @ResponsePayload
    public GetProductDtoByIdResponse getProductDtoByIdResponse(@RequestPayload GetProductDtoByIdRequest request) {
        GetProductDtoByIdResponse response = new GetProductDtoByIdResponse();
        log.error(request.toString());
        response.setProductDto(productDtoSoapService.getProductDtoById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsDtoRequest")
    @ResponsePayload
    GetAllProductsResponse getAllProductsResponse(@RequestPayload GetAllProductsDtoRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productDtoSoapService.getAllProductsDto().forEach(response.getProductsDto()::add);
        log.info("catch in the getAllProductsResponse");
        return response;
    }
}
