package yongjun.sideproject.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import yongjun.sideproject.ui.home.HomeScreen

@Composable
fun App(
    circuit: Circuit = koinInject(),
) {
    KoinContext {
        MaterialTheme {
            CircuitCompositionLocals(circuit = circuit) {
                val backstack = rememberSaveableBackStack(initialScreens = listOf(HomeScreen))
                val navigator = rememberCircuitNavigator(backStack = backstack)
                NavigableCircuitContent(navigator = navigator, backStack = backstack)
            }
        }
    }
}
