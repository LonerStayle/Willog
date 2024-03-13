package kr.loner.willog.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.loner.willog.designsystem.R
import kr.loner.willog.designsystem.theme.WillogTheme

@Composable
fun SearchBar(
    onTextChange: (String) -> Unit
) {
    var textValue by remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(8.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier.padding(horizontal = 8.dp),
                textStyle = WillogTheme.typography.input,
                value = textValue,
                onValueChange = { text ->
                    textValue = text
                    onTextChange(text.text)
                },
                decorationBox = { innerTextField ->
                    if (textValue.text.isEmpty()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                modifier = Modifier.size(16.dp),
                                tint = Color.Gray,
                                contentDescription = "Search",
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = stringResource(id = R.string.search_bar_hint),
                                color = Color.Gray
                            )
                        }
                    }
                    innerTextField()
                })
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        SearchBar {

        }
    }

}