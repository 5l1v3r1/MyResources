from config import credentials
from tabulate import tabulate
from config import credentials
import boto3
import urllib
import botocore
import pymssql

"""
	Get URLs from tul1cipxdb18. Config.py file contains config properties
"""
print "Obtaining database credentials..."
cred = credentials()
SERVER        = str(cred.getHost())
USER_NAME     = str(cred.getUsername())
PASSWORD      = str(cred.getPassword())
DATABASE_NAME = str(cred.getDatabase())
s3_BUCKET_NAME= str(cred.getBucket())

print "Setting Up Connection to " +SERVER+"..."	
conn = pymssql.connect(SERVER,USER_NAME,PASSWORD,DATABASE_NAME)
connCursor = conn.cursor()
keyStore = []

def getURLs():
	print "Getting URLs from " + DATABASE_NAME + " ..."
	query = "SELECT TOP 100000 * FROM rm_image_file"
	connCursor.execute(query)
	row = connCursor.fetchall()
	file = open("imageurls.txt", "w")
	for ro in row:
		keyStore.append(ro[4])
		file.write(str(ro[4])+"\n")
	file.close()

def appendtwo():
	with open('imageurls.txt') as file:
		line = file.readlines()
		for item in line:
			newfile = open('imageurls.csv','w+')
			newfile.write("/2"+item)
	newfile.close()
	file.close()

"""
#To check if the key exists in S3
def checkKey(key):
	try:
		s3.Object(s3_BUCKET_NAME,str(key[0])).load()
		key[1] = "Exists"
	except botocore.exceptions.ClientError as e:
		key[1] = "Does not Exist"		
	return key

	
def runKeyCheck():
	list 	  = []
	FinalList = []
	keyStore.append("Data")
	for key in keyStore:
		list = [key,""]
		FinalList.append(checkKey(list))
	file = open("Test.txt", "w")
	for row in keyStore:
		file.write(str(row)+"\n")
	file.close()
"""									
getURLs()
appendtwo()
conn.close()