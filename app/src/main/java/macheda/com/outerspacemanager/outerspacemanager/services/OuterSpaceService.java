package macheda.com.outerspacemanager.outerspacemanager.services;

import macheda.com.outerspacemanager.outerspacemanager.classes.CurrentUser;
import macheda.com.outerspacemanager.outerspacemanager.services.requests.RegisterRequest;
import macheda.com.outerspacemanager.outerspacemanager.services.responses.BuildingsResponse;
import macheda.com.outerspacemanager.outerspacemanager.services.responses.RegisterResponse;
import macheda.com.outerspacemanager.outerspacemanager.services.requests.LoginRequest;
import macheda.com.outerspacemanager.outerspacemanager.services.responses.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by amacheda on 05/03/2018.
 */

public interface OuterSpaceService {
    @POST("/api/v1/auth/create")
    Call<RegisterResponse> signUp(@Body RegisterRequest registerRequest);

    @POST("/api/v1/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/api/v1/users/get")
    Call<CurrentUser> currentUser(@Header("x-access-token") String token);

    @GET("/api/v1/buildings/list")
    Call<BuildingsResponse> buildings(@Header("x-access-token") String token);

}
