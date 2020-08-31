package pl.charmas.android.reactivelocation2.observables;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;


public abstract class BaseObservableOnSubscribe<T> implements ObservableOnSubscribe<T> {
    private final Context ctx;
    private final Handler handler;
    private final List<Api<? extends Api.ApiOptions.NotRequiredOptions>> services;

    @SafeVarargs
    protected BaseObservableOnSubscribe(ObservableContext ctx, Api<? extends Api.ApiOptions.NotRequiredOptions>... services) {
        this.ctx = ctx.getContext();
        this.handler = ctx.getHandler();
        this.services = Arrays.asList(services);
    }

    @Override
    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
        final GoogleApiClient apiClient = createApiClient(emitter);
        try {
            apiClient.connect();
        } catch (Throwable ex) {
            if (!emitter.isDisposed()) {
                emitter.onError(ex);
            }
        }

        emitter.setDisposable(Disposables.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if (apiClient.isConnected() || apiClient.isConnecting()) {
                    onDisposed(apiClient);
                    apiClient.disconnect();
                }
            }
        }));
    }


    private GoogleApiClient createApiClient(ObservableEmitter<? super T> emitter) {
        ApiClientConnectionCallbacks apiClientConnectionCallbacks = new ApiClientConnectionCallbacks(emitter);
        GoogleApiClient.Builder apiClientBuilder = new GoogleApiClient.Builder(ctx);

        for (Api<? extends Api.ApiOptions.NotRequiredOptions> service : services) {
            apiClientBuilder = apiClientBuilder.addApi(service);
        }

        apiClientBuilder = apiClientBuilder
                .addConnectionCallbacks(apiClientConnectionCallbacks)
                .addOnConnectionFailedListener(apiClientConnectionCallbacks);

        if (this.handler != null) {
            apiClientBuilder = apiClientBuilder.setHandler(handler);
        }

        GoogleApiClient apiClient = apiClientBuilder.build();
        apiClientConnectionCallbacks.setClient(apiClient);
        return apiClient;
    }

    protected void onDisposed(GoogleApiClient locationClient) {
    }

    protected abstract void onGoogleApiClientReady(GoogleApiClient apiClient, ObservableEmitter<? super T> emitter);

    private class ApiClientConnectionCallbacks implements
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener {

        final private ObservableEmitter<? super T> emitter;

        private GoogleApiClient apiClient;

        private ApiClientConnectionCallbacks(ObservableEmitter<? super T> emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onConnected(Bundle bundle) {
            try {
                onGoogleApiClientReady(apiClient, emitter);
            } catch (Throwable ex) {
                emitter.onError(ex);
            }
        }

        @Override
        public void onConnectionSuspended(int cause) {
            emitter.onError(new GoogleAPIConnectionSuspendedException(cause));
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            try
            {
                emitter.onError(new GoogleAPIConnectionException("Error connecting to GoogleApiClient.", connectionResult));

            }catch (Exception e)
            {
               e.printStackTrace();
            }
        }

        void setClient(GoogleApiClient client) {
            this.apiClient = client;
        }
    }
}
