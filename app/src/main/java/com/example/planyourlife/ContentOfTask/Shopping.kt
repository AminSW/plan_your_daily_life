package com.example.planyourlife.ContentOfTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planyourlife.Tasks.TaskOfPlan

@Composable
fun ShoppingTableScreen() {
    // Checkbox durumlarını yönetmek için bir liste
    val shoppingList = remember {
        mutableStateListOf(
            ShoppingItem("product 1", 2, false),
            ShoppingItem("product 2", 2, false),
            ShoppingItem("product 3", 2, false),
            ShoppingItem("product 4", 2, false),
        )
    }
    val shopping= Shopping(shoppingList = shoppingList, id = 1, name = "shopping")
    shopping.ShowTaskContent()
}

class Shopping(
    private val shoppingList: MutableList<ShoppingItem>, id: Int, name: String
): TaskOfPlan(id, name)
{

    @Composable
    override fun ShowTaskContent()
    {
        ShoppingListScreen(shoppingList = shoppingList)
    }

}

data class ShoppingItem(val name: String, val number: Int, val isCompleted: Boolean)

@Composable
fun ShoppingListScreen(shoppingList: MutableList<ShoppingItem>)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tablo Başlığı
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Exercise list",
                modifier = Modifier.weight(2f),
                style = MaterialTheme.typography.bodySmall
            )

        }
        // Egzersiz Satırları
        shoppingList.forEachIndexed { index, ShoppingItem ->
            ShoppingListRow(
                name = ShoppingItem.name,
                number = ShoppingItem.number,
                isChecked = ShoppingItem.isCompleted,
                onCheckedChange = { isChecked ->
                    // Checkbox durumunu güncelle
                    shoppingList[index] = ShoppingItem.copy(isCompleted = isChecked)
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
                color = Color(0xFFBBDEFB), // Soft mavi renk
                shape = RoundedCornerShape(16.dp) // Yuvarlatılmış köşeler
            )
            .shadow(
                elevation = 0.dp, // Hafif gölge
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp), // İçerik için padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            modifier = Modifier.padding(end = 16.dp)
        )

        // Egzersiz Adı
        Text(
            text = name,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
        )

        // Sayısı
        Text(
            text = "x $number",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
        )
    }
}



