/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.belajar.belajarcrud.dao;

import com.ivans.belajar.belajarcrud.entity.Pegawai;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author ivans
 */
public interface PegawaiDao extends PagingAndSortingRepository<Pegawai, Long>{
    
}
