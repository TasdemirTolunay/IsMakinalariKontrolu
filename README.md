#Usage
***
- Run MerkeziSunucu to start the server.
- Run KullaniciClient.
- If there is no error in the connection of the client to the server, the message "New Client Connected...." will appear on the MerkeziSunucu terminal.
- When the KullaniciClient runs, you need to log in as a user. default username "admin", password "admin".
- Actions you can do when you log in:

![Resim1](C:\Users\tasde\IdeaProjects\IsMakinalariKontrol\src\IsMakinalariImages\KullaniClientIslemler.jpg)

- Run IsMakinalariClient.
- If there is no error in the connection of the client to the server, the message "New Client Connected...." will appear on the MerkeziSunucu terminal.
- You need to make a new machine entry from the IsMakinalariClient. Machine information you need to enter: machine name, machine type, machine speed (unit work per minute).
  For example:
  
  
  ![Resim2](C:\Users\tasde\IdeaProjects\IsMakinalariKontrol\src\IsMakinalariImages\MakinaBilgileriOrnek.jpg)

- Machine information is sent to the server. If the client receives an order that matches the machine type while running, the machine starts working for the specified time (unit work * machine speed). You can see the message that the machine is running from the client terminal.
- A new machine login is made each time the MakinaClient is started. The entered machine waits or runs as long as the Client is running. If no work order is entered from the KullanıcıClient, the Machines will wait in the empty state.
- Multiple user and machine clients can run.
