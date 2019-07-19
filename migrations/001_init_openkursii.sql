-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.6-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table db_openkursi.absen_instruktur
CREATE TABLE IF NOT EXISTS `absen_instruktur` (
  `id` int(10) unsigned NOT NULL,
  `id_kelas` int(10) unsigned NOT NULL,
  `id_user` int(10) unsigned NOT NULL,
  `materi` varchar(250) NOT NULL,
  `jam_masuk` varchar(50) DEFAULT NULL,
  `jam_keluar` varchar(50) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `id_kelas` (`id_kelas`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk_absen_instruktur_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_absen_instruktur_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.absen_peserta
CREATE TABLE IF NOT EXISTS `absen_peserta` (
  `id` int(10) unsigned NOT NULL,
  `id_kelas` int(10) unsigned NOT NULL,
  `id_user` int(10) unsigned NOT NULL,
  `tanggal` date NOT NULL,
  `jam_masuk` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `id_kelas` (`id_kelas`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk_absen_peserta_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_absen_peserta_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.jadwal
CREATE TABLE IF NOT EXISTS `jadwal` (
  `id` int(10) unsigned NOT NULL,
  `id_kelas` int(10) unsigned NOT NULL,
  `tanggal` date NOT NULL,
  `jam_pilihan` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `id_kelas` (`id_kelas`),
  CONSTRAINT `fk_jadwal_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.kelas
CREATE TABLE IF NOT EXISTS `kelas` (
  `id` int(10) unsigned NOT NULL,
  `id_materi` int(10) unsigned NOT NULL,
  `kode_kelas` varchar(150) NOT NULL,
  `jenis_kelas` varchar(250) NOT NULL,
  `jam_pilihan` varchar(250) NOT NULL,
  `tanggal_mulai` date NOT NULL,
  `target_peserta` int(10) unsigned NOT NULL,
  `status` varchar(150) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `id_materi` (`id_materi`),
  CONSTRAINT `fk_kelas_materi` FOREIGN KEY (`id_materi`) REFERENCES `materi` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.kelas_instruktur
CREATE TABLE IF NOT EXISTS `kelas_instruktur` (
  `id` int(10) unsigned NOT NULL,
  `id_kelas` int(10) unsigned NOT NULL,
  `id_user` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kelas` (`id_kelas`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk_kelas_instruktur_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_kelas_instruktur_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.kelas_peserta
CREATE TABLE IF NOT EXISTS `kelas_peserta` (
  `id` int(10) unsigned NOT NULL,
  `id_kelas` int(10) unsigned NOT NULL,
  `id_user` int(10) unsigned NOT NULL,
  `nis` int(10) unsigned NOT NULL,
  `nomor_sertifikat` varchar(150) DEFAULT NULL,
  `nilai` int(10) unsigned NOT NULL DEFAULT 0,
  `status` varchar(150) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `id_kelas` (`id_kelas`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk_kelas_peserta_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_kelas_peserta_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.materi
CREATE TABLE IF NOT EXISTS `materi` (
  `id` int(10) unsigned NOT NULL,
  `kode_materi` varchar(50) NOT NULL,
  `nama_materi` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL,
  `username` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `password` tinytext NOT NULL,
  `role` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table db_openkursi.user_detail
CREATE TABLE IF NOT EXISTS `user_detail` (
  `id` int(10) unsigned NOT NULL,
  `id_user` int(10) unsigned NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `tempat_lahir` varchar(50) DEFAULT NULL,
  `jenis_kelamin` varchar(50) DEFAULT NULL,
  `agama` varchar(50) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `kecamatan` varchar(50) DEFAULT NULL,
  `kota_kabupaten` varchar(50) DEFAULT NULL,
  `provinsi` varchar(50) DEFAULT NULL,
  `kode_pos` varchar(50) DEFAULT NULL,
  `telepon` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `status_saat_ini` varchar(50) DEFAULT NULL,
  `asal_sekolah_kampus` varchar(150) DEFAULT NULL,
  `pekerjaan` varchar(150) DEFAULT NULL,
  `nama_orangtua` varchar(150) DEFAULT NULL,
  `telepon_orangtua` varchar(150) DEFAULT NULL,
  `foto` varchar(150) DEFAULT NULL,
  `informasi_dari` varchar(150) DEFAULT NULL,
  `akun_ig` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `fk_user_detail_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
