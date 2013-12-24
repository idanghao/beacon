GRANT ALL PRIVILEGES ON *.* TO 'beacon'@'localhost' IDENTIFIED BY 'beaconv5' WITH GRANT OPTION;


DROP DATABASE IF EXISTS beacon_db_v5;

CREATE DATABASE IF NOT EXISTS beacon_db_v5 default charset utf8 COLLATE utf8_bin;