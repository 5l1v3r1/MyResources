#apache2
sudo apt-get update
sudo apt-get install apache2

#mysql
sudo apt-get install mysql-server libapache2-mod-auth-mysql php5-mysql
sudo mysql_install_db
sudo /usr/bin/mysql_secure_installation

#php5
sudo apt-get install php5 libapache2-mod-php5 php5-mcrypt
sudo service apache2 restart

#java-8
sudo apt-add-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
