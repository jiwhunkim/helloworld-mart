package com.helloworld.domain.product

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
