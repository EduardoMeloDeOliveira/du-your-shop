package com.study.shop.catalogservice.adapter.web

import com.study.shop.catalogservice.adapter.web.dto.CreateProductRequest
import com.study.shop.catalogservice.adapter.web.dto.ProductResponse
import com.study.shop.catalogservice.adapter.web.dto.UpdateProductRequest
import com.study.shop.catalogservice.core.usecase.ManageCatalogUseCase
import com.study.shop.catalogservice.core.usecase.NewProduct
import com.study.shop.catalogservice.core.usecase.ProductUpdate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/products")
class CatalogAdminController (
    private val adminCatalogUseCase: ManageCatalogUseCase
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create (@RequestBody request: CreateProductRequest) : ProductResponse {

        return ProductResponse.from(
            adminCatalogUseCase.create(NewProduct(
                request.sku,
                request.name,
                request.price,
                request.stock
            ))
        )
    }

    @PutMapping("/{sku}")
    fun update(@PathVariable sku: String, @RequestBody request: UpdateProductRequest) : ProductResponse {

        return ProductResponse.from(
            adminCatalogUseCase.update(sku, ProductUpdate(
                request.name,
                request.price,
                request.stock
            ))
        )
    }

    @GetMapping
    fun listAll() : List<ProductResponse> {
        return adminCatalogUseCase.findAll().map { ProductResponse.from(it) }
    }
}