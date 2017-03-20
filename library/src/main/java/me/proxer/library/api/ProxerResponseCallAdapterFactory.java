package me.proxer.library.api;

import com.squareup.moshi.Types;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Ruben Gees
 */
final class ProxerResponseCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(final Type returnType, final Annotation[] annotations, final Retrofit retrofit) {
        if (getRawType(returnType) != ProxerCall.class) {
            return null;
        }

        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("ProxerCall return type must be parameterized as "
                    + "ProxerCall<Foo> or ProxerCall<? extends Foo>");
        }

        return new ProxerResponseCallAdapter<>(getParameterUpperBound(0, (ParameterizedType) returnType));
    }

    private static final class ProxerResponseCallAdapter<R> implements CallAdapter<ProxerResponse<R>, ProxerCall<R>> {

        private final Type responseType;

        ProxerResponseCallAdapter(final Type responseType) {
            this.responseType = Types.newParameterizedType(ProxerResponse.class, responseType);
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public ProxerCall<R> adapt(final Call<ProxerResponse<R>> call) {
            return new ProxerCall<>(call);
        }
    }
}
