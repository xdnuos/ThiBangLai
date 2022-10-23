package com.example.thibanglai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.example.thibanglai.R;
import com.example.thibanglai.ui.QuestionActivity;

public class Submit_dialog extends Dialog {

    public Context context;

    private Button buttonOK;
    private Button buttonCancel;


    public Submit_dialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_submit);

        this.buttonOK = (Button) findViewById(R.id.btn_yes);
        this.buttonCancel  = (Button) findViewById(R.id.btn_no);

        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });
        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }

    // User click "OK" button.
    private void buttonOKClick()  {
        if (context instanceof QuestionActivity) {
            ((QuestionActivity)context).submit();
            this.dismiss();
        }
    }

    // User click "Cancel" button.
    private void buttonCancelClick()  {
        this.dismiss();
    }
}
