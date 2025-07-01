package com.tc.ufocodingchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.ufocodingchallenge.data.model.UfoSighting
import com.tc.ufocodingchallenge.ui.theme.UfoCodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: UfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UfoCodingChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF8F8F8)
                ) {
                    UfoListScreen(
                        viewModel = viewModel,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UfoListScreen(viewModel: UfoViewModel, modifier: Modifier = Modifier) {
    val sightings by viewModel.sightings.collectAsState()
    var selectedId by remember { mutableStateOf<UUID?>(null) }


    Scaffold(
        modifier = modifier,
        containerColor = Color(0xFFF8F8F8),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "UFO Sightings",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = { viewModel.addRandomSighting() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add UFO Sighting",
                            tint = Color(0xFF08A462)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8F8F8)),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            items(sightings, key = { it.id }) { sighting ->
                UfoSightingItem(
                    sighting = sighting,
                    isSelected = selectedId == sighting.id,
                    onItemClick = { selectedId = if (selectedId == sighting.id) null else sighting.id },
                    onRemoveClick = {
                        viewModel.removeSighting(sighting.id)
                        selectedId = null
                    }
                )
            }
        }
    }
}

@Composable
fun UfoSightingItem(
    sighting: UfoSighting,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    val parsedDate = LocalDateTime.parse(sighting.date)
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy @ h:mm a", Locale.getDefault())
    val formattedDate = parsedDate.format(formatter)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp, vertical = 10.dp),
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
            ) {

                Image(
                    painter = painterResource(id = sighting.iconResId),
                    contentDescription = sighting.type,
                    modifier = Modifier.size(74.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = formattedDate,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(2.dp))


                Text(
                    text = "${sighting.speed} knots • ${sighting.type}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )


                if (isSelected) {
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(
                        onClick = onRemoveClick,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Red
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Remove",
                            fontSize = 14.sp
                        )
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
    }
    Spacer(modifier = Modifier.height(0.dp))
}