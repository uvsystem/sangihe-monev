package com.unitedvision.sangihe.monev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.RealisasiException;
import com.unitedvision.sangihe.monev.exception.WrongYearException;
import com.unitedvision.sangihe.monev.repository.RealisasiRepository;
import com.unitedvision.sangihe.monev.service.RealisasiService;

@Service
@Transactional(readOnly = true)
public class RealisasiServiceImpl implements RealisasiService {

	@Autowired
	private RealisasiRepository realisasiRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Realisasi simpan(Realisasi realisasi) throws WrongYearException, RealisasiException {
		if (realisasi.getTahun() < realisasi.getKegiatan().getAwal()
			|| realisasi.getTahun() > realisasi.getKegiatan().getAkhir())
			throw new WrongYearException("Tahun realisasi tidak sesuai dengan tahun kegiatan");
		
		long currentRealisasi = realisasiRepository.summarizeAnggaran(realisasi.getKegiatan());
		if (currentRealisasi + realisasi.getAnggaran() > realisasi.getKegiatan().getAnggaran())
			throw new RealisasiException("Total realisasi anggaran melebihi anggaran kegiatan");

		return realisasiRepository.save(realisasi);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean hapus(Realisasi realisasi) {
		realisasiRepository.delete(realisasi);
		
		return true;
	}

	@Override
	public Realisasi get(int id) {
		return realisasiRepository.findOne(id);
	}

	@Override
	public List<Realisasi> get(Kegiatan kegiatan) {
		return realisasiRepository.findByKegiatan(kegiatan);
	}

	@Override
	public List<Realisasi> get() {
		return realisasiRepository.findAll();
	}

	@Override
	public long getTotalRealisasiAnggaran(Kegiatan kegiatan) {
		return realisasiRepository.summarizeAnggaran(kegiatan);
	}

	@Override
	public long getTotalRealisasiAnggaran(Skpd skpd) {
		return realisasiRepository.summarizeAnggaran(skpd);
	}
}
