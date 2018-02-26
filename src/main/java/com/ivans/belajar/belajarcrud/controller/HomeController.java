/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.belajar.belajarcrud.controller;

import com.ivans.belajar.belajarcrud.dao.PegawaiDao;
import com.ivans.belajar.belajarcrud.entity.Pegawai;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ivans
 */
@Controller
public class HomeController {

    @Autowired
    private PegawaiDao pegawaiDao;

    @GetMapping("/")
    public String list(ModelMap mm) {
        Iterable<Pegawai> listPegawai = pegawaiDao.findAll();

        mm.addAttribute("listPegawai", listPegawai);
        return "list";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) String id, ModelMap mm) {
        Pegawai pegawai = new Pegawai();
        if (StringUtils.hasText(id)) { // ! null, ! empty, ! " "
            pegawai = pegawaiDao.findOne(Long.parseLong(id));
        }

        mm.addAttribute("halo2", "Form Pegawai");
        mm.addAttribute("pegawai", pegawai);
        return "form";
    }

    @PostMapping("/form")
    public String save(@Valid Pegawai pegawai, BindingResult bindingResult, ModelMap mm) {
        if (bindingResult.hasErrors()) {
            mm.addAttribute("halo2", "Form Pegawai");
            mm.addAttribute("pegawai", pegawai);
            return "form";
        }

        pegawaiDao.save(pegawai);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, ModelMap mm) {
        Pegawai p = pegawaiDao.findOne(id);
        if (p == null) {
            Iterable<Pegawai> listPegawai = pegawaiDao.findAll();
            mm.addAttribute("listPegawai", listPegawai);
            mm.addAttribute("error", "<i>Pegawai Tidak Ada</i>");
            return "list";
        }
        pegawaiDao.delete(id);
        return "redirect:/";
    }

}
