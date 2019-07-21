package bookup.com.google.bookup;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;

public class BStoreSignUpStep1Activity extends AppCompatActivity {

    String[] descriptionData = {"1", "2", "3", "4"};

    Button btnNext1, uploadPhoto;
    EditText name, website, contact_no, store_hrs, email, password, confirm_pass;
    Boolean flag = true;
//
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;
    private StorageReference mStorageRef;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Bookstores");

    ArrayList<Bookstore> BookstoreEntry = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bstore_sign_up_step1);

        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.bstore_signup_step1_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        refs();
//
        uploadPhoto.setOnClickListener(SelectPhoto);
        btnNext1.setOnClickListener(UploadRecords);

        btnNext1.setOnClickListener(new View.OnClickListener() {
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
                        stateProgressBar.setAllStatesCompleted(true);
                        break;
                }
            }
        });
    }

    private void refs()
    {
        btnNext1 = findViewById(R.id.BtnBStoreSignUpStep1);
        uploadPhoto = findViewById(R.id.ImgBtnBStoreProfile);
        name = findViewById(R.id.EdTxtBStoreSignUpStep1_Name);
        website = findViewById(R.id.EdTxtBStoreSignUpStep1_Website);
        contact_no = findViewById(R.id.EdTxtBStoreSignUpStep1_ContactNo);
        store_hrs = findViewById(R.id.EdTxtBStoreSignUpStep1_StoreHrs);
        email = findViewById(R.id.EdTxtLogIn_Email);
        password = findViewById(R.id.EdTxtBStoreSignUpStep1_Password);
        confirm_pass = findViewById(R.id.EdTxtBStoreSignUpStep1_ConfirmPassword);
    }

    /*
     *   INTENT TO SELECT PHOTO
     * */
    public View.OnClickListener SelectPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();

            intent.setType("image/*");

            intent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
    };

    //Intent to upload bookstore records
    public View.OnClickListener UploadRecords = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            try
            {
                String bs_name = name.getText().toString();
                String bs_website = website.getText().toString();
                String bs_contactNo = contact_no.getText().toString();
                String bs_storeHrs = store_hrs.getText().toString();
                String bs_email = email.getText().toString();
                String bs_password = password.getText().toString();
                String bs_confirmPassword = confirm_pass.getText().toString();

            }
            catch (Exception e)
            {

            }
        }
    };

}
