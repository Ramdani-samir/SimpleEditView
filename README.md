# SimpleEditView
in its original state, it behaves like a simple textview but when you long click on it,
it becomes an edittext and you can modify its content







# XML
`<com.example.deco.EditTextView
        android:id="@+id/etv1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="20dp"
        android:orientation="vertical"
        app:setBackground="@drawable/textview_background"
        app:setEmbEditTextBackground="@drawable/edittext_background"
        app:setButtonDrawableColor="@color/colorPrimary"
        app:setEditRightDrawableColor="@color/colorPrimary"
        app:setHint=" long click to Edit..." 
/>`

.

.

.

.

.

.

![s](https://user-images.githubusercontent.com/62241965/79981073-5b54ab80-84a4-11ea-8f9f-000a6b3fd92a.png)

# on long click
![longClicked](https://user-images.githubusercontent.com/62241965/79981174-8212e200-84a4-11ea-9a36-050f6817db16.png)

# We can also 
We can also change the color of icons like that :

`app:setButtonDrawableColor="@color/colorPrimary"
 app:setEditRightDrawableColor="@color/colorPrimary"`

![normal](https://user-images.githubusercontent.com/62241965/79981327-c9996e00-84a4-11ea-88f4-e5a11f0cef76.png)

# Java
you can find the view by its ID, and also you can retrieve all views contains this element(edittextview).

`@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edittextview);
        EditTextView editTextView = findViewById(R.id.etv1);
        // to get all embedded views and do what you want.
        editTextView.getEmbeddedEditText(); // embedded edittext
        editTextView.getEmbeddedTextView(); // Textview
        editTextView.getEmbeddedButtonDone(); // imageButton done
    }`

# conclusion
this project is made just to understand how can you create your own component and costumize it as you would like.
