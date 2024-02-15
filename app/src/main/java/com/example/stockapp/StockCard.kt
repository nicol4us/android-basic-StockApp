package com.example.stockapp

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockapp.model.Stock
import com.example.stockapp.ui.theme.StockAppTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.example.stockapp.data.StockRepository


// To represent List of Stock
@Composable
fun StockList(
    stockList : List<Stock>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       itemsIndexed(stockList) {
           index, item ->  StockCard(
           stock = item,
           indexNumber = index,
           modifier = Modifier
               .padding(
                   start = dimensionResource(id = R.dimen.padding_medium),
                   top = dimensionResource(id = R.dimen.padding_small),
                   bottom = dimensionResource(id = R.dimen.padding_small),
                   end = dimensionResource(id = R.dimen.padding_medium))
               )
       }
    }
}

// To represent information of a Stock display from Logo, name of Stock, name of Company and financial report
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockCard(
    stock: Stock,
    indexNumber: Int,
    modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        onClick ={expanded = !expanded},
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            StockItem(
                stock = stock,
                indexNumber = indexNumber,
                modifier = Modifier.height(dimensionResource(id = R.dimen.card_size))
            )
            if (expanded) {
                StockAllReport(
                    stock = stock
                    )
            }
        }
    }
}

// To represent company logo and Stock information
@Composable
fun StockItem(
    stock : Stock,
    indexNumber: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            StockLogo(stockLogo = stock.logoStock
                )
            StockInformation(
                indexNumber = indexNumber,
                nameStock = stock.nameStock, nameCompany = stock.nameCompany,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.padding_small)))
        }
       
}

// To represent name of Stock and company name
@Composable
fun StockInformation(
    indexNumber: Int,
    nameStock : Int,
    nameCompany: Int,
    modifier: Modifier = Modifier
) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center

        ) {
            StockName(indexNumber = indexNumber, nameStock = nameStock)
            Text(text = stringResource(id = nameCompany),
                style = MaterialTheme.typography.labelSmall)
        }
    }

// To represent the number of stock with name of stock according from the list
@Composable
fun StockName(
    indexNumber: Int,
    nameStock: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = "${stringResource(id = R.string.stock)} ${indexNumber +1} : ",
            style = MaterialTheme.typography.bodyLarge,
            )
        Text(text = stringResource(id = nameStock),
            style = MaterialTheme.typography.displayMedium,
            textDecoration = TextDecoration.Underline)
    }
}

// To represent image of company logo
@Composable
fun StockLogo(    
    stockLogo : Int,
    modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = stockLogo),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size_medium))
                .padding(dimensionResource(id = R.dimen.padding_small)),
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )
    }
        }

// To represent all of financial statement report
@Composable
fun StockAllReport(
    stock: Stock,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Divider()
        StockReportScreen(
            reportName = R.string.price_chart, 
            showInformation = stock.priceChartStock)
        Divider()
        StockReportScreen(
            reportName = R.string.income_statement, 
            showInformation = stock.incomeStatementStock)
        Divider()
        StockReportScreen(
            reportName = R.string.balance_sheet, 
            showInformation = stock.balanceSheetStock)
        Divider()
        StockReportScreen(
            reportName = R.string.cash_flow, 
            showInformation = stock.cashFLowStock)
    }
}

// To represent the report of financial statement
@Composable
fun StockReportScreen(
    reportName: Int,
    showInformation : Int,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
        StockReportTitle(
            reportName = reportName, expanded = expanded, onClick = {expanded = !expanded})
        if (expanded) {
            Image(painter = painterResource(id = showInformation), contentDescription = null)
        }
    }
}

// To represent the title of each financial statement report
@Composable
fun StockReportTitle(
    reportName : Int,
    expanded   : Boolean,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.padding_small), 
            end = dimensionResource(id = R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = reportName),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            modifier = Modifier.clickable(onClick = onClick))
    }
}

@Preview
@Composable
fun StockCardPreview() {
    StockAppTheme {
        StockList(stockList = StockRepository.stockListData)
    }
}