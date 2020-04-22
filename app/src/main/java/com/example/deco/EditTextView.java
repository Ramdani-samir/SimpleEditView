package com.example.deco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;

public class EditTextView extends LinearLayout {
    private EditText mEditText;
    private TextView mTextView;
    private ImageButton mButtonDone;
    private String contains = "";


    private Drawable ED_BACKGROUND;
    private int ED_TEXT_COLOR;
    private float ED_TEXT_SIZE;
    private int ED_PADDING;
    private String ED_DISP_TEXT;
    private String ED_HINT;


    private Drawable TV_BACKGROUND;
    private int DRAWABLE_RIGHT_COLOR;
    private int TV_TEXT_COLOR;
    private float TV_TEXT_SIZE;
    private int TV_PADDING;
    private String TV_DISP_TEXT;
    private String TV_HINT;

    private int ICON_COLOR;
    private TypedArray a;

    public EditTextView(Context context) {
        super(context);
    }

    @SuppressLint("NewApi")
    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.edittextview, this, true);

        // get views
        mTextView = (TextView) getChildAt(0);
        mEditText = (EditText) getChildAt(1);
        mButtonDone = (ImageButton) getChildAt(2);


        // Zone Totale ----------------------------------------------------------------------------------
        a = context.obtainStyledAttributes(attrs, R.styleable.OptionsView, 0, 0);

        // Zone Button-----------------------------------------------------------------------------------
        ICON_COLOR = a.getInt(R.styleable.OptionsView_setButtonDrawableColor, Color.BLACK);
        mButtonDone.setImageTintList(ColorStateList.valueOf(ICON_COLOR));
        mButtonDone.setOnClickListener(onClickListener);


        // Zone Edit Text--------------------------------------------------------------------------------
        // get
        ED_BACKGROUND = a.getDrawable(R.styleable.OptionsView_setEmbEditTextBackground);
        ED_TEXT_COLOR = a.getColor(R.styleable.OptionsView_setEmbEditTextTextColor,
                getResources().getColor(R.color.defaultColorEditText));
        ED_TEXT_SIZE = a.getDimensionPixelSize(R.styleable.OptionsView_setEmbEditTextTextSize,
                getDefaultSize());
        ED_PADDING = a.getDimensionPixelSize(R.styleable.OptionsView_setEmbEditTextPadding, getDefaultPadding());
        ED_HINT = a.getString(R.styleable.OptionsView_setEmbEditTextHint);
        ED_DISP_TEXT = a.getString(R.styleable.OptionsView_setEmbEditTextDisplayText);
        DRAWABLE_RIGHT_COLOR = a.getColor(R.styleable.OptionsView_setEditRightDrawableColor,
                Color.BLACK);

        // set
        mEditText.setBackground(ED_BACKGROUND);
        mEditText.setTextColor(ED_TEXT_COLOR);
        mEditText.setTextSize(ED_TEXT_SIZE);
        mEditText.setPadding(ED_PADDING, ED_PADDING, ED_PADDING, ED_PADDING);
        mEditText.setHint(ED_HINT);
        mEditText.setText(ED_DISP_TEXT);
        mEditText.setCompoundDrawablesWithIntrinsicBounds(null,
                null, getDrawable(DRAWABLE_RIGHT_COLOR), null);
        // text changes
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextView.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // Zone Text View -------------------------------------------------------------------------------
        // get
        TV_TEXT_COLOR = a.getColor(R.styleable.OptionsView_setTextColor,
                getResources().getColor(R.color.defaultColorTextView));
        TV_BACKGROUND = a.getDrawable(R.styleable.OptionsView_setBackground);
        TV_TEXT_SIZE = a.getDimensionPixelSize(R.styleable.OptionsView_setTextSize, getDefaultSize());
        TV_PADDING = a.getDimensionPixelSize(R.styleable.OptionsView_setPadding, getDefaultPadding());
        TV_HINT = a.getString(R.styleable.OptionsView_setHint);
        TV_DISP_TEXT = a.getString(R.styleable.OptionsView_setText);

        // set
        mTextView.setBackground(TV_BACKGROUND);
        mTextView.setTextColor(TV_TEXT_COLOR);
        mTextView.setTextSize(TV_TEXT_SIZE);
        mTextView.setPadding(TV_PADDING, TV_PADDING, TV_PADDING, TV_PADDING);
        mTextView.setHint(TV_HINT);
        mTextView.setText(TV_DISP_TEXT);
        // handle click
        mTextView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEditText.setText(mTextView.getText());
                mEditText.setVisibility(View.VISIBLE);
                mTextView.setVisibility(View.GONE);
                mButtonDone.setVisibility(View.VISIBLE);
                return false;
            }
        });

        setMinimumHeight(60);
        a.recycle();

    }

    public EditText getEmbeddedEditText() {
        return mEditText;
    }
    public TextView getEmbeddedTextView() {
        return mTextView;
    }
    public ImageButton getEmbeddedButtonDone(){
        return mButtonDone;
    }

    // On mButtonDone click
    private View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            contains = mEditText.getText().toString();
            mEditText.getText().clear();
            mTextView.setText(contains);
            mEditText.setVisibility(View.GONE);
            mButtonDone.setVisibility(View.GONE);
            mTextView.setVisibility(View.VISIBLE);
        }
    };

    private int getDefaultSize() {
        int sizeInPixel = getResources().getDimensionPixelSize(R.dimen.TextSize);
        return sizeInPixel;
    }

    private int getDefaultPadding() {
        int padd = getResources().getDimensionPixelSize(R.dimen.defaultPadding);
        return padd;
    }

    private Drawable getDrawable(int color) {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_edit);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }
}
