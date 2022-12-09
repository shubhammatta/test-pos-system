package com.example.anymind.posgateway.model.response

import com.fasterxml.jackson.annotation.JsonAlias

data class PayResponse(
    @JsonAlias("final_price")
    val finalPrice: String,
    val points: Int
)