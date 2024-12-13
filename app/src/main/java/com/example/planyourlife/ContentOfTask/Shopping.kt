package com.example.planyourlife.ContentOfTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShoppingTableScreen() {
    val shoppingList = remember {
        mutableStateListOf(
            ShoppingItem("product 1", 2, false),
            ShoppingItem("product 2", 2, false),
            ShoppingItem("product 3", 2, false),
            ShoppingItem("product 4", 2, false),
        )
    }
    val shopping = Shopping(shoppingList = shoppingList, id = 1, name = "shopping")
    shopping.ShowTaskContent()
}

class Shopping(
    private val shoppingList: MutableList<ShoppingItem>, id: Int, name: String
) {
    @Composable
    fun ShowTaskContent() {
        ShoppingListScreen(shoppingList = shoppingList)
    }
}

class ShoppingItem(val name: String, val number: Int, isCompleted: Boolean) {
    var isCompleted by mutableStateOf(isCompleted)
        private set

    fun updateCompletionStatus() {
        isCompleted = !isCompleted
    }
}

@Composable
fun ShoppingListScreen(shoppingList: MutableList<ShoppingItem>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Shopping list",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.bodySmall
            )
        }

        shoppingList.forEach { shoppingItem ->
            ShoppingListRow(
                name = shoppingItem.name,
                number = shoppingItem.number,
                isChecked = shoppingItem.isCompleted,
                onCheckedChange = {
                    shoppingItem.updateCompletionStatus()
                }
            )
        }
    }
}

@Composable
fun ShoppingListRow(
    name: String,
    number: Int,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = Color(0xFFBBDEFB),
                shape = RoundedCornerShape(16.dp)
            )
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            modifier = Modifier.padding(end = 16.dp)
        )

        Text(
            text = name,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
        )

        Text(
            text = "x $number",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
        )
    }
}
