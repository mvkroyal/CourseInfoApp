package com.example.coursesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CourseInfoData(
    @StringRes val courseNameStringResId : Int,
    val numberOfCoursesStringResId : Int,
    @DrawableRes val imageResId : Int,

)
