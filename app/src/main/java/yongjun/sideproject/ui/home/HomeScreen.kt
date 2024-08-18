package yongjun.sideproject.ui.home

import android.os.Parcelable
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize
import yongjun.sideproject.domain.model.StandingResponse
import yongjun.sideproject.ui.utils.Async


@Parcelize
data object HomeScreen : Screen, Parcelable {
    data class State(
        val getStandingResponsesAsync: Async<List<StandingResponse>>,
        val eventSink: (Event) -> Unit = {},
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object RetryClick : Event
    }

}
