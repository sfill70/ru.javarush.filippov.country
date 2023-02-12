Before starting the program, match the database connection settings.
For MySql file - hibernate.cfg.xml
For Redis in File - RedisClientFactory

results (DataBase run in docker containers)

intel 9900KF RAM 32 Gb Win 10
Redis:	 613 ms
MySQL:	1083 ms

AMD  5800H RAM 32 Win 11
Redis:	 ms
MySQL:	 ms

Intel 8530U RAM 32 Gb Ubuntu 22.04
Redis:	 400 ms
MySQL:	1360 ms

AMD FX 3520 RAM 16 Ubuntu 22.04 
Redis:	 794 ms
MySQL:	2259 ms

AMD  5800H RAM 32 Ubuntu 22.04
Redis:	 ms
MySQL:	 ms