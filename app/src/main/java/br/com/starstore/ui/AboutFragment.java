package br.com.starstore.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.starstore.R;
import br.com.starstore.databinding.AboutFragmentBinding;
import br.com.starstore.interaction.AboutInteraction;
import br.com.starstore.viewmodel.AboutViewModel;

/**
 * Created by filipenunes on 04/20/18.
 */
public class AboutFragment extends Fragment implements AboutInteraction {

    private AboutViewModel aboutViewModel;
    private static final int REQUEST_PHONE_CALL = 9000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_menu_about);
        aboutViewModel = new AboutViewModel(this);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.about_fragment, container, false);
        AboutFragmentBinding binding = AboutFragmentBinding.bind(view);
        binding.setAboutVM(aboutViewModel);
        return view;
    }

    @Override
    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                getString(R.string.app_contact_email), null));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_contact_subject));
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(intent, getString(R.string.text_send_email)));
    }

    @Override
    public void callPhone() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + getString(R.string.cellphone)));
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        }
    }
}
