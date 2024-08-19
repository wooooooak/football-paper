package yongjun.sideproject.ui.match

import android.os.Parcelable
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize
import yongjun.sideproject.domain.model.MatchResponse
import yongjun.sideproject.ui.utils.Async
import yongjun.sideproject.ui.utils.Uninitialized

@Parcelize
data class MatchScreen(
    val competitionId: Int,
    val matchDay: Int,
) : Screen, Parcelable {
    data class State(
        val matchDay: Int,
        val getMatchResponsesAsync: Async<List<MatchResponse>> = Uninitialized,
        val eventSink: (Event) -> Unit = {},
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object Pop : Event
    }
}
