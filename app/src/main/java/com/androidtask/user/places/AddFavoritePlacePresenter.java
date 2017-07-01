package com.androidtask.user.places;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.androidtask.R;
import com.androidtask.UseCase;
import com.androidtask.UseCaseHandler;
import com.androidtask.domain.models.FavoritePlace;
import com.androidtask.domain.usecases.InsertPlace;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.androidtask.register.RegisterPresenter.FILE_DATE_TEMPLATE;
import static com.androidtask.register.RegisterPresenter.FILE_EXTENSION;
import static com.androidtask.register.RegisterPresenter.FILE_PREFIX;
import static com.androidtask.register.RegisterPresenter.SEPARATOR;

/**
 * Created by vova on 30.06.17.
 */

public class AddFavoritePlacePresenter implements AddFavoritePlaceContract.Presenter {

    private final UseCaseHandler mUseCaseHandler;
    private final InsertPlace mInsertPlace;
    private final AddFavoritePlaceActivity.EditTextHolder mHolder;
    private AddFavoritePlaceActivity mView;
    private String mUserId;
    private String mPhotoName;
    private String mCurrentPhotoPath;


    public AddFavoritePlacePresenter(UseCaseHandler instance,
                                     AddFavoritePlaceActivity view,
                                     AddFavoritePlaceActivity.EditTextHolder holder,
                                     String userId,
                                     InsertPlace insertPlace) {
        mUseCaseHandler = instance;
        mView = view;
        mUserId = userId;
        mHolder = holder;
        mInsertPlace = insertPlace;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.showPlaceCoordinates();
        mView.showCity();
    }

    @Override
    public void insertPlace() {
        String title = mHolder.mTitle.getText().toString();
        String description = mHolder.mDescription.getText().toString();
        String latitude = mHolder.mLatitude.getText().toString();
        String longitude = mHolder.mLongitude.getText().toString();
        String city = mHolder.mCity.getText().toString();

        String error = mView.getString(R.string.error_field_empty);
        if (TextUtils.isEmpty(title)){
            mHolder.mTitle.setError(error);
        }else if (TextUtils.isEmpty(latitude)){
            mHolder.mLatitude.setError(error);
        }else if (TextUtils.isEmpty(longitude)){
            mHolder.mLongitude.setError(error);
        }else if (TextUtils.isEmpty(city)){
            mHolder.mCity.setError(error);
        }else if (TextUtils.isEmpty(mPhotoName)){
            mView.showPhotoEmptyError();
        }else {

            FavoritePlace favoritePlace = new FavoritePlace();
            favoritePlace.setId(UUID.randomUUID().toString());
            favoritePlace.setTitle(title);
            favoritePlace.setDescription(description);
            favoritePlace.setCity(city);
            favoritePlace.setLatitude(Double.parseDouble(latitude));
            favoritePlace.setLongitude(Double.parseDouble(longitude));
            favoritePlace.setPhoto(mPhotoName);
            favoritePlace.setUserId(mUserId);

            mUseCaseHandler.execute(mInsertPlace, new InsertPlace.RequestValues(favoritePlace), new UseCase.UseCaseCallback<InsertPlace.ResponseValue>() {
                @Override
                public void onSuccess(InsertPlace.ResponseValue response) {

                    mView.showPlaceInsertedSuccess();

                }

                @Override
                public void onError() {
                    mView.showSaveError();
                }
            });
        }

    }

    @Override
    public void cancel() {
        mView.finish();
    }

    @Override
    public File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat(FILE_DATE_TEMPLATE).format(new Date());
        String imageFileName = FILE_PREFIX + timeStamp + SEPARATOR;

        File image = null;
        try {

            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    FILE_EXTENSION,         /* suffix */
                    getStorageDirectory()      /* directory */
            );

            mCurrentPhotoPath = image.getAbsolutePath();
            mPhotoName = image.getName();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    @Override
    public void addPictureToGallery() {

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mView.sendBroadcast(mediaScanIntent);
    }

    @Override
    public Bitmap createImageBitmap(ImageView mImageView) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;


        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        return bitmap;
    }

    private File getStorageDirectory() {
        return   Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

    }
}
