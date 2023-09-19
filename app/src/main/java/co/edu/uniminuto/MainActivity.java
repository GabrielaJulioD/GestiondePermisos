package co.edu.uniminuto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 100;
    private Button checkPermissions;
    private Button requestPermissions;
    private TextView tvCamera;
    private TextView tvContactos;
    private TextView tvUbicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin();
        checkPermissions.setOnClickListener(this::verificarPermisos);
    }

    private void verificarPermisos(View view) {
        int statusPermisionCam = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        int statusPermisionCont = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);
        int statusPermisionLoc = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION);

        tvCamera.setText("Estatus del Permiso de la Cámara:"+statusPermisionCam);
        tvContactos.setText("Estatus del Permiso de acceso a contactos:"+statusPermisionCont);
        tvUbicacion.setText("Estatus del Permiso de la Ubicación:"+statusPermisionLoc);
        requestPermissions.setEnabled(true);
    }

    public void solicitarPermisos(View view) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.CAMERA);
        }

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        }

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.READ_CONTACTS);
        }

        if (!permissionsToRequest.isEmpty()) {
            String[] permissionsArray = new String[permissionsToRequest.size()];
            permissionsToRequest.toArray(permissionsArray);
            ActivityCompat.requestPermissions(MainActivity.this, permissionsArray, REQUEST_CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void begin(){
        tvCamera =findViewById(R.id.tvcamera);
        tvContactos =findViewById(R.id.tvContacts);
        tvUbicacion =findViewById(R.id.tvLocation);
        requestPermissions =findViewById(R.id.btnRequest);
        checkPermissions =findViewById(R.id.btnCheck);
        //Deshabilitar el boton request
        requestPermissions.setEnabled(false);
    }
}