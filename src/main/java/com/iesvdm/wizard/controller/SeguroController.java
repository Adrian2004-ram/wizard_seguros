package com.iesvdm.wizard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/seguros")
public class SeguroController {

    @GetMapping("/calculos/cotizacion/paso1")
    public String paso1(Model model) {



        return "paso1";

    }

}
