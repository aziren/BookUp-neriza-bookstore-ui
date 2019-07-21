package bookup.com.google.bookup;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class BStoreSignUpStep2AdditionalActivity extends AppCompatActivity {

    String[] descData = {"1", "2", "3", "4"};

    Button btnNext3, uploadValDocs2;

    ImageView image2;
    TextView tResult;

    private static  final int CAMERA_REQUEST_CODE = 200;
    private static  final  int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;

    String camPermission[];
    String storePermission[];

    //String[] bookstoreValidDocs = new String[] {"Camera", "Gallery"};
    AlertDialog choiceDialog;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bstore_sign_up_step2_additional);

        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.bstore_signup_step3_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descData);

        refs();

        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (stateProgressBar.getCurrentStateNumber())
                {
                    case 1:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        break;
                    case 2:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        break;
                    case 3:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                        break;
                    case 4:
                        stateProgressBar.setAllStatesCompleted(true);
                        break;
                }
            }
        });
    }
    public void refs() {
        btnNext3 = findViewById(R.id.BtnBStoreSignUpStep2Add);
        image2 = findViewById(R.id.imageIv2);
        uploadValDocs2 = findViewById(R.id.ImgBtnUploadValidDoc2);
        tResult = findViewById(R.id.TxtVwBStoreImg2Txt2);

        uploadValDocs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //items to display in dialog
                String[] items = {" Camera", "Gallery"};
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BStoreSignUpStep2AdditionalActivity.this);
                //set title
                alertBuilder.setTitle("Select Image");
                //alertBuilder.setItems(choices, -1, new DialogInterface.OnClickListener())
                alertBuilder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            //camera option clicked
                            if (!checkCameraPermission()) {
                                //Camera permission not allowed, request it
                                requestCameraPermission();
                            } else {
                                //permission allowed, take picture
                                pickCamera();
                            }
                        }
                        else if (i == 1) {
                            //gallery option clicked
                            if (!checkStoragePermission()) {
                                //Storage permission not allowed, request it
                                requestStoragePermission();
                            } else {
                                //permission allowed, select picture
                                pickGallery();
                            }
                        }
                    }
                });
                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                choiceDialog = alertBuilder.create();
                choiceDialog.show();
            }
        });
    }
    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(BStoreSignUpStep2AdditionalActivity.this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(BStoreSignUpStep2AdditionalActivity.this, camPermission, CAMERA_REQUEST_CODE);
    }
    private void pickCamera() {
        //intent to take image from camera, it will also be save to storage to get high quality image
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPic"); //title of the picture
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image to Text"); //description
        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(BStoreSignUpStep2AdditionalActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return  result;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storePermission, STORAGE_REQUEST_CODE);
    }
    private void pickGallery() {
        //intent to pick image from gallery
        Intent intent = new Intent(Intent.ACTION_PICK);

        //set intent type to image
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }
    //handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case CAMERA_REQUEST_CODE:
                if(grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && writeStorageAccepted){
                        pickCamera();
                    }
                    else
                    {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case STORAGE_REQUEST_CODE:
                if(grantResults.length > 0) {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickGallery();
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    //handle image result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                //got image from gallery now crop it
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON) //enable image guidelines
                        .start(this);
            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                //got image from camera now crop it
                CropImage.activity(uri)
                        .setGuidelines(CropImageView.Guidelines.ON) //enable image guideline
                        .start(this);
            }
        }
        //get cropped image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri(); //get image uri

                //set image to image view
                image2.setImageURI(resultUri);

                //get drawable bitmap for text recognition
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image2.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();

                    //get text from sb until there is no text
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myItems = items.valueAt(i);
                        sb.append(myItems.getValue());
                        sb.append("\n");
                    }
                    //set text to edit text
                    tResult.setText(sb.toString());
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //if there is any error, show it
                Exception error = result.getError();
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
