package com.customer.api.infrastructure.configuration;

import static java.util.Objects.isNull;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@Log4j2
public class WebClientTimeMeterExchangeFilterFunction implements ExchangeFilterFunction {

    private static final String WEBCLIENT_REQUEST_START_TIME = WebClientTimeMeterExchangeFilterFunction.class
        .getName() + ".START_TIME";

    @Override
    public Mono<ClientResponse> filter(final ClientRequest clientRequest, final ExchangeFunction exchangeFunction) {
        return exchangeFunction.exchange(clientRequest)
            .doOnEach(signal -> {
                if (!signal.isOnComplete()) {
                    final long  startTime = signal.getContext().get(WEBCLIENT_REQUEST_START_TIME);
                    Throwable throwable = signal.getThrowable();
                    ClientResponse clientResponse = signal.get();
                    if (isNull(throwable)) {
                        assert clientResponse != null;
                        log.info("c=WebClientRequest status={} method={} url={} timeElapsed={} ms", clientResponse.rawStatusCode()
                            , clientRequest.method(), clientRequest.url(), System.currentTimeMillis() - startTime);
                    } else {
                        log.error("c=WebClientRequest status={} method={} url={} timeElapsed={} ms e={}",
                            isNull(clientResponse) ? "" : clientResponse.rawStatusCode(), clientRequest.method(), clientRequest.url(),
                            System.currentTimeMillis() - startTime, throwable.getMessage());
                    }
                }
            }).subscriberContext(context -> context.put(WEBCLIENT_REQUEST_START_TIME,
                System.currentTimeMillis()));
    }
}
