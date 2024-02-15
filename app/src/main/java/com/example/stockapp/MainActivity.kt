package com.example.stockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockapp.data.StockRepository
import com.example.stockapp.ui.theme.StockAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StockApp()
                }
            }
        }
    }
}


// To represent the main scheme of app
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockApp() {
    Scaffold(
        topBar = {TopAppStockBar(modifier = Modifier.padding(bottom = 8.dp))}
    ) {
        StockList(
        stockList = StockRepository.stockListData,
        modifier = Modifier.padding(it)
            )
    }

}

// To represent the title of the apps
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppStockBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
                HeadTitleApp(
                    mainTitle = R.string.app_name, 
                    smallTitle = R.string.stock, 
                    )
             },
        navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Navigation Icon" )
        }},
        actions = { ProfileImage(
            imageProfile = R.drawable.user_profil_nico,
            modifier = Modifier.size(32.dp))},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.tertiaryContainer),
        modifier = modifier)
}

// To represent the picture of user
@Composable
fun ProfileImage(
    imageProfile : Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = CircleShape,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageProfile),
            contentDescription = null)
    }
}

// To represent the title of the app
@Composable
fun HeadTitleApp(
    mainTitle : Int,
    smallTitle : Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = mainTitle),
            style = MaterialTheme.typography.displayLarge)
        Text(
            text = stringResource(id = smallTitle),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
            )
    }
}

// To represent preview of StockApp function
@Preview(showBackground = true)
@Composable
fun StockAppPreview() {
    StockAppTheme {
        StockApp()
    }
}