package com.example.planyourlife.ViewOfTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckBoxItem(
    name: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
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
            text = text,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
        )

    }
}

@Composable
fun CheckBoxItemWithNumber(
    name: String,
    number: Int,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
)
{
    CheckBoxItem(
        name = name,
        isChecked = isChecked,
        onCheckedChange = onCheckedChange,
        text = "x $number"
    )
}

@Composable
fun CheckBoxItemWithTime(
    name: String,
    number: Int,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
)
{
    CheckBoxItem(
        name = name,
        isChecked = isChecked,
        onCheckedChange = onCheckedChange,
        text = "$number min"
    )
}

