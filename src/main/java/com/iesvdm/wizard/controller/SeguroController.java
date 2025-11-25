package com.iesvdm.wizard.controller;

import com.iesvdm.wizard.model.CotizacionSeguro;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/seguros")
public class SeguroController {

    @GetMapping("/calculos/cotizacion/paso1")
    public String paso1(Model model, HttpSession session, @ModelAttribute CotizacionSeguro cotizacionSeguro) {

        var cotizacionSeguroHttpSession = session.getAttribute("cotizacionSeguro");

        if (cotizacionSeguroHttpSession != null) {
            model.addAttribute("cotizacionSeguro", cotizacionSeguroHttpSession);
        } else {
            model.addAttribute("cotizacionSeguro", new CotizacionSeguro());
        }

        return "paso1";

    }

    @GetMapping("calculos/cotizacion/paso2")
    public String anteriorPaso3(Model model, HttpSession session) {

        var cotizacionSeguroHttpSession = session.getAttribute("cotizacionSeguro");

        if (cotizacionSeguroHttpSession != null) {
            model.addAttribute("cotizacionSeguro", cotizacionSeguroHttpSession);

        } else {
            model.addAttribute("cotizacionSeguro", new CotizacionSeguro());
        }


        return "paso2";

    }

    @PostMapping("/calculos/cotizacion/paso2")
    public String paso1Post(Model model, HttpSession session, @ModelAttribute CotizacionSeguro cotizacionSeguro) {

        // Recurda que pordrías utilizar un mecanismo mas abstracto con @SessionAttributes
        session.setAttribute("cotizacionSeguro", cotizacionSeguro);
        // Entre peticiones consecutivas se mantienene el mapa de httpSesion<String, Object>

        model.addAttribute("cotizacionSeguro", cotizacionSeguro);

        return "paso2";

    }



    @PostMapping("/calculos/cotizacion/paso3")
    public String paso2Post(Model model, HttpSession session, @ModelAttribute CotizacionSeguro cotizacionSeguro) {

        var cotizacionSeguroHttpSesion = (CotizacionSeguro) session.getAttribute("cotizacionSeguro");

        if (cotizacionSeguro.getTipoCobertura() == null) {

            cotizacionSeguroHttpSesion.setMarca(cotizacionSeguro.getMarca());
            cotizacionSeguroHttpSesion.setModelo(cotizacionSeguro.getModelo());
            cotizacionSeguroHttpSesion.setAnioMat(cotizacionSeguro.getAnioMat());
            cotizacionSeguroHttpSesion.setUso(cotizacionSeguro.getUso());

        }

        model.addAttribute("cotizacionSeguro", cotizacionSeguro);

        model.addAttribute("datosConductor", cotizacionSeguroHttpSesion.getNombre()
                                + " | " + cotizacionSeguroHttpSesion.getEdad()
                                + " | " + cotizacionSeguroHttpSesion.getAniosCarnet());

        model.addAttribute("datosVehiculo", cotizacionSeguroHttpSesion.getMarca()
                + " | " + cotizacionSeguroHttpSesion.getModelo()
                + " | " + cotizacionSeguroHttpSesion.getAnioMat()
                + " | " + cotizacionSeguroHttpSesion.getUso());


        if (cotizacionSeguro.getTipoCobertura() != null) {

            cotizacionSeguroHttpSesion.setTipoCobertura(cotizacionSeguro.getTipoCobertura());
            cotizacionSeguroHttpSesion.setAsistencia(cotizacionSeguro.isAsistencia());
            cotizacionSeguroHttpSesion.setVehSustitucion(cotizacionSeguro.isVehSustitucion());

            model.addAttribute("datosCobertura", cotizacionSeguroHttpSesion.getTipoCobertura()
                    + " | " + cotizacionSeguroHttpSesion.isAsistencia()
                    + " | " + cotizacionSeguroHttpSesion.isVehSustitucion());

        }

        return "paso3";

    }




}
