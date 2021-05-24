package com.siddhi.softwares.sutsamaj.Handler;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.siddhi.softwares.sutsamaj.databinding.ImageBottomsheetBinding;

public class ImageSheet extends BottomSheetDialogFragment {

    private ImageBottomsheetBinding binding;

    private ImageSheet.DialogClickListener listener;

    private Boolean profileSet;

    public ImageSheet(Boolean profileSet) {
        this.profileSet = profileSet;
    }


    public interface DialogClickListener {
        void onDialogGalleryClick();
        void onDialogCameraClick();
        void onDialogRemoveClick();
    }

    public void setDialogClickListener(ImageSheet.DialogClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ImageBottomsheetBinding.inflate(inflater, container, false);

        if (profileSet){
            binding.removeIv.setVisibility(View.VISIBLE);
            binding.removeTv.setVisibility(View.VISIBLE);
        } else {
            binding.removeIv.setVisibility(View.VISIBLE);
            binding.removeTv.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.removeIv.setOnClickListener(v -> {
            listener.onDialogRemoveClick();
            dismiss();
        });

        binding.removeTv.setOnClickListener(v -> {
            listener.onDialogRemoveClick();
            dismiss();
        });

        binding.galleryImgView.setOnClickListener(v -> {
            listener.onDialogGalleryClick();
            dismiss();
        });

        binding.galleryTv.setOnClickListener(v -> {
            listener.onDialogGalleryClick();
            dismiss();
        });

        binding.cameraImgView.setOnClickListener(v -> {
            listener.onDialogCameraClick();
            dismiss();
        });

        binding.cameraTv.setOnClickListener(v -> {
            listener.onDialogCameraClick();
            dismiss();
        });

    }
}
