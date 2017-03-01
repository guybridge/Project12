package au.com.wsit.project12.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
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
import au.com.wsit.project12.ui.PostcardActivity;
import au.com.wsit.project12.utils.Constants;

/**
 * Created by guyb on 28/02/17.
 */

public class RoverAdapter extends RecyclerView.Adapter<RoverAdapter.ViewHolder>
{

    private Context context;
    private ArrayList<Rover> rovers = new ArrayList<>();

    public RoverAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rover_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void swap(ArrayList<Rover> rovers)
    {
        if(rovers != null)
        {
            this.rovers = rovers;
            notifyDataSetChanged();
        }
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
        private TextView sol;

        public ViewHolder(View itemView)
        {
            super(itemView);
            roverPhoto = (ImageView) itemView.findViewById(R.id.cameraPicture);
            roverName = (TextView) itemView.findViewById(R.id.roverName);
            cameraName = (TextView) itemView.findViewById(R.id.cameraName);
            photoDate = (TextView) itemView.findViewById(R.id.photoDate);
            sol = (TextView) itemView.findViewById(R.id.solTextView);
        }

        private void bindViewHolder(final Rover rover)
        {
            Picasso.with(context).load(rover.getImageUrl()).into(roverPhoto);
            roverName.setText(rover.getRoverName());
            cameraName.setText(rover.getCameraName());
            photoDate.setText(rover.getPhotoDate());
            sol.setText("SOL: " + rover.getSol());

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, PostcardActivity.class);
                    intent.putExtra(Constants.IMAGE_URL, rover.getImageUrl());
                    intent.putExtra(Constants.NAME, rover.getRoverName());
                    intent.putExtra(Constants.CAMERA, rover.getCameraName());
                    intent.putExtra(Constants.SOL, rover.getSol());
                    intent.putExtra(Constants.EARTH_DATE, rover.getPhotoDate());

                    ActivityOptions options = null;

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                    {
                        options = ActivityOptions
                                .makeSceneTransitionAnimation((Activity)context, roverPhoto, context.getString(R.string.imageTransition));
                        context.startActivity(intent, options.toBundle());

                    }
                    else
                    {
                        context.startActivity(intent);
                    }

                }
            });
        }
    }
}
