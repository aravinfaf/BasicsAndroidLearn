package com.e.learn.signature;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.e.learn.R;
import com.williamww.silkysignature.views.SignaturePad;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SignatureActivity extends AppCompatActivity {

    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    private Button mCompressButton;
    private boolean isSignatured = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {
                //Toast.makeText(SignatureActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {

                Toast.makeText(SignatureActivity.this, "Signed", Toast.LENGTH_SHORT).show();
                isSignatured = true;

                if (mSignaturePad.isEmpty()) {
                    mSaveButton.setEnabled(false);
                    mClearButton.setEnabled(false);
                    mCompressButton.setEnabled(false);
                } else {
                    mSaveButton.setEnabled(true);
                    mClearButton.setEnabled(true);
                    mCompressButton.setEnabled(true);
                }
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
                mCompressButton.setEnabled(false);
            }
        });

        mClearButton = (Button) findViewById(R.id.clear_button);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isSignatured){
                    //do next process
                    Toast.makeText(SignatureActivity.this, "SSSS", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(SignatureActivity.this, "Noo", Toast.LENGTH_SHORT).show();

                    Bitmap signatureBitmap = mSignaturePad.getTransparentSignatureBitmap(true);


                    if (addJpgSignatureToGallery(signatureBitmap)) {
                        Toast.makeText(SignatureActivity.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignatureActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                    }
                    if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                        Toast.makeText(SignatureActivity.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignatureActivity.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mCompressButton = (Button) findViewById(R.id.compress_button);
        mCompressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap signatureBitmap = mSignaturePad.getCompressedSignatureBitmap(50);
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(SignatureActivity.this, "50% compressed signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignatureActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Toast.makeText(SignatureActivity.this, bitmap.getHeight()+"/"+bitmap.getWidth(), Toast.LENGTH_SHORT).show();

        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));

            if( signature == null){
                Toast.makeText(SignatureActivity.this, "Sign please", Toast.LENGTH_SHORT).show();
            }else{
                saveBitmapToJPG(signature, photo);
            }

            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        SignatureActivity.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {

            Toast.makeText(SignatureActivity.this, ""+signatureSvg, Toast.LENGTH_SHORT).show();
            Log.e("SSSS",signatureSvg);

            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}