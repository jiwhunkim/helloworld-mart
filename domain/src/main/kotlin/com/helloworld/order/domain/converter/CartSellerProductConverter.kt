package com.helloworld.order.domain.converter

import com.helloworld.order.domain.CartSellerProduct
import com.helloworld.seller.domain.SellerProduct
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CartSellerProductConverter : Converter<SellerProduct, CartSellerProduct> {
    override fun convert(sellerProduct: SellerProduct): CartSellerProduct {
        return CartSellerProduct(
            sellerProductId = sellerProduct.id,
            sellerProductName = sellerProduct.name,
            salesAmount = sellerProduct.salesAmount,
            discountAmount = sellerProduct.discountAmount,
            amount = sellerProduct.amount
        )
    }
}
