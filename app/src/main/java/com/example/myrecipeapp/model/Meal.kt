package com.example.myrecipeapp.model

import kotlinx.serialization.Serializable


@Serializable
data class MealResponse(
    val meals: List<Meal>
)

@Serializable
data class Meal(
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String,
)

@Serializable
data class MealDetailsResponse(
    val meals: List<MealDetail>
)

@Serializable
data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String?,
    val strYoutube: String?,
    val strSource: String?,
    val strImageSource: String?,
    val strCreativeCommonsConfirmed: String?,
    val dateModified: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?
) {
    /*
    * get() makes ingredients dynamic
    * Instead of storing a static list, it recalculates the ingredients list every time you access ingredients.
    */
    val ingredients: List<Pair<String, String>>
        get() = (1..20)
            .mapNotNull { i ->
                // Uses Java reflection to access the fields dynamically at runtime
                // Java reflection is used because it allows dynamic field access at runtime,
                // unlike direct property access (which requires known field names) or Kotlin reflection (::class.members),
                // which is less efficient for this structured API response.
                val ingredient =
                    this::class.java.getDeclaredField("strIngredient$i").get(this) as? String
                val measure = this::class.java.getDeclaredField("strMeasure$i").get(this) as? String

                if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) measure to ingredient else null
            }
    val mealTags: List<String>
        get() =
            this.strTags?.split(',')?.toList() ?: emptyList()

}