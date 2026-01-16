package com.example.taller.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taller.entity.Coche

// Ajuste de anchos para la tabla de coches
private val NAME_WIDTH = 110.dp
private val PRICE_WIDTH = 80.dp
private val QTY_WIDTH = 60.dp
private val ACTIONS_WIDTH = 96.dp
private val ROW_HORIZONTAL_PADDING = 8.dp
private val ROW_VERTICAL_PADDING = 12.dp

@Composable
fun CocheItemHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = ROW_HORIZONTAL_PADDING,
                vertical = ROW_VERTICAL_PADDING
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Modelo",
            modifier = Modifier.width(NAME_WIDTH),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start
        )
        Text(
            text = "Precio",
            modifier = Modifier.width(PRICE_WIDTH),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Cant.",
            modifier = Modifier.width(QTY_WIDTH),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.width(ACTIONS_WIDTH),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Acciones",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun CocheItem(
    coche: Coche,
    onEditClick: (Coche) -> Unit,
    onDeleteClick: (Coche) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ROW_HORIZONTAL_PADDING, vertical = ROW_VERTICAL_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = coche.name,
                modifier = Modifier.width(NAME_WIDTH),
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineBreak = LineBreak.Paragraph,
                    hyphens = Hyphens.Auto
                ),
                softWrap = true,
                textAlign = TextAlign.Start
            )

            Text(
                text = "${coche.price}€",
                modifier = Modifier.width(PRICE_WIDTH),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Text(
                text = "${coche.quantity}",
                modifier = Modifier.width(QTY_WIDTH),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.width(ACTIONS_WIDTH),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onEditClick(coche) }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Editar coche"
                    )
                }
                IconButton(onClick = { onDeleteClick(coche) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar coche",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}