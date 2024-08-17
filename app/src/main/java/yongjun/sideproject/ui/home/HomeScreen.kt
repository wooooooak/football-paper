package yongjun.sideproject.ui.home

import android.os.Parcelable
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize


@Parcelize
data object HomeScreen : Screen, Parcelable {
    data class State(
        val standings: List<String>,
    ) : CircuitUiState
}
