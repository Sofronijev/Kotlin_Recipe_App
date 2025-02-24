package com.example.myrecipeapp.network


import com.example.myrecipeapp.model.CategoriesResponse
import com.example.myrecipeapp.model.MealCategory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class KtorClient {
    private val client = HttpClient(OkHttp) {
        expectSuccess = true
        defaultRequest { url("https://www.themealdb.com/api/json/v1/1/") }

        install(Logging) {
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

    }

    suspend fun getMealCategories(): List<MealCategory> {
        return client.get("categories.php").body<CategoriesResponse>().categories
    }
}