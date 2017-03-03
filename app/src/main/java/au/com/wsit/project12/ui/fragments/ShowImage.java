package au.com.wsit.project12.ui.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import au.com.wsit.project12.R;
import au.com.wsit.project12.utils.Constants;

/**
 * Created by guyb on 3/03/17.
 */

public class ShowImage extends DialogFragment
{
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.dialog_show_image, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.earthImage);

        Bundle bundle = getArguments();

        String imageUrl = bundle.getString(Constants.IMAGE_URL);

        Picasso.with(getActivity()).load(imageUrl).into(imageView);


        return rootView;
    }
}
