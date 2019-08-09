ALTER TABLE `kelas_peserta`
	ALTER `nis` DROP DEFAULT;
ALTER TABLE `kelas_peserta`
	CHANGE COLUMN `nis` `nis` INT(10) UNSIGNED NULL AFTER `id_user`;