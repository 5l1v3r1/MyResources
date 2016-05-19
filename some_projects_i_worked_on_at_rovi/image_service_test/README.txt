1. Set configs in config.txt file to retrieve urls from tul1cipxdb18 (NB.500000 urls are already stored in imageurls.csv)
2. Run getImageURLs.py to retrieve urls from database
3. Run load test
	i. Running load test with parameters
		-Run "c:/path/to/jmeter jmeter -t imageserver.jmx" command.

	ii. Running load test with parameters
		Parameters
			-SAMPLE_FILE = the output file
			-REPLAY_FILE = the data source file
			-THREADS     = the number of users/threads
			-SERVER_NAME = the name of the server
			-SERVER_PORT = the port number

			-Run "c:/path/to/jmeter jmeter -n -t imageserver.jmx -J threads=50 -J sample_file=sample_output.txt -J replay_file=imageurls.csv -J server_name=ec2-54-208-216-63.compute-1.amazonaws.com -J server_port=8084" command
