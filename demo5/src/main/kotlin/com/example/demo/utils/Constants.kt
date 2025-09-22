package com.example.demo.utils

class Constants {

    companion object{
        private const val URI_API_BASE = "/api"
        private const val URI_API_VERSION = "/v1"
        private const val URL_BASE = URI_API_BASE + URI_API_VERSION
        //BASE API ENDPOINT PARA PERSONAS
        const val URL_BASE_PERSONAS = "$URL_BASE/personas"
    }
}