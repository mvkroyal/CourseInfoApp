package com.example.coursesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.data.DataSource
import com.example.coursesapp.model.CourseInfoData
import com.example.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title ={
                                Text("Course Details")
                            }
                        )
                    }
                ) { innerpadding ->
                    Surface(modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerpadding)
                        .padding(8.dp)
                        .statusBarsPadding()
                    , color = MaterialTheme.colorScheme.background) {
                        CoursesTopicGrid(DataSource().loadCoursesList(), modifier = Modifier.padding(start = 8.dp,top=8.dp, end = 8.dp
                        ))
                    }
                }
            }
        }
    }
}

@Composable
fun CourseTopicCard(courseInfoData: CourseInfoData,modifier: Modifier = Modifier){
    Card(elevation = CardDefaults.cardElevation(4.dp)) {
        Row {
            Image(
                painter = painterResource(courseInfoData.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 68.dp, height = 68.dp)
                    .aspectRatio(1f),
            )
            Column {
                Text(
                    text = stringResource(courseInfoData.courseNameStringResId),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(
                            start = 16.dp
                        )
                    )
                    Text(
                        text = courseInfoData.numberOfCoursesStringResId.toString(),
                        modifier = Modifier.padding(
                            start = 8.dp
                        ),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesTopicGrid(courseInfoList: List<CourseInfoData>,modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(courseInfoList) { item ->
            CourseTopicCard(item)
        }
    }
}

    @Preview
    @Composable
    fun CoursesAppPreview() {
       CoursesTopicGrid(DataSource().loadCoursesList())
    }
