package test.compose.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)



val ChatAnsGradientBush = Brush.verticalGradient(
    listOf(
        Color.Cyan,
        Color.Magenta,
    )
)

val ChatGradientBush = Brush.verticalGradient(
    listOf(
        Color.LightGray,
        Color.Gray
    )
)