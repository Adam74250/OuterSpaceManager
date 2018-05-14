package macheda.com.outerspacemanager.outerspacemanager.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import macheda.com.outerspacemanager.outerspacemanager.R;
import macheda.com.outerspacemanager.outerspacemanager.activities.BatimentsActivity;
import macheda.com.outerspacemanager.outerspacemanager.adapters.BuildingsArrayAdapter;
import macheda.com.outerspacemanager.outerspacemanager.classes.Building;
import macheda.com.outerspacemanager.outerspacemanager.classes.Config;
import macheda.com.outerspacemanager.outerspacemanager.services.OuterSpaceService;
import macheda.com.outerspacemanager.outerspacemanager.services.responses.BuildingsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adammacheda on 14/05/2018.
 */

public class ListBatimentsFragment extends Fragment {
    private String token;
    public static final String OSM_PREFS = "outerSpaceManagerPrefs";
    private ListView listViewBatiments;
    public List<Building> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ProgressDialog pDialog = ProgressDialog.show(getContext(), "",
                "Récupération des informations...", true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences settings = getContext().getSharedPreferences(OSM_PREFS, 0);
        token = settings.getString("auth_token", "Username");

        OuterSpaceService service = retrofit.create(OuterSpaceService.class);
        Call<BuildingsResponse> rep = service.buildings(token);

        rep.enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                pDialog.dismiss();
                if(response.code() == 200) {
                    listItems = response.body().getBuildings();
                    listViewBatiments.setAdapter(new BuildingsArrayAdapter(getActivity(), listItems));
                    listViewBatiments.setOnItemClickListener((BatimentsActivity)getActivity());
                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue lors de la récupération des infos.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {

            }
        });

        View v = inflater.inflate(R.layout.fragment_list_batiments,container);
        listViewBatiments = (ListView)v.findViewById(R.id.listViewBatiments);
        return v;
    }

}
