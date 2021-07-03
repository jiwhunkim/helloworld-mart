package com.helloworld.domain.product

import com.helloworld.product.domain.Product
import com.helloworld.product.domain.ProductOption
import com.helloworld.product.domain.ProductOptionRepository
import com.helloworld.product.domain.ProductRepository
import com.helloworld.seller.domain.Seller
import com.helloworld.seller.domain.SellerProduct
import com.helloworld.seller.domain.SellerProductRepository
import com.helloworld.seller.domain.SellerRepository
import com.helloworld.sku.domain.Sku
import com.helloworld.sku.domain.SkuRepository

class DomainProductCommandService(
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository,
    private val skuRepository: SkuRepository,
    private val sellerRepository: SellerRepository,
    private val sellerProductRepository: SellerProductRepository
) {
    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun save(sku: Sku): Sku {
        return skuRepository.save(sku)
    }

    fun save(seller: Seller): Seller {
        return sellerRepository.save(seller)
    }

    fun save(sellerProduct: SellerProduct): SellerProduct {
        return sellerProductRepository.save(sellerProduct)
    }

    fun save(productOption: ProductOption): ProductOption {
        return productOptionRepository.save(productOption)
    }
}
