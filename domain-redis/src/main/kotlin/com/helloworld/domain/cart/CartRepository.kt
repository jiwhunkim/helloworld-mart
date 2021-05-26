package com.helloworld.domain.cart

import org.springframework.data.repository.CrudRepository

interface CartRepository : CrudRepository<Cart, String> {

}