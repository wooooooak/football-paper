package yongjun.sideproject.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import yongjun.sideproject.koin.SharedKoinQualifier

@Module
@ComponentScan
class AppModule {
    @Single
    @Named(SharedKoinQualifier.APP_SCOPE)
    fun provideAppScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main)
}