package fall2018.csc2017.game_center;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fall2018.csc2017.game_center.slidingtiles.Board;
import fall2018.csc2017.game_center.slidingtiles.SettingsActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.game_center.R;


public class SelectImageActivity extends AppCompatActivity {
    // Flag to indicate the request of the next task to be performed
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_SELECT_IMAGE = 1;
    // The width and length we want to scale to
    private static final int w = 1000;
    private static final int h = 1300;
    public Bitmap scaledBitmap;
    int i;
    static List<Bitmap> l1 = new ArrayList<>();

    // The URI of photo taken with camera
    private Uri uriPhoto;

    Button bTakePhoto;
    Button bFromGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        bTakePhoto = findViewById(R.id.bTakePhoto);
        bFromGallery = findViewById(R.id.bFromGallery);

        //Activate the Take Photo Button
        bTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        bFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageInGallery();
            }
        });

        i = getIntent().getIntExtra(SettingsActivity.SIZE_CONSTANT, Board.DEFAULT_ROW_COL);
    }

    /**
     * Activate the Take Photo Button.
     *//*
    private void TakePhotoButtonListener() {
        Button TakePhoto = findViewById(R.id.bTakePhoto);
        TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
    }
*/
    /**
     * Activate the From Gallery Button.
     *//*
    private void FromGalleryButtonListener() {
        Button FromGallery = findViewById(R.id.bFromGallery);
        FromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageInGallery();
            }
        });
    }
*/
    /**
     *  Deal with the result of selection of the photos and faces.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
            case REQUEST_SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri imageUri;
                    if (data == null || data.getData() == null) {
                        imageUri = uriPhoto;
                    } else {
                        imageUri = data.getData();
                    }
                    //declare a stream to read the image data from the SD card.
                    InputStream inputStream;
                    //we are getting an input stream based on the URI of the image
                    try {
                        inputStream = getContentResolver().openInputStream(imageUri);
                        //get a bitmap from the stream
                        Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                        //create a bitmap that is scaled
                        if (bmp.getWidth() == w && bmp.getHeight() == h) {
                            scaledBitmap = bmp;
                        } else {
                            scaledBitmap = Bitmap.createScaledBitmap(bmp, w, h, true);
                            bmp.recycle();
                        }
                        ImageCropWithRect(scaledBitmap, i);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     *  this method called after the take photo button is selected.
     *  Phone's camera is called out for user to have a photo taken to be set as background.
     */
    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Save the photo taken to a temporary file.
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File file = File.createTempFile("IMG_", ".jpg", storageDir);
                uriPhoto = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            } catch (IOException e) {
            }
        }
    }

    /**
     *  this method called after the from gallery button is selected.
     *  Phone's gallery is called out for user to select image.
     */
    public void selectImageInGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE);
        }
    }

    /**
     *  Cut image into rectangles images.
     *  Need to decide on return type and how the parameter i is given(the 3, 4, 5) value.
     */
    public static void ImageCropWithRect(Bitmap bitmap, int i) {

        int i1 = 1000 / i;//width of each image
        int i2 = 1300 / i;//height of each image
        for (int cnt1 = 0; cnt1 < i; cnt1++) {
            for (int cnt2 = 0; cnt2 < i; cnt2++) {
                l1.add(Bitmap.createBitmap(bitmap, cnt1 * i2, cnt2 * i1, i1, i2));
            }
        }
    }
}