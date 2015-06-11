package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.exception.WrongYearException;

/**
 * Layanan pemrosesan Kegiatan.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public interface KegiatanService {

	/**
	 * Simpan atau update data kegiatan.
	 * @param kegiatan
	 * @return Kegiatan dengan data baru.
	 * @throws ApplicationException
	 */
	Kegiatan simpan(Kegiatan kegiatan) throws WrongYearException;

	/**
	 * Hapus data kegiatan.
	 * @param kegiatan
	 * @return true jika berhasil, selain itu tidak.
	 * @throws ApplicationException
	 */
	boolean hapus(Kegiatan kegiatan);

	/**
	 * Ambil data Kegiatan berdasarkan id.
	 * @param id
	 * @return data Kegiatan
	 * @throws ApplicationException
	 */
	Kegiatan get(int id);

	/**
	 * Ambil data kegiatan berdasarkan Skpd.
	 * @param skpd
	 * @return data Kegiatan.
	 * @throws ApplicationException
	 */
	List<Kegiatan> get(Skpd skpd);

	/**
	 * Ambil semua data kegiatan.
	 * @return data kegiatan.
	 * @throws ApplicationException
	 */
	List<Kegiatan> get();

	/**
	 * Total semua anggaran SKPD pada tahun tertentu.
	 * @param skpd
	 * @param tahun
	 * @return total anggaran SKPD pada tahun tertentu.
	 * @throws ApplicationException
	 */
	long getTotalAnggaran(Skpd skpd, int tahun);

	/**
	 * Ambil data kegiatan berdasarkan SKPD.
	 * @param idSkpd id SKPD.
	 * @return data kegiatan.
	 */
	List<Kegiatan> getBySkpd(Integer idSkpd);
}
