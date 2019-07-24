ALTER TABLE `kelas_instruktur`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `kelas_peserta`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `jadwal`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `absen_instruktur`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `absen_peserta`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `user_detail`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `user_detail`
	DROP FOREIGN KEY `fk_user_detail_user`;

ALTER TABLE `absen_instruktur`
	DROP FOREIGN KEY `fk_absen_instruktur_user`,
	DROP FOREIGN KEY `fk_absen_instruktur_kelas`;

ALTER TABLE `absen_peserta`
	DROP FOREIGN KEY `fk_absen_peserta_kelas`,
	DROP FOREIGN KEY `fk_absen_peserta_user`;

ALTER TABLE `kelas_instruktur`
	DROP FOREIGN KEY `fk_kelas_instruktur_kelas`,
	DROP FOREIGN KEY `fk_kelas_instruktur_user`;

ALTER TABLE `kelas_peserta`
	DROP FOREIGN KEY `fk_kelas_peserta_user`,
	DROP FOREIGN KEY `fk_kelas_peserta_kelas`;

ALTER TABLE `user`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `jadwal`
	DROP FOREIGN KEY `fk_jadwal_kelas`;

ALTER TABLE `kelas`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `kelas`
	DROP FOREIGN KEY `fk_kelas_materi`;

ALTER TABLE `materi`
	CHANGE COLUMN `id` `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

ALTER TABLE `absen_instruktur`
	ADD CONSTRAINT `fk_absen_instruktur_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE;

ALTER TABLE `absen_instruktur`
	ADD CONSTRAINT `fk_absen_instruktur_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `absen_peserta`
	ADD CONSTRAINT `fk_absen_peserta_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE,
	ADD CONSTRAINT `fb_absen_peserta_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `jadwal`
	ADD CONSTRAINT `fk_jadwal_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE;

ALTER TABLE `kelas`
	ADD CONSTRAINT `fk_kelas_materi` FOREIGN KEY (`id_materi`) REFERENCES `materi` (`id`) ON DELETE CASCADE;

ALTER TABLE `kelas_instruktur`
	ADD CONSTRAINT `fk_kelas_instruktur_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE,
	ADD CONSTRAINT `fk_kelas_instruktur_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `kelas_peserta`
	ADD CONSTRAINT `fk_kelas_peserta_kelas` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`) ON DELETE CASCADE,
	ADD CONSTRAINT `fk_kelas_peserta_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

ALTER TABLE `user_detail`
	ADD CONSTRAINT `fk_user_detail_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;