package hr.mateo.mircic.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import hr.mateo.mircic.weather.model.Location;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    private List<Location> locationList;
    private Context context;

    public LocationsAdapter(List<Location> locationList, Context context) {
        this.locationList = locationList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_locations, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Location location = locationList.get(position);
        holder.textViewLocationTitle.setText(location.getTitle());
        holder.textViewLocationId.setText(location.getTitle());
        holder.locationlinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    v.setBackgroundColor(Color.parseColor("#c9d8ef"));

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                    v.setBackgroundColor(Color.parseColor("#ffffff"));
                return false;
            }
        });
        holder.locationlinearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, SixDayWeatherActivity.class);
                myIntent.putExtra("LocationId", location.getWoeid());
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewLocationTitle;
        public TextView textViewLocationId;
        public LinearLayout locationlinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewLocationTitle = (TextView) itemView.findViewById(R.id.location_title);
            textViewLocationId = (TextView) itemView.findViewById(R.id.location_id);
            locationlinearLayout = (LinearLayout) itemView.findViewById(R.id.location_linear_layout);

        }
    }
}
