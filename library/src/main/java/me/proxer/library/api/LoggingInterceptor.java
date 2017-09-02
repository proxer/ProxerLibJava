package me.proxer.library.api;

import lombok.AllArgsConstructor;
import me.proxer.library.api.ProxerApi.Builder.LoggingStrategy;
import me.proxer.library.util.ProxerUrls;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Ruben Gees.
 */
@AllArgsConstructor
class LoggingInterceptor implements Interceptor {

    private final LoggingStrategy loggingStrategy;

    @Override
    public Response intercept(final Chain chain) throws IOException {
        if (loggingStrategy == LoggingStrategy.ALL || ProxerUrls.hasProxerHost(chain.request().url())) {
            final Request requestCopy = chain.request().newBuilder().build();
            final String headerMessage = buildHeaderMessage(requestCopy);
            final String bodyMessage = buildBodyMessage(requestCopy);

            final String message = "Requesting " + requestCopy.url() + " with method " + requestCopy.method() +
                    (bodyMessage.isEmpty() ? " and " : ", ") + headerMessage +
                    (!headerMessage.contains("\n") && bodyMessage.isEmpty() ? "." : bodyMessage);

            Logger.getLogger("ProxerAndroid").info(message);
        }

        return chain.proceed(chain.request());
    }

    private String buildHeaderMessage(final Request requestCopy) {
        final String headerContent = requestCopy.headers().toString().trim();

        if (headerContent.isEmpty()) {
            return "no headers";
        } else {
            return "these headers:\n" + headerContent;
        }
    }

    private String buildBodyMessage(final Request requestCopy) throws IOException {
        final RequestBody body = requestCopy.body();
        final Buffer bodyBuffer = new Buffer();
        final String bodyContent;

        if (body == null) {
            bodyContent = null;
        } else {
            body.writeTo(bodyBuffer);

            bodyContent = bodyBuffer.readUtf8();
        }

        if (bodyContent == null) {
            return "";
        } else if (bodyContent.trim().isEmpty()) {
            return "\nand a blank body.";
        } else {
            return "\nand this body:\n" + bodyContent;
        }
    }
}
