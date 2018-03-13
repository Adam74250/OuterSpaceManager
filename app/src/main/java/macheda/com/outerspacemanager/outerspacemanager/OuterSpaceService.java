package macheda.com.outerspacemanager.outerspacemanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by amacheda on 05/03/2018.
 */

public interface OuterSpaceService {
    @POST("/api/v1/auth/create")
    Call<ConnectionResponse> signUp(@Body ConnectionRequest connectionRequest);
}
