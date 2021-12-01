package ec.com.utn.application.juegoonline001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtid,txtnombre, txt_platafotma, txt_tipo, txtedad;
    Juegos js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtid=findViewById(R.id.txtid);
        txtnombre=findViewById(R.id.txtNombre);
        txt_platafotma=findViewById(R.id.txtPlataforma);
        txt_tipo=findViewById(R.id.txtTipoJuego);
        txtedad=findViewById(R.id.txtEdadPer);

        js = new Juegos(this,"juegos1.db", 1);
    }

    public void cmdAdd_onClick(View v){
        Juego j= js.Add(
                Integer.parseInt(txtid.getText().toString()),
                txtnombre.getText().toString(),
                txt_platafotma.getText().toString(),
                txt_tipo.getText().toString(),
                Integer.parseInt(txtedad.getText().toString()));
        Toast.makeText(this, "Juego insertado !!"+j.nombre, Toast.LENGTH_LONG).show();
    }

    public void cmdUpdate_onClick(View v){
        int r = js.Update(
                Integer.parseInt(txtid.getText().toString()),
                txtnombre.getText().toString(),
                txt_platafotma.getText().toString(),
                txt_tipo.getText().toString(),
                Integer.parseInt(txtedad.getText().toString()));
        Toast.makeText(this, "Registro Actualizado !!", Toast.LENGTH_LONG).show();

    }
    public  void cmdDelete_onClick(View v){
        int r = js.Delete(Integer.parseInt(txtid.getText().toString()));
        if(r>0)
            Toast.makeText(this, "Registro Borrado !!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Error al borrar registro !!", Toast.LENGTH_LONG).show();

    }

    public  void cmdSelectOne_onClick(View v)
    {
        Juego j = js.SelectOne(Integer.parseInt(txtid.getText().toString()));
        if (j!=null)
        {
            txtnombre.setText(j.nombre);
            txt_platafotma.setText(j.plataforma);
            txt_tipo.setText(j.tipo_jue);
            txtedad.setText(""+ j.edad_per);
            Toast.makeText(this, "Registro encontrado:", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Registro no encontrado:", Toast.LENGTH_LONG).show();
    }

    public void cmdSelectBydescripcion_onClick(View v){
        Juego p[];
        p=js.Select_ByDescripcion(txtnombre.getText().toString());
        if(p!= null && p.length>0)
        {
            txtid.setText(""+p[0].id);
            txtnombre.setText(""+p[0].nombre);
            txt_platafotma.setText(""+p[0].plataforma);
            txt_tipo.setText(""+p[0].tipo_jue);
            txtedad.setText(""+p[0].edad_per);
        }else
            Toast.makeText(this, "Error de registro Buscado !!", Toast.LENGTH_LONG).show();
    }

}