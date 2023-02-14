Before starting the program, match the database connection settings.
For MySql file - hibernate.cfg.xml
now - localhost:3310

For Redis in File - settingsRedis.xml and duplicated settingRedis.yaml
now
host - 127.0.0.1
port - 8001
number DataBase - 0 (default)

results reading random 800 entities (DataBase run in docker containers)
average value

intel 9900K RAM 32 Gb Win 10
Redis:	 499 ms
MySQL:	1250 ms

AMD  5900HS RAM 32 Win 11
Redis:	 497 ms
MySQL:	1251 ms

AMD FX 3520 RAM(DDR3) 16 Ubuntu 22.04 ssd(SATA6)
Redis:	 794 ms
MySQL:	2259 ms

Intel 8530U RAM 32 Gb Ubuntu 22.04
Redis:	 400 ms
MySQL:	1360 ms


connection to local database servers without using Docker

Intel 8530U RAM 32 Gb Ubuntu 22.04
Redis:	 319 ms
MySQL:	1329 ms

Redis reading speed depends on speed RAM

MySql reading speed depends on speed SSD(HDD)

