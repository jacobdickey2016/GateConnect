package com.example.android.gateconnect;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by jacob on 3/1/16.
 */

public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

    public static String atPosition;


    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

        atPosition = parent.getItemAtPosition(pos).toString();

        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

/*if (value.equals("ATL")) {
            // SPINNERS 1 & 2 //

            switch (s1_select) {
                case "A": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_a_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "B": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_b_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "C": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_c_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "D": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_d_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "E": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_e_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "F": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_f_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "T": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_t_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
            }

            // SPINNERS 3 & 4 //

            switch (s3_select) {
                case "A": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_a_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "B": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_b_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "C": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_c_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "D": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_d_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "E": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_e_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "F": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_f_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "T": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atl_gate_t_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
            }

        } else if (value.equals("DTW")) {
            // SPINNER 1 & 2 //

            switch (s1_select) {
                case "A": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dtw_gate_a_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "B": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dtw_gate_b_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
                case "C": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dtw_gate_c_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(spinnerAdapter);
                    break;
                }
            }

            // SPINNER 3 & 4 //

            switch (s3_select) {
                case "A": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dtw_gate_a_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "B": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dtw_gate_b_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
                case "C": {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dtw_gate_c_numbers);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner4.setAdapter(spinnerAdapter);
                    break;
                }
            }

        }*/
