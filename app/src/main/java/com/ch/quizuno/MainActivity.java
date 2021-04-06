package com.ch.quizuno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView respuesta;
    private EditText nombre;
    private EditText apellido;
    private EditText correo;
    private EditText edad;
    private EditText salario;
    private EditText cargo;
    private Button guardar;
    private Button filtroEdad;
    private Button filtroSalario;
    private Button filtroCargo;
    private ArrayAdapter<String> adapter;
    private ArrayList<Funcionario> persona = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        respuesta = findViewById(R.id.lvRespuesta);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        correo = findViewById(R.id.txtCorreo);
        edad = findViewById(R.id.txtEdad);
        salario = findViewById(R.id.txtSalario);
        cargo = findViewById(R.id.txtCargo);
        guardar = findViewById(R.id.btnGuardar);
        filtroEdad = findViewById(R.id.btnFiltroEdad);
        filtroSalario = findViewById(R.id.btnFiltroSalario);
        filtroCargo = findViewById(R.id.btnFiltroCargo);

        guardar.setOnClickListener(this);
        filtroEdad.setOnClickListener(this);
        filtroSalario.setOnClickListener(this);
        filtroCargo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnGuardar) {
            guardarPersona();
            limpiar();
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, persona);
            respuesta.setAdapter(adapter);
        } else if (v.getId() == R.id.btnFiltroEdad) {
            menorAmayorEdad();
            ArrayList<String> edadFuncionarios = new ArrayList<>();
            String edadMenor = "El Funcionario mas Joven es: " + "\n" + persona.get(0);
            String edadMayor = "El Funcionario mas Viejo(a) es: " + "\n" + persona.get(persona.size() - 1);
            edadFuncionarios.add(edadMenor);
            edadFuncionarios.add(edadMayor);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, edadFuncionarios);
            respuesta.setAdapter(adapter);

        } else if (v.getId() == R.id.btnFiltroSalario) {
            menorAmayorSalario();
            ArrayList<String> salarioFuncionario = new ArrayList<>();
            String salarioMenor = "El Funcionario con el Salario Menor Es: " + "\n" + persona.get(0);
            String salarioMayor = "El Funcionario con el Salario Mayor Es: " + "\n" + persona.get(persona.size() - 1);
            String salarioPromedio = "El Salario Promedio de los Funcionarios Es: " + "\n" + "$" + promedioFSalario();
            salarioFuncionario.add(salarioMenor);
            salarioFuncionario.add(salarioMayor);
            salarioFuncionario.add(salarioPromedio);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, salarioFuncionario);
            respuesta.setAdapter(adapter);

        } else if (v.getId() == R.id.btnFiltroCargo) {
            ArrayList<String> contarPromedioCargo = new ArrayList<>();
            String c1 = "TECNICO";
            String c2 = "TECNOLOGO";
            String c3 = "INGENIERO";

            String mensaje1 = "";
            String mensaje2 = "";
            String mensaje3 = "";

            for (Funcionario funcionarioCargo : persona) {
                if (funcionarioCargo.getfCargo().equals(c1)) {
                    mensaje1 = "El numero de "+c1+"(s) es de: "+contarFuncionario(c1)+"\n"+
                            "El salario promedio del " + c1 + " Es: $" + promedioSalarioCargo(c1);
                } else if (funcionarioCargo.getfCargo().equals(c2)) {
                    mensaje2 = "El numero de "+c2+"(s) es de: "+contarFuncionario(c2)+"\n"+
                            "El salario promedio del " + c2 + " Es: $" + promedioSalarioCargo(c2);
                } else if (funcionarioCargo.getfCargo().equals(c3)) {
                    mensaje3 = "El numero de "+c3+"(s) es de: "+contarFuncionario(c3)+"\n"+
                            "El salario promedio del " + c3 + " Es: $" + promedioSalarioCargo(c3);
                }
            }
            contarPromedioCargo.add(mensaje1);
            contarPromedioCargo.add(mensaje2);
            contarPromedioCargo.add(mensaje3);

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contarPromedioCargo);
            respuesta.setAdapter(adapter);
        }
    }

    private void guardarPersona() {
        String fnombre = nombre.getText().toString();
        String fApellido = apellido.getText().toString();
        String fCorreo = correo.getText().toString();
        int fEdad = Integer.parseInt(edad.getText().toString());
        double fSalario = Double.parseDouble(salario.getText().toString());
        String fCargo = cargo.getText().toString();

        Funcionario fPersona = new Funcionario(fnombre, fApellido, fCorreo, fEdad, fSalario, fCargo);
        persona.add(fPersona);
    }

    private void limpiar() {
        nombre.getText().clear();
        apellido.getText().clear();
        correo.getText().clear();
        edad.getText().clear();
        salario.getText().clear();
        cargo.getText().clear();
    }

    private void menorAmayorEdad() {
        //Metodo para Ordenar de Menor a Mayor la Lista de Funcionarios
        Collections.sort(persona, new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario o1, Funcionario o2) {
                return new Integer(o1.getfEdad()).compareTo(new Integer(o2.getfEdad()));
            }
        });
    }

    private void menorAmayorSalario() {
        //Metodo para Ordenar de Menor a Mayor el Salario
        Collections.sort(persona, new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario s1, Funcionario s2) {
                return new Double(s1.getfSalario()).compareTo(new Double(s2.getfSalario()));
            }
        });
    }

    private Double promedioFSalario() {
        int contador = 0;
        double salarioTotal = 0;
        double promedio = 0;
        for (Funcionario fsalario : persona) {
            salarioTotal += fsalario.getfSalario();
            contador++;
        }
        promedio = salarioTotal / contador;
        return promedio;
    }

    private Double promedioSalarioCargo(String cargof) {
        int contador = 0;
        double subtotalSalario = 0;
        double promedio = 0;

        for (Funcionario funcionario : persona) {
            if (funcionario.getfCargo().equals(cargof)) {
                subtotalSalario += funcionario.getfSalario();
                contador++;
            }
        }
        promedio = subtotalSalario / contador;
        return promedio;
    }


    private Integer contarFuncionario(String cargof) {
        int contador = 0;

        for (Funcionario fcantidad : persona) {
            if (fcantidad.getfCargo().equals(cargof)) {
                contador++;
            }
        }
        return contador;
    }
}