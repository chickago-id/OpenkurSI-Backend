ALTER TABLE `kelas`
    ADD COLUMN `jumlah_pertemuan` INT UNSIGNED NULL AFTER `target_peserta`,
    ADD COLUMN `biaya` INT UNSIGNED NULL AFTER `jumlah_pertemuan`;