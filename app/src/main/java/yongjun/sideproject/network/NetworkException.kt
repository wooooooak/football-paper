package yongjun.sideproject.network

import okio.IOException
import yongjun.sideproject.ui.common.ErrorPayload

/* 200~299 가 아닌 Http 에러 */
data class AppHttpException(
    override val cause: Throwable?,
    val errorResponsePayload: ErrorPayload?,
) : Exception(cause)

class BadConnectionException(cause: Throwable) : IOException(cause)
