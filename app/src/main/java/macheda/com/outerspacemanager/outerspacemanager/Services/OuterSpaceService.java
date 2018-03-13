package macheda.com.outerspacemanager.outerspacemanager.Services;

import macheda.com.outerspacemanager.outerspacemanager.Services.Requests.ConnectionRequest;
import macheda.com.outerspacemanager.outerspacemanager.Services.Responses.ConnectionResponse;
import macheda.com.outerspacemanager.outerspacemanager.Services.Requests.LoginRequest;
import macheda.com.outerspacemanager.outerspacemanager.Services.Responses.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by amacheda on 05/03/2018.
 */

public interface OuterSpaceService {
    @POST("/api/v1/auth/create")
    Call<ConnectionResponse> signUp(@Body ConnectionRequest connectionRequest);

    @POST("/api/v1/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
