package www.iesmurgi.apiretrofitcrud;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface API {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.133:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("inmuebles")
    Call<List<Respuesta>> getImuebles();

    @POST("inmuebles")
    Call<Respuesta> altaInmueble(@Body Respuesta inmueble);

    @DELETE("inmuebles/{id}")
    Call<Void> deleteInmueble(@Path("id") Long id);

    @POST("inmuebles/{id}")
    Call<Respuesta> editarInmueble(@Path("id") Long id, @Body Respuesta inmueble);
}

