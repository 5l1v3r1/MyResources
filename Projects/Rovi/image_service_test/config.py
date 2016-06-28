class credentials(object):

	def __init__(self):
		self._username = "CORPORATE\jappeah"
		self._password = "Babbatts1"
		self._database = "Staging"
		self._host     = "tul1cipxdb18"
		self._bucket   = "richmedia.rovicorp.com"
	
	def getUsername(self):
		return self._username
	
	def getPassword(self):
		return self._password
	
	def getDatabase(self):
		return self._database
	
	def getHost(self):
		return self._host
	
	def getBucket(self):
		return self._bucket
		
	def getCredentials(self):
		with open('config.txt') as file:
			line = file.read().splitlines()
		
		for item in lines:
			data = item.split("=")
			try:
				if "username" in data[0]:
					self._username = data[1]
				if "password" in data[0]:
					self._password = data[1]
				if "database" in data[0]:
					self._database = data[1]
				if "host" in data[0]:
					self._host = data[1]
				if "bucket" in data[0]:
					self._bucket = data[0]
			except Exception as e:
					print e
						
if __name__ == "__main__":
	getConfig = credentials()
	getConfig.getCredentials()
	