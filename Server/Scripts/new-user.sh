#!/usr/bin/env sh

# 'chmod +rwx ubuntuconfig.sh' -- make this file read/write/executable
# 'ssh-copy-id user@SERVER_IP_ADDRESS' 	-- copy ssh creds to server from local machine

USERNAME= 
adduser ${USERNAME}
gpasswd -a ${USERNAME} sudo
nano /etc/ssh/sshd_config 			
exit
