package yongjun.sideproject.ui

import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class UiModule {
    @Factory
    fun provideCircuit(
        presenterFactories: List<Presenter.Factory>,
        uiFactories: List<Ui.Factory>,
    ): Circuit =
        Circuit.Builder()
            .addPresenterFactories(presenterFactories)
            .addUiFactories(uiFactories)
            .build()
}
