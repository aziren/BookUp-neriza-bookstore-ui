package bookup.com.google.bookup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class BStoreAddNewBookManualStep1Activity extends AppCompatActivity
{
    String[] descriptionData = {"1", "2", "3"};

    Button img_book_cover, add_new_book_next1;
    EditText book_title, book_author, book_ISBN, book_genre,book_edition, book_publisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bstore_add_new_book_manual_step1);

        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.bstore_add_newbook_manual_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        refs();

        add_new_book_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (stateProgressBar.getCurrentStateNumber())
                {
                    case 1:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        break;
                    case 2:
                        stateProgressBar.setAllStatesCompleted(true);
                        break;
                }
            }
        });
    }

    private void refs()
    {
        img_book_cover = findViewById(R.id.ImgBtnUploadBookCover);
        add_new_book_next1 = findViewById(R.id.BtnBStoreAddNewBookManualStep1);
        book_title = findViewById(R.id.EdTxtBStoreAddNewBookManualStep1_BookTitle);
        book_author = findViewById(R.id.EdTxtBStoreAddNewBookManualStep1_Author);
        book_ISBN = findViewById(R.id.EdTxtBStoreAddNewBookManualStep1_ISBN);
        book_genre = findViewById(R.id.EdTxtBStoreAddNewBookManualStep1_Genre);
        book_edition = findViewById(R.id.EdTxtBStoreAddNewBookManualStep1_Edition);
        book_publisher = findViewById(R.id.EdTxtBStoreAddNewBookManualStep1_Publisher);
    }
}
