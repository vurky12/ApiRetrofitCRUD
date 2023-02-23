package www.iesmurgi.apiretrofitcrud;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.iesmurgi.apiretrofitcrud.databinding.InmuebleBinding;

public class Vista extends RecyclerView.ViewHolder {
    private InmuebleBinding binding;

    public Vista(View view) {
        super(view);
        binding = InmuebleBinding.bind(view);
    }

    public void bind(Respuesta inmueble, Adaptador adapter) {
        binding.titulo.setText(inmueble.getTitulo());
        binding.borrarButton.setOnClickListener(v -> {
            API inmuebleApi = API.retrofit.create(API.class);
            long idInmueble = inmueble.getIdInmueble();
            inmuebleApi.deleteInmueble(idInmueble).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        adapter.removeInmueble(inmueble);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("Error al borrrar");
                }
            });
        });

        binding.editarButton.setOnClickListener(v -> {
            Respuesta inmueblePorDefecto = new Respuesta("Editado", 2.0f, "x", 0L, 0.0, "x", "x", "2023-01-01", 0L, 0L);
            API inmuebleApi = API.retrofit.create(API.class);
            inmuebleApi.editarInmueble(inmueble.getIdInmueble(), inmueblePorDefecto).enqueue(new Callback<Respuesta>() {
                @Override
                public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                    if (response.isSuccessful()) {
                        Respuesta inmuebleEditado = response.body();
                        if (inmuebleEditado != null) {
                            adapter.updateInmueble(inmueble, inmuebleEditado);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Respuesta> call, Throwable t) {
                    System.out.println("Error al editar");
                }
            });
        });
    }
}

