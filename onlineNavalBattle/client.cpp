#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <netdb.h>
#include <utility>

using namespace std;

pair<bool, string> makeRequest(const char *msg, const char *ip, const char *port) {
	int status;

	struct addrinfo host_info;       // The struct that getaddrinfo() fills up with data.
	struct addrinfo *host_info_list; // Pointer to the to the linked list of host_info's.

	pair<bool, string> values;

	memset(&host_info, 0, sizeof host_info);

	cout << "Setting up the structs..."  << endl;

	host_info.ai_family = AF_UNSPEC;     // IP version not specified. Can be both.
	host_info.ai_socktype = SOCK_STREAM; // Use SOCK_STREAM for TCP or SOCK_DGRAM for UDP.

	status = getaddrinfo(ip, port, &host_info, &host_info_list);
  
	if (status != 0){ 
		cout << "getaddrinfo error" << gai_strerror(status);
		values.first = false;
		return values;
	}

	cout << "Creating a socket..."  << endl;
	int socketfd ; // The socket descripter
	socketfd = socket(host_info_list->ai_family, host_info_list->ai_socktype, 
	host_info_list->ai_protocol);
	if (socketfd == -1){
		cout << "socket error ";
		values.first = false;
		return values;
	}

	cout << "Connecting..."  << endl;
	status = connect(socketfd, host_info_list->ai_addr, host_info_list->ai_addrlen);
  
	if (status == -1){
		cout << "connect error\n";
		values.first = false;
		return values;
	}

	cout << "sending message..."  << endl;

	int len;
	ssize_t bytes_sent;
	len = strlen(msg);
	bytes_sent = send(socketfd, msg, len, 0);

	cout << "Waiting to recieve data..."  << endl;
	ssize_t bytes_recieved;
	char incoming_data_buffer[1000] = {0};
	bytes_recieved = recv(socketfd, incoming_data_buffer, 1000, 0);
  
	if (bytes_recieved == 0) {
		cout << "server shut down." << endl ;
		values.first = false;
		return values;
	}

	if (bytes_recieved == -1){
		cout << "recieve error!" << endl ;
		values.first = false;
		return values;
	}
	cout << bytes_recieved << " bytes recieved :" << endl ;
	cout << incoming_data_buffer << endl;

	values.first = true;
	values.second = incoming_data_buffer;
	return values;
}