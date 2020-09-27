package com.task.data.dto.login

/**
 * Created by AhmedEltaher
 */
data class LoginResponse(val id: String, val firstName: String, val lastName: String,
                         val streetName: String, val buildingNumber: String,
                         val postalCode: String, val state: String,
                         val country: String, val email: String)
