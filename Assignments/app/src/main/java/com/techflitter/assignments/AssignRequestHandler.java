package com.techflitter.assignments;

import com.techflitter.assignments.models.Container;

import java.util.HashMap;

import retrofit2.Call;

public class AssignRequestHandler<T> extends BaseModelHandler {
    private static AssignRequestHandler ffkRequestHandler;

    public static AssignRequestHandler getInstance(INetwork iNetwork) {
        if (ffkRequestHandler == null) {
            ffkRequestHandler = new AssignRequestHandler(iNetwork);
        }

        ffkRequestHandler.iNetwork = iNetwork;

        synchronized (ffkRequestHandler) {
            return ffkRequestHandler;
        }
    }

    public static AssignRequestHandler getInstance(INetworkList iNetwork) {
        if (ffkRequestHandler == null) {
            ffkRequestHandler = new AssignRequestHandler(iNetwork);
        }

        ffkRequestHandler.iNetworkList = iNetwork;

        synchronized (ffkRequestHandler) {
            return ffkRequestHandler;
        }
    }

    public static void clearInstance() {
        ffkRequestHandler = null;
    }

    private AssignRequestHandler(INetworkList iNetwork) {
        this.iNetworkList = iNetwork;
    }

    private AssignRequestHandler(INetwork iNetwork) {
        this.iNetwork = iNetwork;
    }

    public void getSearchedContent(HashMap<String, String> params) {
        Call<Container> call = RestClient.getInstance().getApiService().getSearchContent(params);
        call.enqueue(actionOnResponseCallBack());
    }


}
