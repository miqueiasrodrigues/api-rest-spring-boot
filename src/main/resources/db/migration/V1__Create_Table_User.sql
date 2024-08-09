CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL,
  `name` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cpf` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `street` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,
  `neighborhood` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zip_code` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `complement` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL
);

ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK2qv8vmk5wxu215bevli5derq` (`cpf`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

