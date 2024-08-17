package yongjun.sideproject.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import yongjun.sideproject.data.DataModule
import yongjun.sideproject.domain.DomainModule
import yongjun.sideproject.ui.UiModule

class FootballPaperApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FootballPaperApplication)
            modules(
                AppModule().module +
                        DataModule().module +
                        DomainModule().module +
                        UiModule().module,
            )
        }
    }
}
