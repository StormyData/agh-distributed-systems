import socket

serverIP = "127.0.0.1"
serverPort = 9008
to_send = 300
msg_bytes = to_send.to_bytes(4, byteorder='little')

print('PYTHON UDP CLIENT')
print(f"Sending {to_send} to {serverIP}, {serverPort}")

client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(msg_bytes, (serverIP, serverPort))

buff, addr = client.recvfrom(1024)
received = int.from_bytes(buff, byteorder="little")
print(f"Received {received} from {addr}")



