package com.study.shop.catalogservice.adapter.web

import com.study.shop.catalogservice.adapter.web.dto.ProductResponse
import com.study.shop.catalogservice.core.usecase.BrowseCatalogUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class CatalogController(
    private val catalogBrowseUseCase: BrowseCatalogUseCase
) {

    @GetMapping
    fun list(): List<ProductResponse> {
        return catalogBrowseUseCase.list().map { ProductResponse.from(it) }
    }

    @GetMapping("/{sku}")
    fun findBySku(@PathVariable sku: String) : ProductResponse{
        return ProductResponse.from(catalogBrowseUseCase.findBySku(sku))
    }

}