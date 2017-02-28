package au.com.wsit.project12.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import au.com.wsit.project12.R;
import au.com.wsit.project12.model.Rover;

/**
 * Created by guyb on 28/02/17.
 */

public class RoverAdapter extends RecyclerView.Adapter<RoverAdapter.ViewHolder>
{

    private Context context;
    private ArrayList<Rover> rovers;

    public RoverAdapter(Context context, ArrayList<Rover> rovers)
    {
        this.context = context;
        this.rovers = rovers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rover_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bindViewHolder(rovers.get(position));
    }

    @Override
    public int getItemCount()
    {
        return rovers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView roverPhoto;
        private TextView roverName;
        private TextView cameraName;
        private TextView photoDate;

        public ViewHolder(View itemView)
        {
            super(itemView);
            roverPhoto = (ImageView) itemView.findViewById(R.id.roverImageView);
            roverName = (TextView) itemView.findViewById(R.id.roverName);
            cameraName = (TextView) itemView.findViewById(R.id.cameraName);
            photoDate = (TextView) itemView.findViewById(R.id.photoDate);
        }

        private void bindViewHolder(Rover rover)
        {
            Picasso.with(context).load(rover.getImageUrl()).into(roverPhoto);
            roverName.setText(rover.getRoverName());
            cameraName.setText(rover.getCameraName());
            photoDate.setText(rover.getPhotoDate());
        }
    }
}
