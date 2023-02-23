package www.iesmurgi.apiretrofitcrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Vista> {
    private List<Respuesta> inmuebles;

    public Adaptador(List<Respuesta> inmuebles) {
        this.inmuebles = inmuebles;
    }

    @Override
    public Vista onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inmueble, parent, false);
        return new Vista(view);
    }

    @Override
    public void onBindViewHolder(Vista holder, int position) {
        holder.bind(inmuebles.get(position), this);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public void removeInmueble(Respuesta inmueble) {
        int position = inmuebles.indexOf(inmueble);
        if (position != -1) {
            inmuebles.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateInmueble(Respuesta oldInmueble, Respuesta newInmueble) {
        int index = inmuebles.indexOf(oldInmueble);
        if (index != -1) {
            inmuebles.set(index, newInmueble);
            notifyItemChanged(index);
        }
    }
}