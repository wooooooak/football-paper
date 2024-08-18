package yongjun.sideproject.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.presenter.presenterOf
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

inline fun <reified S : Screen, UiState : CircuitUiState> presenterFactory(
    crossinline body: @Composable (screen: S, context: CircuitContext, navigator: Navigator) -> UiState,
): Presenter.Factory =
    Presenter.Factory { screen, navigator, context ->
        if (screen is S) {
            presenterOf { body(screen, context, navigator) }
        } else {
            null
        }
    }

inline fun <reified S : Screen, UiState : CircuitUiState> uiFactory(
    crossinline body: @Composable (context: CircuitContext, state: UiState, modifier: Modifier) -> Unit,
): Ui.Factory =
    Ui.Factory { screen, context ->
        if (screen is S) {
            ui<UiState> { state, modifier -> body(context, state, modifier) }
        } else {
            null
        }
    }
