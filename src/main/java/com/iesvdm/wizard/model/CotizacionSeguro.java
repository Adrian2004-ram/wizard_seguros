package com.iesvdm.wizard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotizacionSeguro {

    private Long id;
    private String nombre;
    private String nif;
    private int edad;
    private int anioNacimiento;
    private String marca;
    private String modelo;
    private int anioMarca;
    private String uso;
    private boolean asistencia;
    private boolean vehSustitucion;
    private BigDecimal precioTotal;
    private String tipoCobertura;

}
