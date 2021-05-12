package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.CategoryResponse
import com.nistruct.sportstat.data.models.ui_models.Category

class CategoryResponseToCategoryMapper:DataMapper<CategoryResponse,Category> {
    override fun map(input: CategoryResponse): Category {
        return Category(input.id,input.name,input.numberOfConsultants)
    }
}