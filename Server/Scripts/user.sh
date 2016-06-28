#!/usr/bin/env sh

# 'chmod +rwx ubuntuconfig.sh' 			-- make this file read/write/executable
# 'ssh-copy-id user@SERVER_IP_ADDRESS' 	-- copy ssh creds to server from local machine

#new user
adduser jappeah
gpasswd -a jappeah sudo

#kill root user							-- change PermitRootLogin 'yes' to 'no'
nano /etc/ssh/sshd_config 			

#exit 									-- if able to log back in run 'service ssh restart'
exit
