package www.iesmurgi.apiretrofitcrud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import www.iesmurgi.apiretrofitcrud.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Adaptador adapter;
    private List<Respuesta> inmuebles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupRecyclerView();
        fetchInmuebles();

        binding.anadir.setOnClickListener(v -> {
            addInmueble();
        });
    }

    private void setupRecyclerView() {
        inmuebles = new ArrayList<>();
        adapter = new Adaptador(inmuebles);
        binding.rvInmuebles.setLayoutManager(new LinearLayoutManager(this));
        binding.rvInmuebles.setAdapter(adapter);
    }

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.133:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void addInmueble() {
        Respuesta inmueble = new Respuesta("Piso", 0.0f, "x", 0L, 0.0, "x", "x", "2023-01-01", 0L, 0L);
        Log.d("ANADIR", inmueble.toString());
        Call<Respuesta> call = getRetrofitInstance().create(API.class).altaInmueble(inmueble);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.isSuccessful()) {
                    fetchInmuebles();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("ADD", "Error al hacer la petición");
                showError();
            }
        });
    }

    private void fetchInmuebles() {
        Call<List<Respuesta>> call = getRetrofitInstance().create(API.class).getImuebles();
        call.enqueue(new Callback<List<Respuesta>>() {
            @Override
            public void onResponse(Call<List<Respuesta>> call, Response<List<Respuesta>> response) {
                if (response.isSuccessful()) {
                    List<Respuesta> inmueblesI = response.body();
                    if (inmueblesI != null) {
                        inmuebles.clear();
                        inmuebles.addAll(inmueblesI);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Respuesta>> call, Throwable t) {
                Log.e("FETCH", "No se han podido mostrar los datos");
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
    }
}
