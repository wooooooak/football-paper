package yongjun.sideproject.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yongjun.sideproject.network.AppHttpException
import yongjun.sideproject.network.BadConnectionException

@Composable
fun CommonErrorScreen(
    exception: Exception,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.TopStart)) {
            Icon(Icons.TwoTone.Close, contentDescription = "back")
        }
        Column(
            modifier = Modifier.padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val errorMessage = when (exception) {
                is BadConnectionException -> "인터넷 연결이 좋지 않습니다. 확인 후 재시도 해주세요."
                is AppHttpException -> {
                    exception.errorResponsePayload?.message ?: "알 수 없는 에러가 발생했습니다😭"
                }

                else -> "알 수 없는 에러가 발생했습니다😭"
            }
            Text(
                text = errorMessage,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = onRetryClick,
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp),
            ) {
                Text(text = "재시도 하기")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MaterialTheme {
        CommonErrorScreen(
            BadConnectionException(Throwable()),
        )
    }
}
