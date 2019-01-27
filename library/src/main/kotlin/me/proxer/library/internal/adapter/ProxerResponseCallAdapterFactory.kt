package me.proxer.library.internal.adapter

import com.squareup.moshi.Types
import me.proxer.library.ProxerCall
import me.proxer.library.internal.ProxerResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author Ruben Gees
 */
internal class ProxerResponseCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != ProxerCall::class.java) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalArgumentException(
                "ProxerCall return type must be parameterized as ProxerCall<Foo> or ProxerCall<? extends Foo>"
            )
        }

        return ProxerResponseCallAdapter<Any>(
            getParameterUpperBound(0, returnType)
        )
    }

    private class ProxerResponseCallAdapter<R> internal constructor(
        responseType: Type
    ) : CallAdapter<ProxerResponse<R>, ProxerCall<R>> {

        private val responseType = Types.newParameterizedType(ProxerResponse::class.java, responseType)

        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: Call<ProxerResponse<R>>): ProxerCall<R> {
            return ProxerCall(call)
        }
    }
}
